package com.itechart.courier_manager.repository;

import com.itechart.courier_manager.model.Courier;
import com.itechart.courier_manager.model.CourierManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourierRepository extends JpaRepository<Courier, Long> {
    Optional<Courier> findByUserId(Long userId);

    @Query("select c \n" +
            "from Courier c where c.organization.id = ?1 and c.email not in (select c.email from Courier c" +
            "LEFT JOIN CourierOrder co on c.userId = co.courier.userId where co.deliveryStatus = 'delivering') \n" +
            "group by c")
    List<Courier> getUnoccupiedCouriers(Long organizationId);
}
