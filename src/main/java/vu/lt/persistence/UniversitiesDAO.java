package vu.lt.persistence;

import vu.lt.entities.University;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class UniversitiesDAO {
    @PersistenceContext
    EntityManager entityManager;

    public List<University> loadAll() {
        return this.entityManager.createNamedQuery("University.findAll", University.class).getResultList();
    }

    public void persist(University university) {
        this.entityManager.persist(university);
    }

    public University findOne(Integer id) {
        return this.entityManager.find(University.class, id);
    }
}
