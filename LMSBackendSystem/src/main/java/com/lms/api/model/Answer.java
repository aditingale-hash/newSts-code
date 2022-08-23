package com.lms.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Lob
	private String answer_text;
	
	private int likes=0;
	
	private String username;
	
	@Column(columnDefinition = "DATE")
	private LocalDate dateofpost;
	
	
	public LocalDate getDateofpost() {
		return dateofpost;
	}
	public void setDateofpost(LocalDate dateofpost) {
		this.dateofpost = dateofpost;
	}
	@JsonIgnore
	@ManyToMany(mappedBy = "answer")
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
	public String getAnswer_text() {
		return answer_text;
	}
	public void setAnswer_text(String answer_text) {
		this.answer_text = answer_text;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
