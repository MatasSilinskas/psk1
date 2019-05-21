package vu.lt.persistence;

import vu.lt.entities.Course;
import vu.lt.entities.Student;
import vu.lt.entities.University;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class StudentsDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Student> loadAll() {
        return em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    public void persist(Student student) {
        this.em.persist(student);
    }

    public Student findOne(Integer id) {
        return this.em.find(Student.class, id);
    }

    public void addCourse(Student student, Course course) {
        student.addCourse(course);
    }

    public Student update(Student student) {
        return this.em.merge(student);
    }
}
