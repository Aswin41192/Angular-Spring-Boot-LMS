package com.lms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COURSES")
public class CourseEntity extends BaseEntity {

	@Column(unique = true)
	private String courseId;
	private String courseName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationEndDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated = new Date();
	
	private int maxRegistration;
	
	private String createdBy;
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
	private List<CoursePartEntity> parts = new ArrayList<CoursePartEntity>();

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getRegistrationEndDate() {
		return registrationEndDate;
	}

	public void setRegistrationEndDate(Date registrationEndDate) {
		this.registrationEndDate = registrationEndDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getMaxRegistration() {
		return maxRegistration;
	}

	public void setMaxRegistration(int maxRegistration) {
		this.maxRegistration = maxRegistration;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public List<CoursePartEntity> getParts() {
		return parts;
	}

	public void setParts(List<CoursePartEntity> parts) {
		this.parts = parts;
	}
}
