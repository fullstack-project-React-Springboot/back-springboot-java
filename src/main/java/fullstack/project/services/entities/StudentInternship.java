package fullstack.project.services.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_internship")
public class StudentInternship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "mission", nullable = false)
    private String mission;
    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "company_tutor_name", nullable = false)
    private String companyTutorName;
    @Column(name = "starting_date", nullable = false)
    private String startingDate;
    @Column(name = "ending_date", nullable = false)
    private String endingDate;
    @Column(name = "comment")
    private String comment;
    @Column(name = "company_address", nullable = false)
    private String companyAddress;
}
