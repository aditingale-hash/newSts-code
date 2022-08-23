package com.lms.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String topic;
	
	@JsonManagedReference
	@ManyToMany(cascade =CascadeType.ALL)
	@JoinTable(
					name = "topic_question",
					joinColumns = @JoinColumn(name="Topic_id"),
					inverseJoinColumns = @JoinColumn(name="Question_id")
			)
	private List<Questions>questions;

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
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
	
	
}
