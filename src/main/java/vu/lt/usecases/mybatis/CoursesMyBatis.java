package vu.lt.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.CourseMapper;
import vu.lt.mybatis.model.Course;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CoursesMyBatis {
    @Inject
    private CourseMapper courseMapper;

    @Getter
    private List<Course> allCourses;

    @Getter
    @Setter
    private Course courseToCreate = new Course();

    @PostConstruct
    public void init() {
        this.loadAllCourses();
    }

    private void loadAllCourses() {
        this.allCourses = courseMapper.selectAll();
    }

    @Transactional
    public String createCourse() {
        courseMapper.insert(courseToCreate);
        return "/myBatis/courses?faces-redirect=true";
    }
}
