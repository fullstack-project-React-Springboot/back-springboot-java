package fullstack.project.services.controllers;

import fullstack.project.services.dtos.StudentDTO;
import fullstack.project.services.entities.Student;
import fullstack.project.services.entities.Tutor;
import fullstack.project.services.mappers.StudentDTOMapper;
import fullstack.project.services.services.StudentService;
import fullstack.project.services.services.TutorService;
import fullstack.project.services.strings.routes.Routes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fullstack.project.services.services.TutorServiceImpl.getAuthenticatedTutorEmail;

@RestController
@RequestMapping(Routes.STUDENT_BASE_URL)
public class StudentController {
    private final StudentService studentService;
    private final TutorService tutorService;

    public StudentController(StudentService studentService, TutorService tutorService) {
        this.studentService = studentService;
        this.tutorService = tutorService;
    }

    @GetMapping("/{studentId}")
    public StudentDTO findStudentById(@PathVariable long studentId) {
        return StudentDTOMapper.toDTO(studentService.findById(studentId));
    }

    @GetMapping
    public List<StudentDTO> findAllStudents() {
        return studentService.findAllByTutorEmail(getAuthenticatedTutorEmail())
                .stream()
                .map(StudentDTOMapper::toDTO)
                .toList();
    }

    @PostMapping
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        Tutor tutor = tutorService.findByEmail(getAuthenticatedTutorEmail());
        Student s = studentService.save(StudentDTOMapper.toStudent(studentDTO, tutor));
        return StudentDTOMapper.toDTO(s);
    }

    @PutMapping("/{studentId}")
    public StudentDTO updateStudent(@PathVariable long studentId, @RequestBody StudentDTO studentDTO) {
        Tutor tutor = tutorService.findByEmail(getAuthenticatedTutorEmail());
        studentDTO.setId(studentId);
        Student s = studentService.update(StudentDTOMapper.toStudent(studentDTO, tutor));
        return StudentDTOMapper.toDTO(s);
    }

    @DeleteMapping("/{studentId}")
    public boolean deleteStudent(@PathVariable long studentId) {
        return studentService.deleteById(studentId);
    }
}
