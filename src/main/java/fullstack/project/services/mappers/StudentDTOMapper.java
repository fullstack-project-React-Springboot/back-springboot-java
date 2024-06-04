package fullstack.project.services.mappers;

import fullstack.project.services.dtos.StudentDTO;
import fullstack.project.services.entities.Student;
import fullstack.project.services.entities.Tutor;

public class StudentDTOMapper {
    public static StudentDTO toDTO(Student student) {
        return new StudentDTO(
          student.getId(),
          student.getFirstname(),
          student.getLastname(),
          student.getEmail(),
          student.getPromotion(),
          student.getStudentInternships());
    }

    public static Student toStudent(StudentDTO studentDTO, Tutor tutor) {
        return new Student(
                studentDTO.id(),
                studentDTO.firstname(),
                studentDTO.lastname(),
                studentDTO.email(),
                studentDTO.promotion(),
                studentDTO.studentInternships(),
                tutor
        );
    }

    public static Student toStudent(StudentDTO studentDTO, Tutor tutor, long studentId) {
        return new Student(
                studentId,
                studentDTO.firstname(),
                studentDTO.lastname(),
                studentDTO.email(),
                studentDTO.promotion(),
                studentDTO.studentInternships(),
                tutor
        );
    }
}
