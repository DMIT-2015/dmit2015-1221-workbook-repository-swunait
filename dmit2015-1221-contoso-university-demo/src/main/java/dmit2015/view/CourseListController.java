package dmit2015.view;

import dmit2015.entity.Course;
import dmit2015.repository.CourseRepository;
import lombok.Getter;
import org.omnifaces.util.Messages;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Named("currentCourseListController")
@ViewScoped
public class CourseListController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private CourseRepository _courseRepository;

    @Getter
    private List<Course> courseList;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            courseList = _courseRepository.findAll();
        } catch (Exception ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
}