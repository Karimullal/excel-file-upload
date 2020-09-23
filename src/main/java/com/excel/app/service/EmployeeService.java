package com.excel.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excel.app.model.Employee;
import com.excel.app.repo.EmployeeRepo;
import com.excel.app.util.ExcelHelper;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepo employeeRepo;

	public void saveEmployee(MultipartFile file) {
		try {
			List<Employee> employees = ExcelHelper.excelToEmployee(file.getInputStream());
			employeeRepo.saveAll(employees);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepo.findAll();
	}
	
}
