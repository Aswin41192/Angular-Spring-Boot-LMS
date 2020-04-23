package com.lms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CoursePartRequestDTO {

	private String partId;
	
	@NotBlank(message = "Part Name should not be blank" )
	private String partName;
	
	@NotNull(message = "Course is mandatory to create part")
	private CourseDTO course;

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}
	
	@Override
	public String toString() {
		return "CoursePartDTO [partId=" + partId + ", partName=" + partName + ", course=" + course + "]";
	}
	
}
