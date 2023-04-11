package fullstack.project.services.dtos;

import fullstack.project.services.entities.StudentInternship;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class StudentDTO {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String promotion;
    private List<StudentInternship> studentInternship;
}
