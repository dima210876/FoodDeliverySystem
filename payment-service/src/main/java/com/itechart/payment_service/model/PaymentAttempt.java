package com.itechart.payment_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payment_attempts")
public class PaymentAttempt
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_receipt_id", referencedColumnName = "id")
    @NotNull(message = "Payment receipt id is required")
    @JsonBackReference
    private PaymentReceipt paymentReceipt;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_provider_id", referencedColumnName = "id")
    @NotNull(message = "Payment provider id is required")
    @JsonManagedReference
    private PaymentProvider paymentProvider;

    @Column(name = "transaction_number")
    @Size(min = 10, max = 50, message = "Transaction number string length limits exceeded")
    private String transactionNumber;

    @Column(name = "payment_method")
    @NotNull(message = "Payment method is required")
    @NotBlank(message = "Payment method can't be empty")
    @Size(min = 2, max = 50, message = "Payment method string length limits exceeded")
    private String paymentMethod;

    @Column(name = "payment_status")
    @NotNull(message = "Payment status is required")
    @NotBlank(message = "Payment status can't be empty")
    @Size(min = 2, max = 20, message = "Payment status string length limits exceeded")
    private String paymentStatus;

    @Column(name = "payment_datetime")
    @NotNull(message = "Payment datetime is required")
    @NotBlank(message = "Payment datetime can't be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime paymentDatetime;
}
