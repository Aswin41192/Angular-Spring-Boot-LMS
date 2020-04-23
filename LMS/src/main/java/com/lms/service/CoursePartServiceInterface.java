package com.lms.service;

import com.lms.dto.CoursePartResponseDTO;
import com.lms.dto.CoursePartRequestDTO;
import com.lms.exceptions.RecordNotFoundException;

public interface CoursePartServiceInterface {

	CoursePartResponseDTO createCourse(CoursePartRequestDTO coursePart) throws RecordNotFoundException;
	CoursePartResponseDTO updateCourse(CoursePartRequestDTO coursePart) throws RecordNotFoundException;
	CoursePartResponseDTO findCoursePartByPartId(String partId) throws RecordNotFoundException;
	Iterable<CoursePartResponseDTO> findAllCoursePartsForCourseId(String courseId) throws RecordNotFoundException;
	void deleteCoursePartByPartId(String partId) throws RecordNotFoundException;
}
