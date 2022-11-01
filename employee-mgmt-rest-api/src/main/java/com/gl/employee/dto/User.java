package com.gl.employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private long userId;

    private String username;

    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDate creationDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private char enabledFlag;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDate endDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Role> roles;
}
