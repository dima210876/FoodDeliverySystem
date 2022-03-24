package com.itechart.food_delivery.repository;

import com.itechart.food_delivery.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
