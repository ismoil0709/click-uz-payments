package uz.pdp.clickuzpayments.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import uz.pdp.clickuzpayments.dto.CustomResponseEntity;
import uz.pdp.clickuzpayments.dto.SetBalanceDto;
import uz.pdp.clickuzpayments.model.Card;

import java.util.Map;

@FeignClient(name = "click-uz-cards")
public interface CardProxy {
    @GetMapping("/card/{id}")
    CustomResponseEntity<Card> getCardById(@RequestHeader("Authorization") String token, @PathVariable Long id);
    @PostMapping("/card/balance")
    void setBalance(@RequestHeader("Authorization") String token, @RequestBody SetBalanceDto setBalanceDto);
}
