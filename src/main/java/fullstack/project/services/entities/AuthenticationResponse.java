package fullstack.project.services.entities;

public record AuthenticationResponse (
        String email,
        String token,
        String firstname,
        String lastname
) {}
