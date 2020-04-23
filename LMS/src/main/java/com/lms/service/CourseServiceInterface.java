package com.lms.service;


import com.lms.dto.CourseDTO;
import com.lms.dto.MultiResponseDTO;
import com.lms.exceptions.RecordNotFoundException;

public interface CourseServiceInterface {

	CourseDTO createCourse(CourseDTO course);
	CourseDTO updateCourse(CourseDTO course) throws RecordNotFoundException;
	CourseDTO findCourseByCourseId(String courseId) throws RecordNotFoundException;
	MultiResponseDTO<CourseDTO> findAllCourses(int page,int size);
	void deleteCourseByCourseId(String courseId) throws RecordNotFoundException;
}
