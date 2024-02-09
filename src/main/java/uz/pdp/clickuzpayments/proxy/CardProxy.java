package uz.pdp.clickuzpayments.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(value = "click-uz-cards")
public interface CardProxy {
    @GetMapping("")
    ResponseEntity<?> getCardById(Long id);
    @GetMapping("/{balance}")
    void setBalance(@PathVariable BigDecimal balance);
    @GetMapping("")
    ResponseEntity<?> getCardByNumber(String cardNumber);
}
