package fullstack.project.services.mappers;

import fullstack.project.services.dtos.StudentDTO;
import fullstack.project.services.entities.Student;
import fullstack.project.services.entities.Tutor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudentDTOMapperTest {

    @Test
    void toDTO() {
        // Given
        var studentDTO = new StudentDTO(1L, "firstname", "lastname",
        "email", "promotion", List.of());

        var student = new Student(1L, "firstname", "lastname",
                "email", "promotion", List.of(), null);
        // When
        var result = StudentDTOMapper.toDTO(student);
        // Then
        assertThat(result).isEqualTo(studentDTO);

    }

    @Test
    void toStudent() {
        // Given
        var tutor = new Tutor(1L, "firstname", "lastname", "email",
                "password");
        var studentDTO = new StudentDTO(1L, "firstname", "lastname",
                "email", "promotion", List.of());

        var student = new Student(1L, "firstname", "lastname",
                "email", "promotion", List.of(), tutor);
        // When
        var result = StudentDTOMapper.toStudent(studentDTO, tutor);
        // Then
        assertThat(result).isEqualTo(student);
    }
}
