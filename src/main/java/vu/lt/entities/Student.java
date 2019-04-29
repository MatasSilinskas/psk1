package vu.lt.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STUDENT")
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select student from Student as student")
})
@Getter
@Setter
@EqualsAndHashCode
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20)
    @Column(name = "STUDENT_NUMBER")
    private String studentNumber;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @JoinTable(name = "STUDENT_COURSE", joinColumns = {
            @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Course> courseList = new ArrayList<>();


    public Student() {
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }
}
