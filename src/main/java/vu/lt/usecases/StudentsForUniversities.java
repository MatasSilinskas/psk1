package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Student;
import vu.lt.entities.University;
import vu.lt.persistence.StudentsDAO;
import vu.lt.persistence.UniversitiesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class StudentsForUniversities implements Serializable {
    @Inject
    private UniversitiesDAO universitiesDAO;

    @Inject
    private StudentsDAO studentsDAO;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @Getter @Setter
    private University university;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer universityId = Integer.parseInt(requestParameters.get("universityId"));
        this.university = universitiesDAO.findOne(universityId);
    }

    @Transactional
    public String createStudent() {
        studentToCreate.setUniversity(university);
        this.studentsDAO.persist(studentToCreate);
        return "universities?faces-redirect=true&universityId=" + this.university.getId();
    }
}
