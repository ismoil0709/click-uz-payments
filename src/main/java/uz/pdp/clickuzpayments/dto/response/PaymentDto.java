package uz.pdp.clickuzpayments.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@Getter
@Builder
public class PaymentDto {
    private Long serviceId;
    private Long cardId;
    private Map<String,Object> values;
    private BigDecimal amount;
}
