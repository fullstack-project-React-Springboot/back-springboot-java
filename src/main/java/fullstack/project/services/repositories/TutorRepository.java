package fullstack.project.services.repositories;

import fullstack.project.services.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Tutor findByEmail(String email);
}
