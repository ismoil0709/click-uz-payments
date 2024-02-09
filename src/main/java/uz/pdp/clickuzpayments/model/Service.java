package uz.pdp.clickuzpayments.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import uz.pdp.clickuzpayments.model.enums.ServiceCategory;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Service extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ServiceCategory category;
    private Boolean isMaintenance;
    private String url;
    private Integer cashback;
    private Double longitude;
    private Double latitude;
    @Embedded
    @Cascade(CascadeType.ALL)
    private Form form;
}
