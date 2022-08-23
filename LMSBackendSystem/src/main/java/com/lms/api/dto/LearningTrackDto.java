package com.lms.api.dto;

import java.util.List;

import com.lms.api.model.Course;

public class LearningTrackDto {

	private String name;
	private Long id;
	private List<Course>course;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}
	
	
	
}
