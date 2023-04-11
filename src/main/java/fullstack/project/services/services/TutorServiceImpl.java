package fullstack.project.services.services;


import fullstack.project.services.entities.Tutor;
import fullstack.project.services.repositories.TutorRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;

    @Autowired
    public TutorServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Tutor save(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor update(Tutor tutor) throws EntityExistsException{
        if(!tutorRepository.existsById(tutor.getId()))
            throw new EntityExistsException("Tutor " + tutor.getId() + " doesn't exist");
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor findByEmail(String email) throws EntityNotFoundException {
        return tutorRepository.findByEmail(email);
    }

}
