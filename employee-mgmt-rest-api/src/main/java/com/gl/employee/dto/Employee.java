package com.gl.employee.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@JsonPropertyOrder({"employeeId", "firstName", "lastName", "email"})
public class Employee {


    @JsonProperty(value = "id"
            //, access = JsonProperty.Access.WRITE_ONLY
    )
    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
}
