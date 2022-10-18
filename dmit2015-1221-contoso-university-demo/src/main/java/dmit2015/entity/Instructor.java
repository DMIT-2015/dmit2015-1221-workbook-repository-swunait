package dmit2015.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@DiscriminatorValue("Instructor")
@Getter @Setter
public class Instructor extends Person implements Serializable {

    @Column(name = "HireDate", nullable = true)
    private LocalDate hireDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CourseInstructor",
            joinColumns = @JoinColumn(name = "PersonID", referencedColumnName = "PersonID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID", referencedColumnName = "CourseID")
    )
    private Set<Course> courses;

    @OneToOne(mappedBy = "instructor", cascade = CascadeType.REMOVE)
    private OfficeAssignment officeAssignment;

}