package dmit2015.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(schema = "ContosoUniversity")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OfficeAssignment implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "InstructorID", nullable = false)
    private Integer instructorId;

    @Column(name = "Location", nullable = false, length = 50)
    private String location;

    @Column(name = "Timestamp", nullable = false)
    private LocalDateTime timestamp;

    @OneToOne
    @JoinColumn(name = "InstructorID", referencedColumnName = "PersonID", nullable = false)
    private Instructor instructor;

    @PrePersist
    private void beforePersist() {
        timestamp = LocalDateTime.now();
    }

    @PreUpdate
    private void beforeUpdate() {
        timestamp = LocalDateTime.now();
    }

}