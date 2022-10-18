package dmit2015.repository;

import common.jpa.AbstractJpaRepository;
import dmit2015.entity.Course;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CourseRepository extends AbstractJpaRepository<Course, Integer> {

    public CourseRepository() {
        super(Course.class);
    }

}