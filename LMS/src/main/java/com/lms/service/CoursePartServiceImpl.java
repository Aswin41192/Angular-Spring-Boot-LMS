package com.lms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dto.CoursePartRequestDTO;
import com.lms.dto.CoursePartResponseDTO;
import com.lms.entity.CourseEntity;
import com.lms.entity.CoursePartEntity;
import com.lms.exceptions.RecordNotFoundException;
import com.lms.mapper.CoursePartMapper;
import com.lms.repository.CoursePartRepository;
import com.lms.repository.CourseRepository;

@Service
public class CoursePartServiceImpl implements CoursePartServiceInterface {
	
	@Autowired
	private CoursePartRepository coursePartRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CoursePartMapper mapper;
	

	@Override
	public CoursePartResponseDTO createCourse(CoursePartRequestDTO coursePartDTO) throws RecordNotFoundException {
		CourseEntity course = courseRepository.findByCourseId(coursePartDTO.getCourse().getCourseId())
								.orElseThrow(()-> new RecordNotFoundException("Course does not exists!"));
		CoursePartEntity coursePartEntity = mapper.convertDTOToEntity(coursePartDTO);
		coursePartEntity.setCourse(course);
		coursePartEntity.setPartId(UUID.randomUUID().toString());
		CoursePartEntity savedCoursePart = coursePartRepository.save(coursePartEntity);
		CoursePartResponseDTO coursePartResponseDTO = mapper.convertEntityToDTO(savedCoursePart);
		coursePartResponseDTO.setCourseId(course.getId());
		return coursePartResponseDTO;
	}

	@Override
	public CoursePartResponseDTO updateCourse(CoursePartRequestDTO coursePartDTO) throws RecordNotFoundException {
		CoursePartEntity coursePart = coursePartRepository.findByPartId(coursePartDTO.getPartId())
										.orElseThrow(()->new RecordNotFoundException("Course Part doesnot exist"));
		BeanUtils.copyProperties(coursePartDTO, coursePart);
		return mapper.convertEntityToDTO(coursePartRepository.save(coursePart));
	}

	@Override
	public CoursePartResponseDTO findCoursePartByPartId(String partId) throws RecordNotFoundException {
		CoursePartEntity coursePart = getCoursePart(partId);
		return mapper.convertEntityToDTO(coursePart);
	}

	@Override
	public Iterable<CoursePartResponseDTO> findAllCoursePartsForCourseId(String courseId) 
			throws RecordNotFoundException {
		List<CoursePartResponseDTO> courseParts = new ArrayList<CoursePartResponseDTO>();
		Iterable<CoursePartEntity> coursePartEntities= coursePartRepository.findByCourseId(Long.parseLong(courseId))
				.orElseThrow(()-> new RecordNotFoundException("Invalid Course Id"));
		coursePartEntities.forEach(part->courseParts.add(mapper.convertEntityToDTO(part)));
		return courseParts;
	}

	@Override
	public void deleteCoursePartByPartId(String partId) throws RecordNotFoundException {
		CoursePartEntity coursePart = getCoursePart(partId);
		coursePartRepository.delete(coursePart);
	}
	
	private CoursePartEntity getCoursePart(String partId) throws RecordNotFoundException {
		return coursePartRepository.findByPartId(partId)
					.orElseThrow(()-> new RecordNotFoundException("Course Part does not exists!"));
	}

}
