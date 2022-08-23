package com.lms.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Questions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Lob
	private String question_text;
	
	private int likes=0;
	
	private String username;
	
	@Column(columnDefinition = "DATE")
	private LocalDate dateofpost;
	@JsonIgnore
	@ManyToMany(mappedBy = "questions")
	
	private List<Topic>topic;
	@JsonIgnore
	@ManyToMany(cascade =CascadeType.ALL )
	@JoinTable(
					name = "Question_Answer",
					joinColumns = @JoinColumn(name="Question_id"),
					inverseJoinColumns = @JoinColumn(name="Answer_id")
			)
	private List<Answer>answer;
	

	public LocalDate getDateofpost() {
		return dateofpost;
	}

	public void setDateofpost(LocalDate dateofpost) {
		this.dateofpost = dateofpost;
	}

	public List<Topic> getTopic() {
		return topic;
	}

	public void setTopic(List<Topic> topic) {
		this.topic = topic;
	}

	public List<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
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
