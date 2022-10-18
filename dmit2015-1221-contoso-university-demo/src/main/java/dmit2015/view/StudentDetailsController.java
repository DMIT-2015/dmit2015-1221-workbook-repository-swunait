package dmit2015.view;

import dmit2015.entity.Enrollment;
import dmit2015.entity.Student;
import dmit2015.repository.EnrollmentRepository;
import dmit2015.repository.StudentRepository;

import lombok.Getter;
import lombok.Setter;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Faces;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Named("currentStudentDetailsController")
@ViewScoped
public class StudentDetailsController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private StudentRepository _studentRepository;

    @Inject
    @ManagedProperty("#{param.editId}")
    @Getter
    @Setter
    private Integer editId;

    @Getter
    private Student existingStudent;

    @Inject
    private EnrollmentRepository _enrollmentRepository;

    @PostConstruct
    public void init() {
//        Optional<Student> optionalStudent = _studentRepository.findOptionalById(editId);
        Optional<Student> optionalStudent = _studentRepository.findByIdIncludeEnrollments(editId);
        if (optionalStudent.isPresent()) {
            existingStudent = optionalStudent.get();
        } else {
            Faces.redirect(Faces.getRequestURI().substring(0, Faces.getRequestURI().lastIndexOf("/")) + "/index.xhtml");
        }
    }

    public List<Enrollment> fetchEnrollments() {
        return _enrollmentRepository.findByStudentIdIncludeCourse(existingStudent.getId());
    }
}