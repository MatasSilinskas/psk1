package vu.lt.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COURSE")
@NamedQueries({
        @NamedQuery(name = "Course.findAll", query = "select course from Course as course")
})
@Getter
@Setter
@EqualsAndHashCode
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20)
    @Column(name = "COURSE_NAME")
    private String name;

    @ManyToMany(mappedBy = "courseList", fetch = FetchType.EAGER)
    private List<Student> studentList = new ArrayList<>();

}
