package com.lms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CoursePartResponseDTO {

	private String partId;
	private String partName;
	
	@JsonIgnore
	private Long  courseId;

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

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "CoursePartResponseDTO [partId=" + partId + ", partName=" + partName + ", courseId=" + courseId + "]";
	}
}
