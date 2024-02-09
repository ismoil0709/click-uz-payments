package uz.pdp.clickuzpayments.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.clickuzpayments.model.Card;
import uz.pdp.clickuzpayments.model.Form;
import uz.pdp.clickuzpayments.model.Service;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@Getter
public class PayToServiceDto {
    private Form form;
    private Card card;
    private Map<String,Object> values;
    private BigDecimal amount;
}
