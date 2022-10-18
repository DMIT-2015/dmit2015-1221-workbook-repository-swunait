package dmit2015.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(schema = "ContosoUniversity")
@Getter @Setter
public class Department implements Serializable {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DepartmentID", nullable = false)
    private Integer departmentId;

    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "Budget", nullable = false)
    private BigDecimal budget;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "Administrator")
    private Integer instructorId;

    @ManyToOne()
    @JoinColumn(name = "Administrator", referencedColumnName = "PersonID", insertable = false, updatable = false)
    private Instructor administrator;

    @OneToMany(mappedBy = "department")
    private Collection<Course> courses;

}