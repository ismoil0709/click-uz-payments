package uz.pdp.clickuzpayments.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Embeddable
public class Field extends Auditing{
    private String name;
    private String fullText;
    private String regex;
    private List<String> values;
    private String format;
}
