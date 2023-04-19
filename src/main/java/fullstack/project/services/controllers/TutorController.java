package fullstack.project.services.controllers;
import fullstack.project.services.dtos.TutorDTO;
import fullstack.project.services.entities.Tutor;
import fullstack.project.services.mappers.TutorDTOMapper;
import fullstack.project.services.services.TutorService;

import org.springframework.web.bind.annotation.*;

import static fullstack.project.services.services.TutorServiceImpl.getAuthenticatedTutorEmail;

@RestController
public class TutorController {
    private final TutorService tutorService;
    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }
    @PutMapping("/tutors")
    public TutorDTO updateTutor(@RequestBody TutorDTO tutorDTO) {
        Tutor t = tutorService.findByEmail(tutorDTO.getEmail());
        tutorDTO.setId(t.getId());
        return TutorDTOMapper
                .toDTO(tutorService.update(TutorDTOMapper.toTutor(tutorDTO)));
    }
    @GetMapping("/")
    public TutorDTO getTutor() {
        return TutorDTOMapper.toDTO(tutorService.findByEmail(getAuthenticatedTutorEmail()));
    }
}
