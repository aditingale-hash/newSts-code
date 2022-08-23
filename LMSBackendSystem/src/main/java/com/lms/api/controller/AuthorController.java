package com.lms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.model.Author;
import com.lms.api.model.Course;
import com.lms.api.repository.AuthorRepository;
import com.lms.api.repository.CourseRepository;


@RestController
public class AuthorController {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private CourseRepository courseRepository;
		
	@PostMapping("/author")
	public Author AddAuthor(@RequestBody Author author) {
		
		return authorRepository.save(author);
	}
	
	@PutMapping("/author/course/{aid}/{cid}")
	public Course AssignAuthorToCourse(@PathVariable("aid")Long aid,@PathVariable("cid")Long cid) {
		
		Author author=authorRepository.getById(aid);
		Course course=courseRepository.getById(cid);
		
		List<Author>authorList=course.getAuthor();
		authorList.add(author);
			
		course.setAuthor(authorList);
		
		
		return courseRepository.save(course);
		
	
			
	}
	@PutMapping("/course/author/{cid}/{aid}")
	public Course UnAssignAuthorToCourse(@PathVariable("cid")Long cid,@PathVariable("aid")Long aid) {
		
		Author author=authorRepository.getById(aid);
		Course course=courseRepository.getById(cid);
		
		List<Author>authorList=course.getAuthor();
		authorList.remove(author);
			
		course.setAuthor(authorList);
		
		
		return courseRepository.save(course);
		
	
			
	}
	
}


