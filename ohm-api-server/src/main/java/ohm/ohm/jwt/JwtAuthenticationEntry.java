package ohm.ohm.jwt;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//유효한 자격증명을 제공하지 않고 접근하려 할때 401 Unauthorized 에러를 리턴할 클래스이다.(401에러)
@Component
public class JwtAuthenticationEntry implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401(인증 실패)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
