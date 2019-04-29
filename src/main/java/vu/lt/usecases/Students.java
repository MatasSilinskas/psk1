package vu.lt.usecases;

import lombok.Getter;
import vu.lt.entities.Student;
import vu.lt.persistence.StudentsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Model
public class Students implements Serializable {
    private List<Student> allStudents;

    @Inject
    private StudentsDAO studentsDAO;

    @Getter
    private Student studentToCreate = new Student();

    @PostConstruct
    public void init() {
        loadStudents();
    }

    public void loadStudents() {
        this.allStudents = studentsDAO.loadAll();
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }
}
