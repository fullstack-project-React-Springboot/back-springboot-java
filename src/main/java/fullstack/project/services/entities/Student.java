package fullstack.project.services.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student extends Intern {

    @NotEmpty(message = "promotion cannot be empty")
    @Column(name = "promotion", nullable = false)
    private String promotion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_internship_id")
    private List<StudentInternship> studentInternships;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    @JsonIgnore
    private Tutor tutor;

    public Student(long id, String firstname, String lastname, String email, String promotion,
                   List<StudentInternship> studentInternships,
                   Tutor tutor
    ) {
        super(id, firstname, lastname, email);
        this.promotion = promotion;
        this.studentInternships = studentInternships;
        this.tutor = tutor;
    }
}
