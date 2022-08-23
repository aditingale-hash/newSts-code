package com.lms.api.model;

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
import javax.persistence.OneToOne;


@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private double fee=0;
	
	@Lob
	private String description;
	
	@OneToOne
	private LearningTrack learningTrack;
	
	
	
	@ManyToMany(cascade =CascadeType.ALL )
	@JoinTable(
					name = "Author_Course",
					joinColumns = @JoinColumn(name="Course_id"),
					inverseJoinColumns = @JoinColumn(name="Author_id")
			)
	private List<Author>author;
	
	public List<Author> getAuthor() {
		return author;
	}

	public void setAuthor(List<Author> author) {
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LearningTrack getLearningTrack() {
		return learningTrack;
	}

	public void setLearningTrack(LearningTrack learningTrack) {
		this.learningTrack = learningTrack;
	}
	
	
	
}
