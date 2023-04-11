package fullstack.project.services.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import fullstack.project.services.strings.values.Values;

import java.util.Date;

@Component
public class JWTUtil {
    private final String secret = Values.SECRET;
    public String generateToken(String email) throws IllegalArgumentException {
        Date currentDate = new Date();
        int validityDuration = 30;
        Date expirationDate = new Date(currentDate.getTime() + validityDuration * 60 * 1000);
        return JWT.create()
                .withSubject(Values.USER_DETAILS)
                .withClaim(Values.EMAIL, email)
                .withIssuedAt(currentDate)
                .withExpiresAt(expirationDate)
                .withIssuer(Values.APPLICATION_NAME)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(Values.USER_DETAILS)
                .withIssuer(Values.APPLICATION_NAME)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(Values.EMAIL).asString();
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader(Values.AUTHORIZATION);
        return authHeader != null && authHeader.startsWith(Values.BEARER) ?
            authHeader.substring(7) : null;
    }

}
