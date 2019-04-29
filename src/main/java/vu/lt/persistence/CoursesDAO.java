package vu.lt.persistence;

import vu.lt.entities.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CoursesDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Course> loadAll() {
        return this.em.createNamedQuery("Course.findAll", Course.class).getResultList();
    }

    public Course findOne(Integer id) {
        return this.em.find(Course.class, id);
    }

    public void persist(Course course) {
        this.em.persist(course);
    }
}
