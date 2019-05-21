package vu.lt.rest;

import lombok.*;
import vu.lt.entities.Student;
import vu.lt.persistence.StudentsDAO;
import vu.lt.rest.contracts.StudentDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/students")
public class StudentController {

    @Inject
    @Setter @Getter
    private StudentsDAO studentsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Student student = studentsDAO.findOne(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(this.getStudentDTO(student)).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer playerId, StudentDTO studentData) {
        try {
            Student student = studentsDAO.findOne(playerId);

            if (student == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            student.setFirstName(studentData.getFirstName());
            student.setLastName(studentData.getLastName());
            student.setStudentNumber(studentData.getStudentNumber());

            studentsDAO.update(student);

            return Response.ok(this.getStudentDTO(student)).build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStudentNumber(studentDTO.getStudentNumber());
        student.setLastName(studentDTO.getLastName());
        student.setFirstName(studentDTO.getFirstName());

        studentsDAO.persist(student);

        return Response.ok(this.getStudentDTO(student)).status(Response.Status.CREATED).build();
    }

    private StudentDTO getStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setStudentNumber(student.getStudentNumber());
        studentDTO.setId(student.getId());

        return studentDTO;
    }
}