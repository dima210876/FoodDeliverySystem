package com.itechart.courier_manager.service;

import com.itechart.courier_manager.dto.*;
import com.itechart.courier_manager.exception.ChangeOrderStatusException;
import com.itechart.courier_manager.model.Courier;
import com.itechart.courier_manager.model.CourierOrder;
import com.itechart.courier_manager.repository.CourierOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

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
    private final CourierService courierService;

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

    @Transactional
    public CourierOrder addCourierOrder(AddingCourierOrderDTO courierOrderDto) throws URISyntaxException {
        URI url = new URI("http://food-delivery/changeOrderStatus");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://food-delivery/changeOrderStatus", courierOrderDto.getOrderId(), String.class);
        Courier courier = courierService.findById(courierOrderDto.getUserId());
        CourierOrder courierOrder = createCourierOrder(courierOrderDto, courier);
        return courierOrderRepository.save(courierOrder);
    }

    private CourierOrder createCourierOrder(AddingCourierOrderDTO courierOrderDto, Courier courier){
        return CourierOrder.builder()
                .orderId(courierOrderDto.getOrderId())
                .courier(courier)
                .deliveryMethod("b")
                .deliveryStatus("delivering")
                .build();
    }
}
