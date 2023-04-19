package fullstack.project.services.services;


import jakarta.persistence.*;
import fullstack.project.services.entities.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student save(Student student);
    List<Student> findAllByTutorEmail(String tutorEmail);
    Student findById(long studentId) throws EntityNotFoundException;
    Student update(Student student) throws EntityExistsException;
    boolean deleteById(long studentId) throws EntityExistsException;
    List<Student> saveAll(List<Student> students);
}
