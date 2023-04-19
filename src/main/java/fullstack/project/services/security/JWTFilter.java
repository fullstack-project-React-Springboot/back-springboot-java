package fullstack.project.services.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import fullstack.project.services.entities.UserPrincipal;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import fullstack.project.services.strings.values.Values;
import java.io.IOException;

@Component
@Getter
@Setter
public class JWTFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;

    public JWTFilter(UserDetailsService userDetailsService, JWTUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, JWTVerificationException {
        try{
            jwtUtil.getToken(request).ifPresent(token -> {
                String email = jwtUtil.retrieveEmail(token);
                UserPrincipal userDetails = (UserPrincipal) userDetailsService.loadUserByUsername(email);
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            });
            filterChain.doFilter(request, response);
        }catch (JWTVerificationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(Values.EXPIRED_TOKEN);
            response.getWriter().flush();
            response.getWriter().close();
        }
    }
}
