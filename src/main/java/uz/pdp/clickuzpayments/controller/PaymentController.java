package uz.pdp.clickuzpayments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuzpayments.dto.CustomResponseEntity;
import uz.pdp.clickuzpayments.dto.PaymentDto;
import uz.pdp.clickuzpayments.service.PaymentService;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("/")
    public CustomResponseEntity<?> payment(@RequestBody PaymentDto paymentDto) {
        return CustomResponseEntity.ok(paymentService.payment(paymentDto));
    }
}
