package com.lms.api.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.LearningTrackDto;
import com.lms.api.dto.UserDto;
import com.lms.api.model.Course;
import com.lms.api.model.LearningTrack;
import com.lms.api.model.User;
import com.lms.api.repository.CourseRepository;
import com.lms.api.repository.EnrollRepository;
import com.lms.api.repository.LearningTrackRepository;
import com.lms.api.repository.UserRepository;


@RestController
public class UserController {

	
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EnrollRepository enrollRepository;
	
	
	@Autowired
	private LearningTrackRepository learningTrackRepository;
	
	@PostMapping("/user/register")
	public User Register(@RequestBody User user) {
		
		String PlainText=user.getPassword();
		String encodedpass=passwordEncoder.encode(PlainText);
		user.setPassword(encodedpass);
		
		return userRepository.save(user);
		
	}
	
	@GetMapping("/user/login")
	public UserDto Login(Principal principal) {
		String username=principal.getName();
		User user=userRepository.findByUsername(username);
		UserDto dto=new UserDto();
		dto=dto.convert(user);
		return dto;
		
		
		
	}
	
	/*
	 * User Profile Update name, mobile, city
	 */
	
	
	@PutMapping("/user/update/{id}")
	public User updateUserProfile(@PathVariable("id") Long id ,@RequestBody User usernew){
		
		User userDB=userRepository.getById(id);
		
		if(usernew.getName()!=null)
			userDB.setName(usernew.getName());
		if(usernew.getCity()!=null)
			userDB.setCity(usernew.getCity());
		if(usernew.getMobile()!=null)
			userDB.setMobile(usernew.getMobile());
		
		return userRepository.save(userDB);
		
	}
	
	//get all learning track with courses based on userId
	
	
@GetMapping("/learningtrack/course/{userid}")
public List<LearningTrackDto> GetLearningTrackBasedonUserId(@RequestBody LearningTrack learningTrack,
		@PathVariable("userid") Long userid) {
	
	List<LearningTrack>list=learningTrackRepository.findAll();
	List<LearningTrackDto> listDto=new ArrayList<>();
	//for every learning track fectch the list of courses
	
	list.stream().forEach(lt->{
		LearningTrackDto dto=new LearningTrackDto();
		List<Course> listCourse=enrollRepository.findByUserId(userid);
		dto.setId(lt.getId());
		dto.setName(lt.getName());
		dto.setCourse(listCourse);
		listDto.add(dto);
		
	});
	
	return listDto;

}

@GetMapping("/user/learning-track")
public List<LearningTrackDto> GetLearningTrackByUserId(Principal principal) {
	
	List<LearningTrack>list=learningTrackRepository.getLearningTracksByUserID(principal.getName());
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
