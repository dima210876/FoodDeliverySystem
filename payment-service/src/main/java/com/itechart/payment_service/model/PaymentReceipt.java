package com.itechart.payment_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payment_receipts")
public class PaymentReceipt
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    @NotNull(message = "Order ID is required")
    @NotBlank(message = "Order ID can't be empty")
    @Min(value = 1L, message = "Order ID min limit exceeded")
    @Max(value = Long.MAX_VALUE, message = "Order ID max limit exceeded")
    private Long orderId;

    @Column(name = "receipt_status")
    @NotNull(message = "Receipt status is required")
    @NotBlank(message = "Status can't be empty")
    @Size(max = 20, message = "Status string length exceeded")
    private String receiptStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentReceipt")
    @JsonManagedReference
    private Collection<PaymentAttempt> paymentAttempts;
}
