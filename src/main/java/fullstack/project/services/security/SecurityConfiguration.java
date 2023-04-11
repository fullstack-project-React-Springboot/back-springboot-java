package fullstack.project.services.security;

import fullstack.project.services.services.TokenRevocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.*;
import fullstack.project.services.strings.routes.Routes;
import fullstack.project.services.strings.values.Values;

@Configuration
public class SecurityConfiguration {

    private final JWTFilter filter;
    private final TokenRevocationService tokenRevocationService;

    @Autowired
    public SecurityConfiguration(JWTFilter filter, TokenRevocationService tokenRevocationService) {
        this.filter = filter;
        this.tokenRevocationService = tokenRevocationService;
        this.filter.setTokenRevocationService(tokenRevocationService);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors()
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(
                        Routes.LOGIN,
                        Routes.REGISTER
                )
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .userDetailsService(filter.getUserDetailsService())
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Values.UNAUTHORIZED)
                )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .logout()
                .logoutUrl(Routes.LOGOUT)
                .addLogoutHandler((request, response, auth) -> {
                    String token = filter.getJwtUtil().getToken(request);
                    if(token != null) {
                        tokenRevocationService.revokeToken(token);
                        SecurityContextHolder.clearContext();
                    }
                })
                .logoutSuccessHandler((request, response, auth) -> {
                    response.setStatus(HttpServletResponse.SC_OK);
                    String token = filter.getJwtUtil().getToken(request);
                    if(tokenRevocationService.isTokenRevoked(token)) {
                        response.getWriter().write("Logged out successfully");
                        response.getWriter().flush();
                        response.getWriter().close();
                    }
                });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(filter.getUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }
}
