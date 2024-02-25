package uz.pdp.clickuzpayments.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.clickuzpayments.dto.CustomResponseEntity;
import uz.pdp.clickuzpayments.security.ClickUzAuthentication;

@FeignClient(value = "click-uz-settings")
public interface TokenProxy {
    @GetMapping("/verify")
    CustomResponseEntity<ClickUzAuthentication> verify(@RequestParam String token);
}
