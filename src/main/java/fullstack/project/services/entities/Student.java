package fullstack.project.services.entities;

import jakarta.persistence.*;
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

    @Column(name = "promotion", nullable = false)
    private String promotion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_internship_id")
    private List<StudentInternship> studentInternship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public Student(long id, String firstname, String lastname, String email,
                   String promotion, List<StudentInternship> studentInternship, Tutor tutor) {
        super(id, firstname, lastname, email);
        this.promotion = promotion;
        this.studentInternship = studentInternship;
        this.tutor = tutor;
    }
}
