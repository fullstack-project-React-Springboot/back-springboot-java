package fullstack.project.services.controllers;

import fullstack.project.services.dtos.TutorDTO;
import fullstack.project.services.entities.Tutor;
import fullstack.project.services.mappers.TutorDTOMapper;
import fullstack.project.services.services.TutorService;

import fullstack.project.services.strings.routes.Routes;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static fullstack.project.services.services.TutorServiceImpl.getAuthenticatedTutorEmail;

@RestController
@RequestMapping(Routes.TUTOR_BASE_URL)
public class TutorController {

    private final TutorService tutorService;
    private final PasswordEncoder passwordEncoder;

    public TutorController(TutorService tutorService, PasswordEncoder passwordEncoder) {
        this.tutorService = tutorService;
        this.passwordEncoder = passwordEncoder;
    }

    @PutMapping
    public TutorDTO updateTutor(@RequestBody TutorDTO tutorDTO) {
        Tutor tutor = tutorService.findByEmail(tutorDTO.getEmail());
        return TutorDTOMapper
                .toDTO(tutorService.update(TutorDTOMapper.toTutor(tutorDTO, tutor)));
    }

    @GetMapping
    public TutorDTO getTutor() {
        return TutorDTOMapper.toDTO(tutorService.findByEmail(getAuthenticatedTutorEmail()));
    }

    @PostMapping(Routes.REGISTER)
    public TutorDTO registerTutor(@RequestBody Tutor tutor) {
        String encodedPassword = passwordEncoder.encode(tutor.getPassword());
        tutor.setPassword(encodedPassword);
        return TutorDTOMapper.toDTO(tutorService.save(tutor));
    }
}
