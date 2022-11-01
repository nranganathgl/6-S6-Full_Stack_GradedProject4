package com.gl.employee.repository;

import com.gl.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    public List<EmployeeEntity> findByFirstNameOrLastName(String firstName, String lastName);
}
