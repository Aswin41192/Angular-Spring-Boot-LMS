package com.lms.controller;

import javax.validation.Valid;
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
import com.lms.dto.CoursePartRequestDTO;
import com.lms.dto.CoursePartResponseDTO;
import com.lms.exceptions.RecordNotFoundException;
import com.lms.service.CoursePartServiceInterface;

@RestController
@RequestMapping("/api/course/parts")
public class CoursePartController extends BaseController {

	@Autowired
	private CoursePartServiceInterface coursePartServiceInterfaceImpl;
	
	
	@PostMapping
	public ResponseEntity<CoursePartResponseDTO> createCoursePart(@Valid @RequestBody CoursePartRequestDTO coursePartDTO) throws RecordNotFoundException {
		CoursePartResponseDTO createdCourse = coursePartServiceInterfaceImpl.createCourse(coursePartDTO);
		return getResponseEntity_WithStatusCreated(createdCourse);
		
	}
	
	@GetMapping
	public ResponseEntity<Iterable<CoursePartResponseDTO>> findAllCourseParts(@RequestParam("courseId") @NotBlank(message="Course Id cannot be blank") String courseId) throws RecordNotFoundException {
		Iterable<CoursePartResponseDTO> courseParts = coursePartServiceInterfaceImpl.findAllCoursePartsForCourseId(courseId);
		return getResponseEntity_WithStatusOk(courseParts);
	}
	
	@GetMapping("/{partId}")
	public ResponseEntity<CoursePartResponseDTO> findCoursePartByPartId(@PathVariable @NotBlank(message="Part Id cannot be blank") String partId) throws RecordNotFoundException {
		CoursePartResponseDTO coursePart = coursePartServiceInterfaceImpl.findCoursePartByPartId(partId);
		return getResponseEntity_WithStatusOk(coursePart);
	}
	
	@PutMapping
	public ResponseEntity<CoursePartResponseDTO> updateCoursePart(@Valid @RequestBody CoursePartRequestDTO coursePartDTO) throws RecordNotFoundException {
		CoursePartResponseDTO updatedCoursePart = coursePartServiceInterfaceImpl.updateCourse(coursePartDTO);
		return getResponseEntity_WithStatusOk(updatedCoursePart);
	}
	
	@DeleteMapping("/{partId}")
	public ResponseEntity<String> deleteCoursePartByPartId(@PathVariable @NotBlank(message="Part Id cannot be blank") String partId) throws RecordNotFoundException {
		coursePartServiceInterfaceImpl.deleteCoursePartByPartId(partId);
		return getResponseEntity_WithStatusOk("Part deleted successfully!");
	}
	

}
