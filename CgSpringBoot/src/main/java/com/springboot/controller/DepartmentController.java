package com.springboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Department;
import com.springboot.repository.DepartmentRepository;

@RestController
public class DepartmentController {
	
@Autowired
private DepartmentRepository departmentrepository;

@PostMapping("/department")
public Department  adddepartment(@RequestBody Department department)
{
	return departmentrepository.save(department);
}

@GetMapping("/department")
public List<Department> getAllDepartments() {
	
	return departmentrepository.findAll();
	
}
}