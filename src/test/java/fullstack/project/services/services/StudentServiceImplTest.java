package fullstack.project.services.services;

import fullstack.project.services.entities.Student;
import fullstack.project.services.repositories.StudentRepository;
import fullstack.project.services.strings.values.Values;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student buildStudent() {
        var student = new Student();
        student.setId(1);
        return student;
    }

    @Test
    void testSave() {
        var student = buildStudent();
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        var savedStudent = studentService.save(student);

        assertThat(savedStudent)
                .isNotNull()
                .returns(1L, Student::getId);
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void findAllByTutorEmail() {

        var tutorEmail = "tutor@example.com";
        var student1 = new Student();
        var student2 = new Student();

        when(studentRepository.findAllByTutorEmail(tutorEmail)).thenReturn(List.of(student1, student2));

        var students = studentService.findAllByTutorEmail(tutorEmail);

        assertThat(students).asList().hasSize(2).contains(student1, student2);
        verify(studentRepository, times(1)).findAllByTutorEmail(tutorEmail);

    }

    @Test
    void findById() {
        var student = buildStudent();
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        Student foundStudent = studentService.findById(student.getId());

        assertThat(foundStudent).isEqualTo(student);
        verify(studentRepository, times(1)).findById(anyLong());
    }

    @Test
    void findById_IfNotFound() {

        long studentId = 1L;

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> studentService.findById(studentId));

        var expectedMessage = "Student " + studentId + " " + Values.NOT_FOUND_MESSAGE;
        var actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void update() {
        var student = buildStudent();
        when(studentRepository.existsById(anyLong())).thenReturn(true);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        var updatedStudent = studentService.update(student);

        assertThat(updatedStudent).isNotNull()
                .returns(student.getId(), Student::getId);
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void update_IfNotExist() {
        var student = buildStudent();
        when(studentRepository.existsById(anyLong())).thenReturn(false);
        var exception = assertThrows(EntityExistsException.class, () -> studentService.update(student));

        var expectedMessage = student.getEmail() + " " + Values.DO_NOT_EXIST;
        var actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void deleteById() {
        long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(true, false);
        boolean isDeleted = studentService.deleteById(studentId);
        assertThat(isDeleted).isTrue();
        verify(studentRepository, times(2)).existsById(anyLong());
    }

    @Test
    void deleteById_IfNotExist() {
        long studentId = 1L;
        when(studentRepository.existsById(anyLong())).thenReturn(false);

        Exception exception = assertThrows(EntityExistsException.class, () -> studentService.deleteById(studentId));

        String expectedMessage = "Student " + studentId + " " + Values.DO_NOT_EXIST;
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void saveAll() {
        var student1 = new Student();
        var student2 = new Student();
        var students = List.of(student1, student2);
        when(studentRepository.saveAll(students)).thenReturn(students);

        var savedStudents = studentService.saveAll(students);

        assertThat(savedStudents).asList().hasSize(2).contains(student1, student2);
        verify(studentRepository, times(1)).saveAll(students);
    }
}
