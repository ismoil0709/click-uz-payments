package uz.pdp.clickuzpayments.model;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.clickuzpayments.model.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Service service;
    @Transient
    private Card card;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private LocalDateTime paymentTime;

    public Payment(Long id, Service service, Card card, BigDecimal amount, PaymentStatus status, LocalDateTime paymentTime) {
        this.id = id;
        this.service = service;
        this.card = null;
        this.amount = amount;
        this.status = status;
        this.paymentTime = paymentTime;
    }
}
