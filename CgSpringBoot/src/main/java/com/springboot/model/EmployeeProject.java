package com.springboot.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class EmployeeProject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade =CascadeType.ALL) 
	private Project project;
	
	@OneToOne(cascade =CascadeType.ALL )
	private Employee employee;
	
	@Temporal(TemporalType.DATE)
	private Date WorkingForm;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getWorkingForm() {
		return WorkingForm;
	}

	public void setWorkingForm(Date workingForm) {
		WorkingForm = workingForm;
	}

	
	
}
