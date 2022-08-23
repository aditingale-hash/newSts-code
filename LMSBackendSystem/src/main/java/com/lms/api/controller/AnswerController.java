package com.lms.api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.model.Answer;
import com.lms.api.repository.AnswerRepository;

@RestController
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("/answer")
	public Answer AddAnswer(@RequestBody Answer answer) {
		answer.setDateofpost(LocalDate.now());
		return answerRepository.save(answer);
		
	}
	
}
