package uz.pdp.clickuzpayments.model;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.clickuzpayments.model.enums.CardType;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Card {
    private Long id;
    private String name;
    private String cardNumber;
    private String expiryDate;
    @Enumerated(EnumType.STRING)
    private CardType type;
    private boolean isMain;
    private Byte cvv;
    private BigDecimal balance;
}
