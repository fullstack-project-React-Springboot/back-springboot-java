package fullstack.project.services.controllers;

import fullstack.project.services.entities.AuthenticationRequest;
import fullstack.project.services.entities.AuthenticationResponse;
import fullstack.project.services.entities.UserPrincipal;
import fullstack.project.services.security.JWTUtil;
import fullstack.project.services.strings.routes.Routes;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping(Routes.LOGIN)
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()
                )
        );
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthenticationResponse(user.getUsername(), token);
    }
}
