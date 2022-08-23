package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Project;
import com.springboot.repository.ProjectRepository;

@RestController
public class ProjectCntroller {

	@Autowired
	private ProjectRepository projectrepository;
	
	@PostMapping("/project")
	public Project postproject(@RequestBody Project project) {
		
		 return projectrepository.save(project);
		
	}
	
	@GetMapping("/project")
	public List<Project> getAllProjects() {
		
		return  projectrepository.findAll();
		
	}
	@GetMapping("/project/{pid}")
	public Project getAllProjectById(@PathVariable("pid") long pid) {
		Project project=projectrepository.getById(pid);
		return project;
	}
	
	@DeleteMapping("/project/{pid}")	
	public void deleteProject(@PathVariable("pid") Long pid) {
		projectrepository.deleteById(pid);

	}
	
	

	
}
