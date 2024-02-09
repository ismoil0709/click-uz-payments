package uz.pdp.clickuzpayments.service;

import org.springframework.stereotype.Service;
import uz.pdp.clickuzpayments.dto.response.PaymentDto;
import uz.pdp.clickuzpayments.model.Payment;
import uz.pdp.clickuzpayments.model.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PaymentService {
    Payment payment(PaymentDto paymentDto);
    List<Payment> getAll();
    List<Payment> getAllByCardId(Long cardId);
    List<Payment> getAllByStatus(PaymentStatus status);
    List<Payment> getAllByPaymentTime(LocalDateTime paymentTime);
}
