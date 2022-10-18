package dmit2015.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(schema = "ContosoUniversity")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "CourseID", nullable = false)
    private Integer courseId;

    @Column(name = "Title", nullable = false, length = 100)
    private String title;

    @Column(name = "Credits", nullable = false)
    private Integer credits;

    @Column(name = "DepartmentID", nullable = false, insertable = false, updatable = false)
    private Integer departmentId;


    @ManyToOne
    @JoinColumn(name = "DepartmentID", referencedColumnName = "DepartmentID", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "course")
    private Collection<Enrollment> enrollments;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private Set<Instructor> instructors;


    @OneToOne(mappedBy = "course")

    private OnlineCourse onlineCourse;

    @OneToOne(mappedBy = "course")
    private OnsiteCourse onsiteCourse;

}