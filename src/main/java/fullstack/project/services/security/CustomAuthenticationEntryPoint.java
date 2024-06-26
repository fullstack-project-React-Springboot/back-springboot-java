package fullstack.project.services.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import fullstack.project.services.entities.ErrorResponse;
import fullstack.project.services.strings.values.Values;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter()
                .write(objectMapper
                        .writeValueAsString(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),Values.AUTHENTICATION_FAILED + ", " + authException.getMessage()))
                );
        response.getWriter().flush();
        response.getWriter().close();
    }
}
