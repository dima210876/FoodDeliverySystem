package com.itechart.restaurant_info_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restaurants")
public class Manager {

    @Id
    private Long user_id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;

    @OneToOne(mappedBy = "manager")
    private Restaurant restaurant;
}
