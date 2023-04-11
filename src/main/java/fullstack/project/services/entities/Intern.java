package fullstack.project.services.entities;

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
    protected long id;
    @Column(name = "firstname", nullable = false)
    protected String firstname;
    @Column(name = "lastname", nullable = false)
    protected String lastname;
    @Column(name = "email", nullable = false)
    protected String email;

}
