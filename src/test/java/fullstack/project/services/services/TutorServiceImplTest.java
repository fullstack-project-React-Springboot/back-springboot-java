package fullstack.project.services.services;

import fullstack.project.services.entities.Intern;
import fullstack.project.services.entities.Tutor;
import fullstack.project.services.repositories.TutorRepository;
import fullstack.project.services.strings.values.Values;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TutorServiceImplTest {

    @Mock
    private TutorRepository tutorRepository;
    @InjectMocks
    private TutorServiceImpl tutorServiceImpl;

    private Tutor buildTutor() {
        var email = "blabla@gmail.com";
        var tutor = new Tutor();
        tutor.setEmail(email);
        return tutor;
    }

    @Test
    void save() {
        var tutor = buildTutor();
        when(tutorRepository.findByEmail(tutor.getEmail())).thenReturn(Optional.empty());
        when(tutorRepository.save(any(Tutor.class))).thenReturn(tutor);
        var savedTutor = tutorServiceImpl.save(tutor);
        assertThat(savedTutor).isNotNull()
                .returns(tutor.getEmail(), Intern::getEmail);
        verify(tutorRepository, times(1)).findByEmail(any(String.class));
    }

    @Test
    void save_IfAlreadyExists() {
        var tutor = buildTutor();
        when(tutorRepository.findByEmail(tutor.getEmail())).thenReturn(Optional.of(tutor));
        var exception = assertThrows(EntityExistsException.class, () -> tutorServiceImpl.save(tutor));
        var expectedMessage = tutor.getEmail() + " " + Values.ALREADY_REGISTER;
        var actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
        verify(tutorRepository, times(1)).findByEmail(any(String.class));
    }

    @Test
    void update() {
        var tutor = buildTutor();
        when(tutorRepository.findByEmail(any(String.class))).thenReturn(Optional.of(tutor));
        when(tutorRepository.save(any(Tutor.class))).thenReturn(tutor);
        var updatedTutor = tutorServiceImpl.update(tutor);
        assertThat(updatedTutor).isNotNull()
                .returns(tutor.getEmail(), Intern::getEmail);
        verify(tutorRepository, times(1)).findByEmail(any(String.class));
        verify(tutorRepository, times(1)).save(any(Tutor.class));
    }

    @Test
    void update_IfNotExist() {
        var tutor = buildTutor();
        when(tutorRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        var exception = assertThrows(EntityExistsException.class, () -> tutorServiceImpl.update(tutor));
        var expectedMessage = tutor.getEmail() + " " + Values.DO_NOT_EXIST;
        var actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    void findByEmail() {
        var tutor = buildTutor();
        when(tutorRepository.findByEmail(any(String.class))).thenReturn(Optional.of(tutor));
        var foundTutor = tutorServiceImpl.findByEmail(tutor.getEmail());
        assertThat(foundTutor).isNotNull()
                .returns(tutor.getEmail(), Intern::getEmail);
        verify(tutorRepository, times(1)).findByEmail(any(String.class));
    }

    @Test
    void findByEmail_IfNotFound() {
        var tutor = buildTutor();
        when(tutorRepository.findByEmail(tutor.getEmail())).thenReturn(Optional.empty());
        var exception = assertThrows(EntityNotFoundException.class, () -> tutorServiceImpl.findByEmail(tutor.getEmail()));
        var expectedMessage = tutor.getEmail() + " " + Values.NOT_FOUND_MESSAGE;
        var actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
        verify(tutorRepository, times(1)).findByEmail(any(String.class));
    }
}
