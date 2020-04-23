package com.lms.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lms.dto.CourseDTO;
import com.lms.dto.MultiResponseDTO;
import com.lms.entity.CourseEntity;
import com.lms.exceptions.RecordNotFoundException;
import com.lms.mapper.CourseMapper;
import com.lms.repository.CourseRepository;

@Service
public class CourseServiceInterfaceImpl implements CourseServiceInterface {
	
	private CourseRepository courseReposiory;
	private CourseMapper courseMapper;
	

	public CourseServiceInterfaceImpl(CourseRepository courseReposiory,CourseMapper courseMapper) {
		super();
		this.courseReposiory = courseReposiory;
		this.courseMapper = courseMapper;
	}

	@Override
	public CourseDTO createCourse(CourseDTO courseDTO) {
		 courseDTO.setCourseId(UUID.randomUUID().toString());
		 courseDTO.getParts().forEach(part->part.setPartId(UUID.randomUUID().toString()));
		 CourseEntity course = courseMapper.convertDTOToEntity(courseDTO);
		 return courseMapper.convertEntityToDTO(courseReposiory.save(course));
	}

	@Override
	public CourseDTO updateCourse(CourseDTO pCourse) throws RecordNotFoundException {
		CourseEntity course = findCourse(pCourse.getCourseId());
		BeanUtils.copyProperties(pCourse, course);
		return courseMapper.convertEntityToDTO(courseReposiory.save(course));
	}


	@Override
	public CourseDTO findCourseByCourseId(String courseId)throws RecordNotFoundException {
		CourseEntity course = findCourse(courseId);
		System.out.println("Course Parts ->>"+course.getParts());
		return courseMapper.convertEntityToDTO(course);
	}

	@Override
	public MultiResponseDTO<CourseDTO> findAllCourses(int page,int size) {
		page=page==0?0:page-1;
		MultiResponseDTO<CourseDTO> response = new MultiResponseDTO<CourseDTO>();
		Pageable pageable = PageRequest.of(page, size);
		Page<CourseEntity> courseEntities = courseReposiory.findAll(pageable);
		List<CourseDTO> courses = courseEntities.getContent().stream().map(courseMapper::convertEntityToDTO)
									.collect(Collectors.toList());
		response.setItems(courses);
		response.setTotalCount(courseEntities.getTotalElements());
		response.setTotalPage(courseEntities.getTotalPages());
		return response;
	}

	@Override
	public void deleteCourseByCourseId(String courseId) throws RecordNotFoundException {
		CourseEntity course = findCourse(courseId);
		courseReposiory.deleteById(course.getId());
	}
	
	private CourseEntity findCourse(String courseId) throws RecordNotFoundException {
		CourseEntity course = courseReposiory.findByCourseId(courseId).orElseThrow(
									()->new RecordNotFoundException("Invalid Course Id or course not available"));
		return course;
	}

}
