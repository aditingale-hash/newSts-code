package com.health.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;


@Entity
public class Precription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String tablets;
	private String precaution;
	private String username;
	

	
	@OneToOne
	private User user;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTablets() {
		return tablets;
	}
	public void setTablets(String tablets) {
		this.tablets = tablets;
	}
	public String getPrecaution() {
		return precaution;
	}
	public void setPrecaution(String precaution) {
		this.precaution = precaution;
	}
	
}
