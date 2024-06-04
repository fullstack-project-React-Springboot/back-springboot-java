package fullstack.project.services.dtos;

import jakarta.validation.constraints.NotBlank;



public record TutorDTO (
    long id,
    @NotBlank(message = "email should not be blank")
    String email,
    @NotBlank(message = "firstname should not be blank")
    String firstname,
    @NotBlank(message = "lastname should not be blank")
    String lastname
){}
