package com.excel.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excel.app.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
