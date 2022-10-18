package dmit2015.repository;

import common.jpa.AbstractJpaRepository;
import dmit2015.entity.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class StudentRepository extends AbstractJpaRepository<Student, Integer> {

    public StudentRepository() {
        super(Student.class);
    }

    public Optional<Student> findByIdIncludeEnrollments(int studentId) {
        Optional<Student> optionQuerySingleResult;

        try {
            Student querySingleResult = getEntityManager().createQuery(
                            "SELECT s FROM Student s LEFT JOIN FETCH s.enrollments WHERE s.id = :idValue", Student.class)
                    .setParameter("idValue", studentId)
                    .getSingleResult();
            optionQuerySingleResult = Optional.of(querySingleResult);
        } catch (NoResultException e) {
            optionQuerySingleResult = Optional.empty();
        }

        return optionQuerySingleResult;
    }

    public List<Student> findByName(String partialName) {
        return getEntityManager().createQuery("""
            SELECT s
            FROM Student s
            WHERE s.lastName LIKE :filter OR s.firstName LIKE :filter
            """, Student.class)
                .setParameter("filter", "%" + partialName + "%")
                .getResultList();
    }
}