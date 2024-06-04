package fullstack.project.services.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@MappedSuperclass
public class Intern {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @NotBlank(message = "The firstname cannot be blank")
    @Column(name = "firstname", nullable = false)
    protected String firstname;
    @NotBlank(message = "The lastname cannot be blank")
    @Column(name = "lastname", nullable = false)
    protected String lastname;
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false)
    protected String email;

}
