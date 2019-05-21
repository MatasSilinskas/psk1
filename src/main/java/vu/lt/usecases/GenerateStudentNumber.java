package vu.lt.usecases;

import vu.lt.services.StudentNumberGeneratorInterface;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GenerateStudentNumber implements Serializable {
    @Inject
    private StudentNumberGeneratorInterface studentNumberGenerator;

    private Future<String> studentNumberGenerationTask = null;

    public String generateStudentNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        studentNumberGenerationTask = studentNumberGenerator.generate();

        return  "/student?faces-redirect=true&studentId=" + requestParameters.get("studentId");
    }

    public boolean isStudentNumberGenerationRunning() {
        return studentNumberGenerationTask != null && !studentNumberGenerationTask.isDone();
    }

    public String getStudentNumberGenerationStatus() throws ExecutionException, InterruptedException {
        if (studentNumberGenerationTask == null) {
            return null;
        }

        if (isStudentNumberGenerationRunning()) {
            return "Student number generation in progress";
        }

        return "Suggested student number: " + studentNumberGenerationTask.get();
    }
}
