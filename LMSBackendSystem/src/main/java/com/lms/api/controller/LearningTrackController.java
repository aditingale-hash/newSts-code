package com.lms.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.LearningTrackDto;
import com.lms.api.model.Course;
import com.lms.api.model.LearningTrack;
import com.lms.api.repository.CourseRepository;

import com.lms.api.repository.LearningTrackRepository;

@RestController
public class LearningTrackController {

	
	
	@Autowired
private LearningTrackRepository learningTrackRepository;
	@Autowired
	private CourseRepository courseRepository;

	
	
	/*
	 * create post API for learning track 
	 */
	
	@PostMapping("/learningtrack")
	public LearningTrack PostLearningTrack(@RequestBody LearningTrack learningTrack ) {
		
		return learningTrackRepository.save(learningTrack);
		
	}
	
		
	/*
	 * Create an API that will return the id,names of all Learning track along with its courses
	 * 
	 * {
	 * id:1
	 * name :Core Java - Foundational
	 * courses: [
	 *  {  }, { }, { }
	 * ]
	 * }
	 */
	//@GetMapping()
	
	@GetMapping("/learning-track")
	public List<LearningTrackDto> getlearningTrackWithCourse() {
		//fetch all learning tracks;
		List<LearningTrack>list=learningTrackRepository.findAll();
		List<LearningTrackDto> listDto=new ArrayList<>();
		//for every learning track fectch the list of courses
		
		list.stream().forEach(lt->{
			LearningTrackDto dto=new LearningTrackDto();
			List<Course> listCourse=courseRepository.findByLearningTrackId(lt.getId());
			dto.setId(lt.getId());
			dto.setName(lt.getName());
			dto.setCourse(listCourse);
			listDto.add(dto);
			
		});
		
		return listDto;
	}


}
	
	




