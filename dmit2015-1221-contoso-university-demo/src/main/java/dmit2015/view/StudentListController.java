package dmit2015.view;

import dmit2015.entity.Student;
import dmit2015.repository.StudentRepository;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Named("currentStudentListController")
@ViewScoped
public class StudentListController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private StudentRepository _studentRepository;

    @Getter
    private List<Student> studentList;

    @Getter @Setter
    private String currentFilter;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            studentList = _studentRepository.findAll();
        } catch (Exception ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public void doSearch() {
        if (!currentFilter.isBlank()) {
//            studentList = _studentRepository.findByName(currentFilter);
            studentList = studentList
                    .stream()
                    .filter(student -> student.getLastName().contains(currentFilter) || student.getFirstName().contains(currentFilter))
                    .toList();
        }
    }
}