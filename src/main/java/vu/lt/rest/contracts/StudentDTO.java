package vu.lt.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private Integer id;
    private String studentNumber;
    private String firstName;
    private String lastName;
}
