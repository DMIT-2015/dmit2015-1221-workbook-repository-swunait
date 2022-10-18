package dmit2015.repository;

import common.jpa.AbstractJpaRepository;
import dmit2015.entity.Enrollment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class EnrollmentRepository extends AbstractJpaRepository<Enrollment, Integer> {

    public EnrollmentRepository() {
        super(Enrollment.class);
    }

    public List<Enrollment> findByStudentIdIncludeCourse(int studentId) {
        return getEntityManager().createQuery("""
                SELECT e 
                FROM Enrollment e JOIN FETCH e.course
                WHERE e.studentId = :studentId 
                ORDER BY e.course.title""", Enrollment.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }

    public List<Enrollment> findByCourseIdIncludeStudent(int courseId) {
        return getEntityManager().createQuery("""
                SELECT e 
                FROM Enrollment e JOIN FETCH e.student
                WHERE e.courseId = :courseId 
                ORDER BY e.student.lastName, e.student.firstName""", Enrollment.class)
                .setParameter("courseId", courseId)
                .getResultList();
    }

}