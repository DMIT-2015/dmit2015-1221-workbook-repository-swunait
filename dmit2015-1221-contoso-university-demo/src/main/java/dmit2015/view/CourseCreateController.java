package dmit2015.view;

import dmit2015.entity.Course;
import dmit2015.entity.Department;
import dmit2015.repository.CourseRepository;

import dmit2015.repository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Map;

@Named("currentCourseCreateController")
@RequestScoped
public class CourseCreateController {

    @Inject
    private CourseRepository _courseRepository;

    @Inject
    private DepartmentRepository _departmentRepository;

    @Getter
    private Map<String, Integer> departmentSelectionMap;

    @NotNull(message = "Department must be selected")
    @Getter @Setter
    private Integer selectedDepartmentId;

    @Getter
    private Course newCourse = new Course();

    @PostConstruct
    public void init() {
        departmentSelectionMap = _departmentRepository.selectItemMap();
    }

    public String onCreateNew() {
        String nextPage = "";
        try {
            Department selectedDepartment = _departmentRepository.getReference(selectedDepartmentId);
            newCourse.setDepartment(selectedDepartment);
            _courseRepository.add(newCourse);
            Messages.addFlashGlobalInfo("Create was successful. {0}", newCourse.getDepartmentId());
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Create was not successful. {0}", e.getMessage());
        }
        return nextPage;
    }

}