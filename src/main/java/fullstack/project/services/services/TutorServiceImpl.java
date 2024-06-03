package fullstack.project.services.services;


import fullstack.project.services.entities.Tutor;
import fullstack.project.services.repositories.TutorRepository;
import fullstack.project.services.strings.values.Values;
import jakarta.persistence.*;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;

    public TutorServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Tutor save(Tutor tutor) throws EntityExistsException {
        if (tutorRepository.findByEmail(tutor.getEmail()).isPresent())
            throw new EntityExistsException(tutor.getEmail() + " " + Values.ALREADY_REGISTER);
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor update(Tutor tutor) throws EntityExistsException {
        if (tutorRepository.findByEmail(tutor.getEmail()).isEmpty()) {
            throw new EntityExistsException(tutor.getEmail() + " " + Values.DO_NOT_EXIST);
        }
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor findByEmail(String email) throws EntityNotFoundException {
        return tutorRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(email + " " + Values.NOT_FOUND_MESSAGE));
    }

    public static String getAuthenticatedTutorEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated())
            throw new AuthenticationCredentialsNotFoundException(Values.NOT_FOUND_MESSAGE);
        return authentication.getName();
    }

}
