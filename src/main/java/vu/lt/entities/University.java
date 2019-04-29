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
@Table(name = "UNIVERSITY")
@NamedQueries({
        @NamedQuery(name = "University.findAll", query = "select university from University as university")
})
@Getter
@Setter
@EqualsAndHashCode
public class University implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20)
    @Column(name = "university_name")
    private String name;

    @OneToMany(mappedBy = "university", fetch = FetchType.EAGER)
    private List<Student> studentList = new ArrayList<>();

    public University() {
    }
}
