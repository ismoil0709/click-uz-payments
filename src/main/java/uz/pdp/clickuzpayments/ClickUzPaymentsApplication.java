package uz.pdp.clickuzpayments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.pdp.clickuzpayments.exception.NullOrEmptyException;

import java.util.List;

@SpringBootApplication
@EnableFeignClients(basePackages = "uz.pdp")
@EnableJpaAuditing
public class ClickUzPaymentsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClickUzPaymentsApplication.class,args);
    }

}
