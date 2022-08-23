package com.lms.api.dto;

import java.time.LocalDate;

public class QuestionDto {
	
private Long id;
private String text;
private int likes;
private String username;
private int numberofanswer;
private LocalDate postdate;

public LocalDate getPostdate() {
	return postdate;
}
public void setPostdate(LocalDate postdate) {
	this.postdate = postdate;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
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
public int getNumberofanswer() {
	return numberofanswer;
}
public void setNumberofanswer(int numberofanswer) {
	this.numberofanswer = numberofanswer;
}

}
