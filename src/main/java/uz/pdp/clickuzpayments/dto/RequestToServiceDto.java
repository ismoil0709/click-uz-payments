package uz.pdp.clickuzpayments.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@Getter
public class RequestToServiceDto {
    private String cardNumber;
    private Map<String,Object> values;
    private BigDecimal amount;
}
