package fullstack.project.services.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tutor")
public class Tutor extends Intern{

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor_id")
    private List<Student> students = new ArrayList<>();

    public Tutor(long id, String firstname, String lastname, String email,
                 String password) {
        super(id, firstname, lastname, email);
        this.password = password;
    }
}
