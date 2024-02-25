package uz.pdp.clickuzpayments.service;

import org.springframework.stereotype.Service;
import uz.pdp.clickuzpayments.dto.PaymentDto;
import uz.pdp.clickuzpayments.model.History;

@Service
public interface PaymentService {
    History payment(PaymentDto paymentDto);
}
