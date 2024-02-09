package uz.pdp.clickuzpayments.model;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.clickuzpayments.model.enums.RegionType;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private String passport;
    private LocalDate dateOfIssue;
    private LocalDate expiryDate;
    private String JShShIR;
    private LocalDate dateOfBirth;
    private RegionType region;
    private Byte PIN;
    private String phoneNumber;
    private List<Card> cards;
    private List<Device> devices;
}
