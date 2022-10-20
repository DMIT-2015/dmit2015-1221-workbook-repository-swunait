package dmit2015.view;

import dmit2015.entity.Enrollment;
import dmit2015.entity.Student;
import dmit2015.repository.EnrollmentRepository;
import dmit2015.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named("currentStudentQueryDetailsController")
@ViewScoped
public class StudentQueryDetailsController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private StudentRepository _studentRepository;

    private Integer editId;

    @Getter
    private Student existingStudent;

    @Inject
    private EnrollmentRepository _enrollmentRepository;

    @PostConstruct
    public void init() {
    }

    @Getter @Setter
    private String selectedStudentName;

    public List<String> completeText(String query) {
        var queryResultList = _studentRepository.findByName(query);
        var nameList = new ArrayList<String>();
        for (var currentStudent : queryResultList) {
            nameList.add(currentStudent.getFullName());
        }
        return nameList;
    }


    public List<Enrollment> fetchEnrollments() {
        return _enrollmentRepository.findByStudentIdIncludeCourse(existingStudent.getId());
    }
}