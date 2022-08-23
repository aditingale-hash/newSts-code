package com.lms.api.controller;

public class PaginationDto {
private Long id;
private String TopicName;
private int numberofquestions;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getTopicName() {
	return TopicName;
}
public void setTopicName(String topicName) {
	TopicName = topicName;
}
public int getNumberofquestions() {
	return numberofquestions;
}
public void setNumberofquestions(int numberofquestions) {
	this.numberofquestions = numberofquestions;
}

}
