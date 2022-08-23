package com.lms.api.dto;

import com.lms.api.model.Course;

public class ReviewDto {

	private String content;
	private double rating;
	private Course course;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
