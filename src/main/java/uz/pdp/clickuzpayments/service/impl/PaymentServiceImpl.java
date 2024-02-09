package uz.pdp.clickuzpayments.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.clickuzpayments.dto.response.PayToServiceDto;
import uz.pdp.clickuzpayments.dto.response.PaymentDto;
import uz.pdp.clickuzpayments.exception.InvalidArgumentException;
import uz.pdp.clickuzpayments.exception.NotFoundException;
import uz.pdp.clickuzpayments.exception.NullOrEmptyException;
import uz.pdp.clickuzpayments.model.Card;
import uz.pdp.clickuzpayments.model.Payment;
import uz.pdp.clickuzpayments.model.enums.PaymentStatus;
import uz.pdp.clickuzpayments.proxy.CardProxy;
import uz.pdp.clickuzpayments.repository.PaymentRepository;
import uz.pdp.clickuzpayments.repository.ServiceRepository;
import uz.pdp.clickuzpayments.service.PaymentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final CardProxy cardProxy;
    private final ServiceRepository serviceRepository;

    @Override
    public Payment payment(PaymentDto paymentDto) {
        if (paymentDto.getServiceId() == null)
            throw new NullOrEmptyException("Service Id");
        if (paymentDto.getCardId() == null)
            throw new NullOrEmptyException("Card Id");
        if (paymentDto.getAmount() == null)
            throw new NullOrEmptyException("Amount");
        if (paymentDto.getValues() == null)
            throw new NullOrEmptyException("Values");
        PaymentStatus paymentStatus = PaymentStatus.ERROR;
        ResponseEntity<?> proxyCardById = cardProxy.getCardById(paymentDto.getCardId());
        if (proxyCardById.getBody() == null)
            throw new NotFoundException("Card");
        Card card = (Card) proxyCardById.getBody();
        uz.pdp.clickuzpayments.model.Service service = serviceRepository.findById(paymentDto.getServiceId()).orElseThrow(
                () -> new NotFoundException("Service")
        );
        paymentDto.getValues().keySet().forEach(k -> {
                    service.getForm().getFields().forEach(f -> {
                        if (!f.getName().equals(k))
                            throw new InvalidArgumentException("value");
                    });
                }
        );
        cardProxy.setBalance(card.getBalance().subtract(paymentDto.getAmount()));
        if (sendRequest(new PayToServiceDto(service.getForm(), card, paymentDto.getValues(), paymentDto.getAmount()), service.getUrl())) {

            cardProxy.setBalance(card.getBalance().subtract(paymentDto.getAmount()));
            paymentStatus = PaymentStatus.SUCCESSFULLY;
        }
        return paymentRepository.save(
                Payment.builder()
                        .service(service)
                        .card(card)
                        .paymentTime(LocalDateTime.now())
                        .amount(paymentDto.getAmount())
                        .status(paymentStatus)
                        .build()
        );
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getAllByCardId(Long cardId) {
        if (cardId == null)
            throw new NullOrEmptyException("Card Id");
        ResponseEntity<?> proxyCardById = cardProxy.getCardById(cardId);
        if (proxyCardById.getBody() == null)
            throw new NotFoundException("Card");
       return paymentRepository.findAllByCardId(cardId);
    }

    @Override
    public List<Payment> getAllByStatus(PaymentStatus status) {
        if (status == null)
            throw new NullOrEmptyException("Status");
        return paymentRepository.findAllByStatus(status);
    }

    @Override
    public List<Payment> getAllByPaymentTime(LocalDateTime paymentTime) {
        if (paymentTime == null)
            throw new NullOrEmptyException("PaymentTime");
        if (paymentTime.isAfter(LocalDateTime.now()))
            throw new InvalidArgumentException("PaymentTime");
        return paymentRepository.findAllByPaymentTime(paymentTime);
    }

    private boolean sendRequest(PayToServiceDto pay, String url) {
        try {
            return new RestTemplate().postForLocation(url, pay) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
