package com.lms.dto;

import java.util.ArrayList;
import java.util.List;

public class MultiCourseResponseDTO extends PaginationDTO {

	private List<CourseDTO> courses = new ArrayList<CourseDTO>();

	public List<CourseDTO> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDTO> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "MultiCourseResponseDTO [courses=" + courses + "]";
	}
	
}
