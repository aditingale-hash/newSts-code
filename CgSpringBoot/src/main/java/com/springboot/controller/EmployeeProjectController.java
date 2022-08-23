package com.springboot.controller;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Employee;
import com.springboot.model.EmployeeProject;
import com.springboot.model.Project;
import com.springboot.repository.EmployeeProjectRepository;
import com.springboot.repository.EmployeeRepository;
import com.springboot.repository.ProjectRepository;

@RestController
public class EmployeeProjectController {
@Autowired
private EmployeeProjectRepository  employeeProjectRepository;

@Autowired
private ProjectRepository projectrepository;

@Autowired
private EmployeeRepository employeerepository;

@PostMapping("/employee/project/{eid}/{pid}")
public EmployeeProject postEmployeeProject(@PathVariable("pid")Long pid,@PathVariable("eid")Long eid) {
	
Project project=projectrepository.getById(pid);

Employee employee=employeerepository.getById(eid);

EmployeeProject employeeProject=new EmployeeProject();

employeeProject.setProject(project);
employeeProject.setEmployee(employee);

LocalDate ld=LocalDate.of(2021, 11, 15);

Date dt=new Date();


	dt=Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());


employeeProject.setWorkingForm(dt);
	
	return employeeProjectRepository.save(employeeProject);
		
}

@GetMapping("/employeeproject")
public List<EmployeeProject> getAllEmployeeProject() {
	
	return employeeProjectRepository.findAll();
	
}

@DeleteMapping("/employeeproject/{epid}")
public void deleteEmployeeProject(@PathVariable("epid") Long epid) {
	employeeProjectRepository.deleteById(epid);
	
	
} 

@GetMapping("/employee/project/{pid}")
public List<Employee> getEmployeeByProject(@PathVariable("pid") Long pid) {
	
	List<Employee>list=employeeProjectRepository.getEmployeeByProject(pid);
	return list;
}

}
