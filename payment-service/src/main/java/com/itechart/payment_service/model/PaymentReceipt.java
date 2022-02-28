package com.itechart.payment_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "receipt_status", nullable = false)
    private String receiptStatus;

    @OneToMany(mappedBy = "paymentReceipt")
    private Set<PaymentAttempt> paymentAttempts;
}
