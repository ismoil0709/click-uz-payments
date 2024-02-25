package uz.pdp.clickuzpayments.security;

import com.fasterxml.jackson.core.ObjectCodec;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClickUzAuthentication{
    private Object principal;
    private Object credentials;
    private List<String> roles;
    public static UsernamePasswordAuthenticationToken cast(ClickUzAuthentication clickUzAuthentication){
        return new UsernamePasswordAuthenticationToken(
                clickUzAuthentication.getPrincipal(),
                clickUzAuthentication.getCredentials(),
                clickUzAuthentication.getRoles().stream().map(SimpleGrantedAuthority::new).toList());
    }
}
