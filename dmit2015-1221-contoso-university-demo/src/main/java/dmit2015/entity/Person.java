package dmit2015.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "ContosoUniversity", name = "Person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Discriminator", discriminatorType = DiscriminatorType.STRING, length = 50)
@Getter @Setter
public abstract class Person {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PersonID", nullable = false)
    private Integer id;

    @NotBlank(message = "Last Name value is required")
    @Column(name = "LastName", nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "First Name value is required")
    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Transient
    public String getFullName() {
        return lastName + ", " + firstName;
    }

}