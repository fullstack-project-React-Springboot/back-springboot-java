package fullstack.project.services.dtos;

import fullstack.project.services.entities.StudentInternship;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


public record StudentDTO (
        long id,
        @NotBlank(message = "firstname should not be blank")
        String firstname,
        @NotBlank(message = "lastname should not be blank")
        String lastname,
        @NotBlank(message = "email should not be blank")
        String email,
        @NotBlank(message = "promotion should not be blank")
        String promotion,
        List<StudentInternship> studentInternships
){}
