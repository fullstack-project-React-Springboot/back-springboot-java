package fullstack.project.services.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import fullstack.project.services.entities.UserPrincipal;
import fullstack.project.services.services.TokenRevocationService;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public JWTFilter(UserDetailsService userDetailsService, JWTUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    private TokenRevocationService tokenRevocationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.getToken(request);
        if(token != null) {
            try {
                String email = jwtUtil.validateTokenAndRetrieveSubject(token);
                UserPrincipal userDetails = (UserPrincipal) userDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } catch (JWTVerificationException | EntityNotFoundException exc) {
                if(!response.isCommitted())
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, Values.INVALID_JWT_TOKEN);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
