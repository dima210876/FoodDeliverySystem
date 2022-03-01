package com.itechart.payment_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payment_providers")
public class PaymentProvider
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(max = 100, message = "Name string length exceeded")
    private String name;

    @Column(name = "status")
    @NotNull(message = "Status is required")
    @NotBlank(message = "Status can't be empty")
    @Size(max = 20, message = "Status string length exceeded")
    private String status;

    @Column(name = "description")
    @Size(max = 200, message = "Description string length exceeded")
    private String description;
}
