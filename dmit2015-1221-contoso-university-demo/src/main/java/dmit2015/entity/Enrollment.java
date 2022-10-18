package dmit2015.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(schema = "ContosoUniversity", name = "StudentGrade")
@Getter
@Setter
public class Enrollment implements Serializable {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "EnrollmentID", nullable = false)
    private Integer enrollmentId;

    @Column(name = "CourseID", nullable = false, insertable = false, updatable = false)
    private Integer courseId;

    @Column(name = "StudentID", nullable = false, insertable = false, updatable = false)
    private Integer studentId;

    @Column(name = "Grade", nullable = true, precision = 2)
    private BigDecimal grade;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CourseID", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentID", referencedColumnName = "PersonID", nullable = false)
    private Student student;

}