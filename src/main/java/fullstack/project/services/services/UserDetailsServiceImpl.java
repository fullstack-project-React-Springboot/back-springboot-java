package fullstack.project.services.services;

import fullstack.project.services.entities.UserPrincipal;
import fullstack.project.services.repositories.TutorRepository;
import fullstack.project.services.strings.values.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final TutorRepository tutorRepository;

    public UserDetailsServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserPrincipal.create(
                tutorRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException(email + " " + Values.NOT_FOUND_MESSAGE)));
    }
}
