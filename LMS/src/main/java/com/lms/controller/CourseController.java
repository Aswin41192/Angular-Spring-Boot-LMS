package com.lms.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lms.dto.CourseDTO;
import com.lms.dto.MultiCourseResponseDTO;
import com.lms.exceptions.RecordNotFoundException;
import com.lms.service.CourseServiceInterface;

@RestController
@RequestMapping("/api/course")
public class CourseController extends BaseController {
	
	@Autowired
	private CourseServiceInterface courseServiceInterfaceImpl;

	@PostMapping
	public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDTO course) {
		 if(course.getEndDate().before(course.getStartDate())) {
			 return getResponseEntity_WithStatusBadRequest("Course End Date should be ahead of the Course Start Date!");
		 }

		CourseDTO savedCourse = courseServiceInterfaceImpl.createCourse(course);
		return getResponseEntity_WithStatusCreated(savedCourse);
	}

	@GetMapping
	public ResponseEntity<MultiCourseResponseDTO> findAllCourses(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@Min(value = 1, message="Size parameter should be greater than 0") @RequestParam(name = "size", defaultValue = "5") int size) {
		logger.info("Inside the method {} -------->","findAllCourses");
		MultiCourseResponseDTO allCourses = courseServiceInterfaceImpl.findAllCourses(page, size);
		logger.info("Courses ---------> {}",allCourses);
		return getResponseEntity_WithStatusOk(allCourses);
	}
	
	@GetMapping("/{courseId}")
	public ResponseEntity<CourseDTO> findCourseByCourseId(
			@NotBlank(message = "Course Id cannot be blank") @PathVariable("courseId") String courseId)
			throws RecordNotFoundException {
		CourseDTO course = courseServiceInterfaceImpl.findCourseByCourseId(courseId);
		return getResponseEntity_WithStatusOk(course);
	}

	@PutMapping
	public ResponseEntity<CourseDTO> updateCourse(@Valid @RequestBody CourseDTO course) throws RecordNotFoundException {
		CourseDTO savedCourse = courseServiceInterfaceImpl.updateCourse(course);
		return getResponseEntity_WithStatusCreated(savedCourse);
	}

	@DeleteMapping("/{courseId}")
	public ResponseEntity<String> deleteCourse(
			@NotBlank(message = "Course Id cannot be blank") @PathVariable("courseId") String courseId)
			throws RecordNotFoundException {
		courseServiceInterfaceImpl.deleteCourseByCourseId(courseId);
		return getResponseEntity_WithStatusOk("Course Deleted Successfully!");
	}

}
