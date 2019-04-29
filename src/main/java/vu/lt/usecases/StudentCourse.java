package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Course;
import vu.lt.entities.Student;
import vu.lt.persistence.CoursesDAO;
import vu.lt.persistence.StudentsDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class StudentCourse {

    @Inject
    private StudentsDAO studentsDAO;

    @Inject
    private CoursesDAO coursesDAO;

    @Getter @Setter
    private Integer courseId;

    @Getter @Setter
    private Integer studentId;

    @Transactional
    public String addCourseToStudent() {
        Course course = coursesDAO.findOne(courseId);
        Student student = studentsDAO.findOne(studentId);
        studentsDAO.addCourse(student, course);

        return "index?faces-redirect=true";
    }
}
