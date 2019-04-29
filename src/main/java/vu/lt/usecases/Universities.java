package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.University;
import vu.lt.persistence.UniversitiesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Universities {
    @Inject
    private UniversitiesDAO universitiesDAO;

    @Getter @Setter
    private University universityToCreate = new University();

    @Getter
    private List<University> allUniversities;

    @PostConstruct
    public void init() {
        loadAllUniversities();
    }

    @Transactional
    public String createUniversity() {
        this.universitiesDAO.persist(universityToCreate);
        return "index?faces-redirect=true";
    }

    public void loadAllUniversities() {
        this.allUniversities = universitiesDAO.loadAll();
    }
}
