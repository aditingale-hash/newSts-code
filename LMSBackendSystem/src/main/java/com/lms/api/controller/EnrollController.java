package com.lms.api.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.model.Enroll;
import com.lms.api.model.LearningTrack;
import com.lms.api.model.User;
import com.lms.api.repository.EnrollRepository;
import com.lms.api.repository.LearningTrackRepository;
import com.lms.api.repository.UserRepository;


@RestController
public class EnrollController {
	
	@Autowired
	private LearningTrackRepository learningTrackRepository;
	
	@Autowired
	private EnrollRepository enrollRepository;
	
	@Autowired
	private UserRepository userRepositor;
	
   
	@PostMapping("/enroll/user/{learningid}/{userid}")
	public void EnrollUserInLearningTrack(@PathVariable("learningid") Long learningid,
			@PathVariable("userid") Long userid){
		User user=userRepositor.getById(userid);
		
		LearningTrack learningTrack = learningTrackRepository.getById(learningid);
		
		Enroll enroll=new Enroll();
		enroll.setUser(user);
		enroll.setLearningTrack(learningTrack);
		enroll.setEnd_date(LocalDate.now().plusYears(1));
		enroll.setEnroll_date(LocalDate.now());
		 enrollRepository.save(enroll);
	}

		

	}

