package uz.pdp.clickuzpayments.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Device {
    private Long id;
    private String name;
    private String IP;
    private LocalDateTime registered;
    private LocalDateTime lastEnter;
}

