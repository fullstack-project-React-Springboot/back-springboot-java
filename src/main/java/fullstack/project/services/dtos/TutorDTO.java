package fullstack.project.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TutorDTO {
    private long id;
    private String email;
    private String firstname;
    private String lastname;
}
