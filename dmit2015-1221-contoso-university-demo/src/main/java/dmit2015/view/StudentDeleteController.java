package dmit2015.view;

import dmit2015.entity.Student;
import dmit2015.repository.StudentRepository;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

@Named("currentStudentDeleteController")
@ViewScoped
public class StudentDeleteController implements Serializable {
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

    @PostConstruct
    public void init() {
        Optional<Student> optionalStudent = _studentRepository.findOptionalById(editId);
        if (optionalStudent.isPresent()) {
            existingStudent = optionalStudent.get();
        } else {
            Faces.redirect(Faces.getRequestURI().substring(0, Faces.getRequestURI().lastIndexOf("/")) + "/index.xhtml");
        }
    }

    public String onDelete() {
        String nextPage = "";
        try {
            _studentRepository.delete(existingStudent);
            Messages.addFlashGlobalInfo("Delete was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Delete not successful.");
        }
        return nextPage;
    }
}