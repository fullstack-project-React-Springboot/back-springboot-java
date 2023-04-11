package fullstack.project.services.entities;

import java.util.List;

public record AuthenticationResponse (
        String email,
        String token,
        List<Student> studentList
        ) {}
