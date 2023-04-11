package fullstack.project.services.services;

import fullstack.project.services.entities.Tutor;
import fullstack.project.services.entities.UserPrincipal;
import fullstack.project.services.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final TutorRepository tutorRepository;

    @Autowired
    public UserDetailsServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Tutor tutor = tutorRepository.findByEmail(email);
        if(tutor == null)
            throw new UsernameNotFoundException("User not found");
        return UserPrincipal.create(tutor);
    }
}
