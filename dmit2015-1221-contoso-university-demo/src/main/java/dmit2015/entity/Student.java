package dmit2015.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@DiscriminatorValue("Student")
//@NamedEntityGraph(
//        name = "Student.enrollments",
//        attributeNodes = @NamedAttributeNode(value = "enrollments", subgraph = "enrollments"),
//        subgraphs = @NamedSubgraph(name = "enrollments", attributeNodes = @NamedAttributeNode("course"))
//)
@Getter @Setter
public class Student extends Person implements Serializable {

    @Column(name = "EnrollmentDate", nullable = true)
    @NotNull(message = "Enrollment Date value is required")
    @PastOrPresent(message = "Enrollment Date must be in the past or present")
    private LocalDate enrollmentDate;

    @OneToMany(mappedBy = "student")
    private Collection<Enrollment> enrollments;

}