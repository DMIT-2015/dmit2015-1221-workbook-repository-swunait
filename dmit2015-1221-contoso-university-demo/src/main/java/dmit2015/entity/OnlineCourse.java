package dmit2015.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(schema = "ContosoUniversity")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OnlineCourse implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "CourseID", nullable = false)
    private Integer courseId;

    @Column(name = "URL", nullable = false, length = 100)
    private String url;

    @OneToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID", nullable = false)
    private Course course;

}