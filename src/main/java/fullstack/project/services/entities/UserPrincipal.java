package fullstack.project.services.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class UserPrincipal implements UserDetails {
    private Tutor tutor;
    public static UserPrincipal create(Tutor tutor) {return new UserPrincipal(tutor);}
    @Override
    public String getPassword() {
        return tutor.getPassword();
    }

    @Override
    public String getUsername() {
        return tutor.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
