package fullstack.project.services.commands;

import com.github.javafaker.Faker;
import fullstack.project.services.entities.Student;
import fullstack.project.services.entities.StudentInternship;
import fullstack.project.services.entities.Tutor;
import fullstack.project.services.repositories.StudentRepository;
import fullstack.project.services.repositories.TutorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.IntStream;

@Component
@Slf4j
public class DefaultDataLoader implements CommandLineRunner {
    private final TutorRepository tutorRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final Faker faker = new Faker();

    public DefaultDataLoader(TutorRepository tutorRepository, StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.tutorRepository = tutorRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) {
        Tutor defaultTutor = new Tutor(1, "default", "default", "default.default@gmail.com", "123456");
        defaultTutor.setPassword(passwordEncoder.encode(defaultTutor.getPassword()));
        tutorRepository.findByEmail(defaultTutor.getEmail())
                .ifPresentOrElse(dbTutor -> log.info("Default tutor already settled up")
                        , () -> {
                    tutorRepository.save(defaultTutor);
                    studentRepository.saveAll(generateStudents(defaultTutor));
                    log.info("Default tutor set up");
                });
    }
    private List<StudentInternship> generateStudentInternships() {
        LocalDate startingDate = faker.date()
                .between(Date.valueOf(LocalDate.of(2007,1, 1)), Date.valueOf(LocalDate.now()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        log.info("Generating a list of internships...");
        return IntStream
                .rangeClosed(1, 2)
                .mapToObj(i ->
                        new StudentInternship(
                                faker.number().randomNumber(),
                                faker.business().toString(),
                                faker.company().name(),
                                faker.name().lastName(),
                                startingDate.toString(),
                                faker.date().between(Date.valueOf(startingDate), Date.valueOf(LocalDate.now().plusYears(1))).toString(),
                                faker.lorem().paragraph(1),
                                faker.address().fullAddress())
                )
                .toList();
    }
    private List<Student> generateStudents(Tutor tutor) {
        int startYear = faker.number().numberBetween(2000, 2022);
        LocalDate startingDate = LocalDate.of(startYear, 9, 1);
        log.info("Generating a list of students...");
        return IntStream.rangeClosed(1, 30)
                .mapToObj(i -> new Student(
                        i,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.date()
                                .between(Date.valueOf(startingDate), Date.valueOf(LocalDate.now().plusYears(5)))
                                .toString(),
                        generateStudentInternships(),
                        tutor
                        )
                )
                .toList();
    }
}
