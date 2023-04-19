package fullstack.project.services.repositories;

import fullstack.project.services.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByTutorEmail(String tutorEmail);
}
