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
          student.getStudentInternship());
    }

    public static Student toStudent(StudentDTO studentDTO, Tutor tutor) {
        return new Student(
                studentDTO.getId(),
                studentDTO.getFirstname(),
                studentDTO.getLastname(),
                studentDTO.getEmail(),
                studentDTO.getPromotion(),
                studentDTO.getStudentInternship(),
                tutor);
    }
}
