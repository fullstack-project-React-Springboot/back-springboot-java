package fullstack.project.services.entities;

public record AuthenticationResponse (
        String email,
        String token
) {}
