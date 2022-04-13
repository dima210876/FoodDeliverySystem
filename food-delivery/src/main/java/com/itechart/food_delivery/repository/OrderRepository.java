package com.itechart.food_delivery.repository;

import com.itechart.food_delivery.dto.ReadyOrderDTO;
import com.itechart.food_delivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select new com.itechart.food_delivery.dto.ReadyOrderDTO(o.id, o.customer, o.orderPrice, o.orderAddress)\n" +
            "from Order o JOIN Customer c on o.customer = c\n" +
            "where o.orderStatus = 'ready'")
    List<ReadyOrderDTO> getAllReadyOrders();

    @Modifying
    @Transactional
    @Query("update Order o set o.orderStatus='delivering' where o.id=?1")
    void changeOrderStatus(Long id);
}
