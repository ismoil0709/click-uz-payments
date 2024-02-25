package uz.pdp.clickuzpayments.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uz.pdp.clickuzpayments.dto.RequestToServiceDto;

@UtilityClass
public class Requests {
    public static boolean sendRequest(RequestToServiceDto pay, String url) {
        return true;
//        ResponseEntity<Response> entity = new RestTemplate().postForEntity(url, pay, Response.class);
//        if (entity.getBody() == null)
//            return false;
//        return entity.getBody().getSuccess();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    private class Response {
        private Boolean success;
    }
}
