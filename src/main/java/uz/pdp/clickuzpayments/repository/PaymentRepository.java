package uz.pdp.clickuzpayments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.clickuzpayments.model.Payment;
import uz.pdp.clickuzpayments.model.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findAllByServiceId(Long serviceId);
    List<Payment> findAllByCardId(Long cardId);
    List<Payment> findAllByStatus(PaymentStatus status);
    List<Payment> findAllByPaymentTime(LocalDateTime paymentTime);
}
