package fullstack.project.services.services;


import fullstack.project.services.entities.Student;
import fullstack.project.services.repositories.StudentRepository;
import fullstack.project.services.strings.values.Values;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }
    @Override
    public List<Student> findAllByTutorEmail(String tutorEmail) {
        return studentRepository.findAllByTutorEmail(tutorEmail);
    }
    @Override
    public Student findById(long studentId) throws EntityNotFoundException {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student " + studentId + " " + Values.NOT_FOUND_MESSAGE));
    }

    @Override
    public Student update(Student student) throws EntityExistsException {
        if(!studentRepository.existsById(student.getId()))
            throw new EntityExistsException(student.getEmail() + " " + Values.DO_NOT_EXIST);
        return studentRepository.save(student);
    }
    @Override
    public boolean deleteById(long studentId) throws EntityExistsException {
        if(!studentRepository.existsById(studentId))
            throw new EntityExistsException("Student " + studentId + " " + Values.DO_NOT_EXIST);
        studentRepository.deleteById(studentId);
        return !studentRepository.existsById(studentId);
    }

    @Override
    public List<Student> saveAll(List<Student> students) {
        return studentRepository.saveAll(students);
    }
}
