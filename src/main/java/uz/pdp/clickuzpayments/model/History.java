package uz.pdp.clickuzpayments.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.clickuzpayments.model.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class History {
    private Long id;
    private String receiverCardNumber;
    private String senderCardNumber;
    private BigDecimal amount;
    private Long serviceId;
    private LocalDateTime transactionDateTime;
    private PaymentStatus status;
}