package com.itechart.courier_manager.service;

import com.itechart.courier_manager.dto.ChangeStatusDTO;
import com.itechart.courier_manager.dto.CourierOrderDTO;
import com.itechart.courier_manager.dto.OrderAddressesDTO;
import com.itechart.courier_manager.dto.OrderDto;
import com.itechart.courier_manager.exception.ChangeOrderStatusException;
import com.itechart.courier_manager.model.CourierOrder;
import com.itechart.courier_manager.repository.CourierOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourierOrderService {
    private final CourierOrderRepository courierOrderRepository;
    @LoadBalanced
    private final RestTemplate restTemplate;

    public List<CourierOrderDTO> getCourierOrders(Long courierId) throws ChangeOrderStatusException {
        List<CourierOrder> courierOrders = courierOrderRepository.findAllByCourier_UserId(courierId);

        List<CourierOrderDTO> courierOrderDTOs = new ArrayList<>();

        for (CourierOrder courierOrder : courierOrders) {
            final String GET_ORDER_ADDRESSES = "http://FOOD-DELIVERY/getOrderAddresses/" + courierOrder.getOrderId();
            ResponseEntity<OrderAddressesDTO> response = restTemplate.postForEntity(GET_ORDER_ADDRESSES, courierOrder.getOrderId(),OrderAddressesDTO.class);

            if(!response.getStatusCode().is2xxSuccessful()){
                throw new ChangeOrderStatusException("Couldn't change order status");
            }

            OrderAddressesDTO orderAddressesDTO = response.getBody();

            courierOrderDTOs.add(
                    CourierOrderDTO.builder()
                            .id(courierOrder.getOrderId())
                            .restaurantAddresses(orderAddressesDTO.getRestaurantAddresses())
                            .deliveryAddress(orderAddressesDTO.getDeliveryAddress())
                            .orderStatus(courierOrder.getDeliveryStatus())
                            .build()
            );
        }

        return courierOrderDTOs;
    }

    public void changeOrderStatus(ChangeStatusDTO changeStatusDTO) throws ChangeOrderStatusException {
        final String CHANGE_ORDER_STATUS = "http://FOOD-DELIVERY/getOrderAddresses/" + changeStatusDTO.getId();

        if(Objects.equals(changeStatusDTO.getOrderStatus(), "DELIVERING")){
            throw new ChangeOrderStatusException("Order is finished");
        }

        Optional<CourierOrder> courierOrderOptional = courierOrderRepository.findByOrderId(changeStatusDTO.getId());

        if (courierOrderOptional.isEmpty()) {
            throw new ChangeOrderStatusException("Couldn't change order status");
        }

        CourierOrder courierOrder = courierOrderOptional.get();
        courierOrder.setDeliveryStatus(changeStatusDTO.getOrderStatus());
        courierOrderRepository.save(courierOrder);

        ResponseEntity<OrderDto> response = restTemplate.postForEntity(CHANGE_ORDER_STATUS,
                changeStatusDTO, OrderDto.class);

        if(!response.getStatusCode().is2xxSuccessful()){
            throw new ChangeOrderStatusException("Couldn't change order status");
        }
    }
}
