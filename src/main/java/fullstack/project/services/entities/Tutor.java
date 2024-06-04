package fullstack.project.services.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
public class Tutor extends Intern {

    @Size(min = 4, message = "password should have at least 4 characters")
    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor_id")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    public Tutor(long id, String firstname, String lastname, String email, String password) {
        super(id, firstname, lastname, email);
        this.password = password;
    }
    public Tutor(long id, String email, String firstname, String lastname, String password, List<Student> students) {
        super(id, firstname, lastname, email);
        this.password = password;
        this.students = students;
    }
}
