package fullstack.project.services.mappers;

import fullstack.project.services.dtos.TutorDTO;
import fullstack.project.services.entities.Tutor;

public class TutorDTOMapper {
    public static TutorDTO toDTO(Tutor tutor) {
        return new TutorDTO(
                tutor.getId(),
                tutor.getEmail(),
                tutor.getFirstname(),
                tutor.getLastname()
        );
    }
    public static Tutor toTutor(TutorDTO tutorDTO, Tutor tutor) {
        return new Tutor(
                tutorDTO.id(),
                tutorDTO.email(),
                tutorDTO.firstname(),
                tutorDTO.lastname(),
                tutor.getPassword(),
                tutor.getStudents()
        );
    }
}
