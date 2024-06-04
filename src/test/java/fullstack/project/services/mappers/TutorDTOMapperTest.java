package fullstack.project.services.mappers;

import fullstack.project.services.dtos.TutorDTO;
import fullstack.project.services.entities.Tutor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TutorDTOMapperTest {

    @Test
    void toDTO() {
        // Given
        var tutorDTO = new TutorDTO(1L, "email", "firstname", "lastname");
        var tutor = new Tutor(1L, "firstname", "lastname", "email", "password");
        // When
        var expectedTutorDto = TutorDTOMapper.toDTO(tutor);
        // Then
        assertThat(expectedTutorDto).isEqualTo(tutorDTO);
    }

    @Test
    void toTutor() {
        // Given
        var tutorDTO = new TutorDTO(1L, "email", "firstName", "lastname");
        var tutor = new Tutor(1L, "email", "firstName", "lastname", "password");
        // When
        var expectedTutor = TutorDTOMapper.toTutor(tutorDTO, tutor);
        // Then
        assertThat(expectedTutor).isEqualTo(tutor);
    }
}
