package fullstack.project.services.entities;


import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message = "email should not be blank")
        String email,
        @NotBlank(message = "password should not be blank")
        String password
) {}
