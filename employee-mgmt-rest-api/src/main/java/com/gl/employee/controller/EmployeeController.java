package com.gl.employee.controller;

import com.gl.employee.dto.Employee;
import com.gl.employee.dto.Role;
import com.gl.employee.dto.User;
import com.gl.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "/role", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role result = employeeService.createRole(role);

        if (result.getId() != 0) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(role, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User result = employeeService.createUser(user);
        if (result.getUserId() != 0) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee result = employeeService.createEmployee(employee);
        if (result.getEmployeeId() != 0) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(employee, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @GetMapping("accessDenied")
    public ResponseEntity<String> accessDenied() {
        return new ResponseEntity<>("Sorry, you do not have permission to access this URI.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee result = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable long employeeId) {
        return employeeService.deleteEmployee(employeeId);
        //return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search/{empName}")
    public List<Employee> getEmployeeByName(@PathVariable String empName) {
        return employeeService.getEmployeeByFirstNameOrLastName(empName, empName);
    }

    @GetMapping("/sort")
    public List<Employee> getEmployeeSortBy(@RequestParam("order") String order) {
        return employeeService.getEmployeeSortBy(order);
    }
}
