package fullstack.project.services.entities;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationResponse (
        @NotBlank(message = "email should not be blank")
        String email,
        String token
) {}
