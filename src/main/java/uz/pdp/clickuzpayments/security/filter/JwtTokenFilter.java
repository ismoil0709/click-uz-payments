package uz.pdp.clickuzpayments.security.filter;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.clickuzpayments.dto.CustomResponseEntity;
import uz.pdp.clickuzpayments.proxy.TokenProxy;
import uz.pdp.clickuzpayments.security.ClickUzAuthentication;
import uz.pdp.clickuzpayments.service.impl.PaymentServiceImpl;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@NonNullApi
public class JwtTokenFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    public static String TOKEN = "token";

    private final TokenProxy tokenProxy;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        TOKEN = token;
        if (token != null && token.startsWith(BEARER)) {
            token = token.split(" ")[1];
            CustomResponseEntity<ClickUzAuthentication> verify = tokenProxy.verify(token);
            if (verify.getBody() != null)
                SecurityContextHolder.getContext().setAuthentication(
                    ClickUzAuthentication.cast(verify.getBody())
                );
        }
        filterChain.doFilter(request, response);
    }
}
