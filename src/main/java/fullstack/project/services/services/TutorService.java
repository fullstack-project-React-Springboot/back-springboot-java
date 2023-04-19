package fullstack.project.services.services;


import fullstack.project.services.entities.Tutor;
import jakarta.persistence.*;

public interface TutorService {
    Tutor save(Tutor tutor) throws EntityExistsException;
    Tutor update(Tutor tutor) throws EntityExistsException;
    Tutor findByEmail(String email) throws EntityNotFoundException;
}
