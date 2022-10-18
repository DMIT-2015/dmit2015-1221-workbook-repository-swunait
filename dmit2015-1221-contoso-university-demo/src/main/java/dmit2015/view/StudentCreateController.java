package dmit2015.view;

import dmit2015.entity.Student;
import dmit2015.repository.StudentRepository;

import lombok.Getter;
import org.omnifaces.util.Messages;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("currentStudentCreateController")
@RequestScoped
public class StudentCreateController {

    @Inject
    private StudentRepository _studentRepository;

    @Getter
    private Student newStudent = new Student();

    public String onCreateNew() {
        String nextPage = "";
        try {
            _studentRepository.add(newStudent);
            Messages.addFlashGlobalInfo("Create was successful. {0}", newStudent.getId());
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Create was not successful. {0}", e.getMessage());
        }
        return nextPage;
    }

}