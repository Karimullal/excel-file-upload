package com.excel.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excel.app.model.Employee;
import com.excel.app.service.EmployeeService;
import com.excel.app.util.ExcelHelper;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/upload")
	private ResponseEntity<String> saveEmployee(@RequestParam("file") MultipartFile file){
			
		if(ExcelHelper.isExcelFormat(file)) {
			employeeService.saveEmployee(file);
		}
		return new ResponseEntity<String>("Excel data saved file name is :: "+file.getOriginalFilename(), HttpStatus.OK);
	}
	
	@GetMapping("get/all/emp")
	private ResponseEntity<List<Employee>> getAllEmployees(){
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
	}
}
