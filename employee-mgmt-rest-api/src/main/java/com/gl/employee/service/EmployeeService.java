package com.gl.employee.service;

import com.gl.employee.dto.Employee;
import com.gl.employee.dto.Role;
import com.gl.employee.dto.User;

import java.util.List;

public interface EmployeeService {

    public Role createRole(Role role);

    public User createUser(User user);

    public Employee createEmployee(Employee employee);

    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(long id);

    public Employee updateEmployee(Employee employee);

    public String deleteEmployee(long employeeId);

    public List<Employee> getEmployeeByFirstNameOrLastName(String firstName, String lastName);

    public List<Employee> getEmployeeSortBy(String order);
}
