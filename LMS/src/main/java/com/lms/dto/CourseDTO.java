package com.lms.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
public class CourseDTO {

	private String courseId;
	
	@NotBlank(message = "Course Name should not be blank!")
	private String courseName;
	
	@NotNull(message = "Start Date should not be blank")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss a")
	@FutureOrPresent(message = "Start Date should be in future or present with the pattern dd-MM-yyyy hh:mm:ss AM/PM")
	private Date startDate;
	
	@NotNull(message = "End Date should not be blank")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss a")
	@FutureOrPresent(message = "End Date should be in future or present with the pattern dd-MM-yyyy hh:mm:ss AM/PM")
	private Date endDate;
	
	@NotNull(message = "Registration End Date should not be blank")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss a")
	@FutureOrPresent(message = "Registration End Date should be in future or present with the pattern dd-MM-yyyy hh:mm:ss AM/PM")
	private Date registrationEndDate;
	
	@DecimalMin(value = "1", message = "Registration Count should be greater than 1")
	private int maxRegistration;
	
	@NotBlank(message = "Created By should not be blank")
	private String createdBy;
	
	private List<CoursePartResponseDTO> parts = new ArrayList<CoursePartResponseDTO>();
	
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
	
	public List<CoursePartResponseDTO> getParts() {
		return parts;
	}
	public void setParts(List<CoursePartResponseDTO> parts) {
		this.parts = parts;
	}
	@Override
	public String toString() {
		return "CourseDTO [courseId=" + courseId + ", courseName=" + courseName + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", registrationEndDate=" + registrationEndDate + ", maxRegistration="
				+ maxRegistration + ", createdBy=" + createdBy + "]";
	}
	
	
	
}
