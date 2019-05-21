package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Student;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.StudentsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@ViewScoped
@Named
@Getter
@Setter
public class EditStudent implements Serializable {
    private Student student;

    @Inject
    private StudentsDAO studentsDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));

        this.student = studentsDAO.findOne(studentId);
    }

    @Transactional
    @LoggedInvocation
    public String update() {
        try {
            studentsDAO.update(this.student);
        } catch (OptimisticLockException e) {
            return "/student?faces-redirect=true&studentId=" + this.student.getId() + "&error=optimistic-lock-exception";
        }

        return "/student?faces-redirect=true&studentId=" + this.student.getId();
    }
}
