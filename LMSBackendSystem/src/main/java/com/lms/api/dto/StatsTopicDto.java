package com.lms.api.dto;

public class StatsTopicDto {
private int totalNumberOfTopics;
private int totalNumberOfQuestions;
private int totalNumberOfAnswers;
private int totalNumberOfUsers; // [that have posted either Q or A]
public int getTotalNumberOfTopics() {
	return totalNumberOfTopics;
}
public void setTotalNumberOfTopics(int totalNumberOfTopics) {
	this.totalNumberOfTopics = totalNumberOfTopics;
}
public int getTotalNumberOfQuestions() {
	return totalNumberOfQuestions;
}
public void setTotalNumberOfQuestions(int totalNumberOfQuestions) {
	this.totalNumberOfQuestions = totalNumberOfQuestions;
}
public int getTotalNumberOfAnswers() {
	return totalNumberOfAnswers;
}
public void setTotalNumberOfAnswers(int totalNumberOfAnswers) {
	this.totalNumberOfAnswers = totalNumberOfAnswers;
}
public int getTotalNumberOfUsers() {
	return totalNumberOfUsers;
}
public void setTotalNumberOfUsers(int totalNumberOfUsers) {
	this.totalNumberOfUsers = totalNumberOfUsers;
}

}
