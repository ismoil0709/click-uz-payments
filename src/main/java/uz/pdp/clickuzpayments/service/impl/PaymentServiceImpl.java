package uz.pdp.clickuzpayments.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.pdp.clickuzpayments.dto.CustomResponseEntity;
import uz.pdp.clickuzpayments.dto.RequestToServiceDto;
import uz.pdp.clickuzpayments.dto.PaymentDto;
import uz.pdp.clickuzpayments.dto.SetBalanceDto;
import uz.pdp.clickuzpayments.exception.BadRequestException;
import uz.pdp.clickuzpayments.exception.InvalidArgumentException;
import uz.pdp.clickuzpayments.exception.NotFoundException;
import uz.pdp.clickuzpayments.exception.NullOrEmptyException;
import uz.pdp.clickuzpayments.model.Card;
import uz.pdp.clickuzpayments.model.History;
import uz.pdp.clickuzpayments.model.enums.PaymentStatus;
import uz.pdp.clickuzpayments.proxy.CardProxy;
import uz.pdp.clickuzpayments.proxy.HistoryProxy;
import uz.pdp.clickuzpayments.repository.ServiceRepository;
import uz.pdp.clickuzpayments.security.filter.JwtTokenFilter;
import uz.pdp.clickuzpayments.service.PaymentService;
import uz.pdp.clickuzpayments.util.Requests;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final CardProxy cardProxy;
    private final HistoryProxy historyProxy;
    private final ServiceRepository serviceRepository;
    @Value("${click.uz.const.min}")
    private BigDecimal min;
    @Value("${click.uz.const.max}")
    private BigDecimal max;
    @Value("${click.uz.const.commission}")
    private Integer commission;
    @Override
    public History payment(PaymentDto paymentDto) {
        if (paymentDto.getServiceId() == null)
            throw new NullOrEmptyException("Service Id");
        if (paymentDto.getCardId() == null)
            throw new NullOrEmptyException("Card Id");
        if (paymentDto.getAmount() == null)
            throw new NullOrEmptyException("Amount");
        if (paymentDto.getValues() == null)
            throw new NullOrEmptyException("Values");
        if (paymentDto.getAmount().compareTo(min) < 0)
            throw new InvalidArgumentException("balance");
        if (paymentDto.getAmount().compareTo(max) > 0)
            throw new NullOrEmptyException("balance");
        PaymentStatus paymentStatus = PaymentStatus.FAILED;
        CustomResponseEntity<Card> proxyCardById = cardProxy.getCardById(JwtTokenFilter.TOKEN,paymentDto.getCardId());
        if (proxyCardById.getBody() == null)
            throw new BadRequestException(proxyCardById.getErrorMessage());
        Card card = proxyCardById.getBody();
        uz.pdp.clickuzpayments.model.Service service = serviceRepository.findById(paymentDto.getServiceId()).orElseThrow(
                () -> new NotFoundException("Service")
        );
        paymentDto.getValues().keySet().forEach(k -> service.getForm().getFields().forEach(f -> {
            if (!f.getName().equals(k))
                throw new InvalidArgumentException("value");}));
        if (Requests.sendRequest(new RequestToServiceDto(card.getCardNumber(), paymentDto.getValues(), paymentDto.getAmount()), service.getUrl())) {
            BigDecimal commission = paymentDto.getAmount().multiply(new BigDecimal(this.commission).divide(new BigDecimal(100),2,RoundingMode.HALF_UP));
            if (paymentDto.getAmount().add(commission).compareTo(card.getBalance()) > 0)
                throw new InvalidArgumentException("amount");
            else {
                BigDecimal newBalance = card.getBalance().subtract(paymentDto.getAmount().add(commission));
                newBalance = newBalance.add(paymentDto.getAmount().multiply(new BigDecimal(service.getCashback()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)));
                cardProxy.setBalance(JwtTokenFilter.TOKEN,new SetBalanceDto(card.getId(),newBalance));
                paymentStatus = PaymentStatus.SUCCESS;
            }
        }
        CustomResponseEntity<History> responseEntity = historyProxy.create(JwtTokenFilter.TOKEN,History.builder()
                .serviceId(service.getId())
                .receiverCardNumber(null)
                .senderCardNumber(card.getCardNumber())
                .transactionDateTime(LocalDate.now().atTime(LocalTime.now().getHour(), LocalTime.now().getMinute(), 0))
                .amount(paymentDto.getAmount())
                .status(paymentStatus)
                .build());
        if (responseEntity.getBody() == null)
            throw new BadRequestException(responseEntity.getErrorMessage());
        return responseEntity.getBody();
    }
}
