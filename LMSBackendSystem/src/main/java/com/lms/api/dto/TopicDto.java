package com.lms.api.dto;

import java.util.List;


public class TopicDto {

	private Long id;
	private String topic;
	private List<QuestionDto>questions;
	private int numberofquestions;
	
	public int getNumberofquestions() {
		return numberofquestions;
	}
	public void setNumberofquestions(int numberofquestions) {
		this.numberofquestions = numberofquestions;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<QuestionDto> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDto> questions) {
		this.questions = questions;
	}
	
		
}
