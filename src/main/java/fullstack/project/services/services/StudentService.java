package fullstack.project.services.services;


import jakarta.persistence.*;
import fullstack.project.services.entities.Student;
import java.util.List;

public interface StudentService {
    Student save(Student student);
    List<Student> findAllByTutorId(long tutorId);
    Student udapte(Student student) throws EntityExistsException;
    boolean deleteById(long studentId);
    List<Student> saveAll(List<Student> students);
}
