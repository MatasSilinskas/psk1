package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Course;
import vu.lt.persistence.CoursesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Courses {
    @Inject
    private CoursesDAO coursesDAO;

    @Getter @Setter
    private Course courseToCreate = new Course();

    @Getter
    private List<Course> allCourses;

    @PostConstruct
    public void init() {
        loadAllCourses();
    }

    @Transactional
    public String createCourse() {
        this.coursesDAO.persist(courseToCreate);
        return "index?faces-redirect=true";
    }

    public void loadAllCourses() {
        this.allCourses = coursesDAO.loadAll();
    }

}
