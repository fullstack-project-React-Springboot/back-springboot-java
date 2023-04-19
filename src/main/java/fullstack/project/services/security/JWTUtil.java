package fullstack.project.services.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import fullstack.project.services.strings.values.Values;

import java.time.*;
import java.util.Optional;

@Component
public class JWTUtil {
    private final String secret = Values.SECRET;
    public String generateToken(String email) throws IllegalArgumentException {
        OffsetDateTime now = OffsetDateTime.now();
        int validityDuration = 30;
        return JWT.create()
                .withSubject(Values.USER_DETAILS)
                .withClaim(Values.EMAIL, email)
                .withIssuedAt(now.toInstant())
                .withExpiresAt(now.plusMinutes(validityDuration).toInstant())
                .withIssuer(Values.APPLICATION_NAME)
                .sign(Algorithm.HMAC256(secret));
    }

    public String retrieveEmail(String token) throws JWTVerificationException {
        DecodedJWT jwtDecoded = jwtDecoder(token);
        return jwtDecoded.getClaim(Values.EMAIL).asString();
    }

    private DecodedJWT jwtDecoder(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(Values.USER_DETAILS)
                .withIssuer(Values.APPLICATION_NAME)
                .build();
        return verifier.verify(token);
    }

    public Optional<String> getToken(HttpServletRequest request) {
        String authHeader = request.getHeader(Values.AUTHORIZATION);
        return Optional.ofNullable(
                authHeader != null && authHeader.startsWith(Values.BEARER) ?
                        authHeader.substring(7) : null
        );
    }

}
