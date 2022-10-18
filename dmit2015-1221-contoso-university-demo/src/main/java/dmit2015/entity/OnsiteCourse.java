package dmit2015.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(schema = "ContosoUniversity")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OnsiteCourse implements Serializable  {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "CourseID", nullable = false)
    private Integer courseId;
    @Column(name = "Location", nullable = false, length = 50)
    private String location;
    @Column(name = "Days", nullable = false, length = 50)
    private String days;
    @Column(name = "Time", nullable = false)
    private LocalTime time;


    @OneToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID", nullable = false)
    private Course course;

}