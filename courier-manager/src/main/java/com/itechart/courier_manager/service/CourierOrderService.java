package com.itechart.courier_manager.service;

import com.itechart.courier_manager.dto.CourierOrderDto;
import com.itechart.courier_manager.model.Courier;
import com.itechart.courier_manager.model.CourierOrder;
import com.itechart.courier_manager.repository.CourierOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@AllArgsConstructor
public class CourierOrderService {
    private final CourierOrderRepository courierOrderRepository;
    private final CourierService courierService;
    private final RestTemplate restTemplate;

    @Transactional
    public CourierOrder addCourierOrder(CourierOrderDto courierOrderDto) throws URISyntaxException {
        URI url = new URI("http://food-delivery/changeOrderStatus");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://food-delivery/changeOrderStatus", courierOrderDto.getOrderId(), String.class);
        Courier courier = courierService.findById(courierOrderDto.getUserId());
        CourierOrder courierOrder = createCourierOrder(courierOrderDto, courier);
        return courierOrderRepository.save(courierOrder);
    }

    private CourierOrder createCourierOrder(CourierOrderDto courierOrderDto, Courier courier){
        return CourierOrder.builder()
                .orderId(courierOrderDto.getOrderId())
                .courier(courier)
                .deliveryMethod("b")
                .deliveryStatus("delivering")
                .build();
    }
}
