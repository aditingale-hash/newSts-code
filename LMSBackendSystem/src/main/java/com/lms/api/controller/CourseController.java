package com.lms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.model.Course;
import com.lms.api.model.LearningTrack;
import com.lms.api.repository.CourseRepository;
import com.lms.api.repository.LearningTrackRepository;

@RestController
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private LearningTrackRepository learningTrackRepository;
		

	/*
	 * Create a post api for course
	 */
	
	@PostMapping("/course/{learningid}")
	public Course addCourse(@RequestBody Course course,@PathVariable("learningid") Long learningid) 
	{
		LearningTrack learningTrack = learningTrackRepository.getById(learningid);
		
		if(learningTrack != null)
			course.setLearningTrack(learningTrack);
		
		return courseRepository.save(course);
	}

	
	
	
}
