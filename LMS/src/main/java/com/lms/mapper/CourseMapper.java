package com.lms.mapper;

import org.springframework.stereotype.Component;

import com.lms.dto.CourseDTO;
import com.lms.entity.CourseEntity;

@Component
public class CourseMapper extends Mapper<CourseEntity,CourseDTO,CourseDTO> {

	@Override
	public CourseEntity convertDTOToEntity(CourseDTO dto) {
		return getMapper().map(dto, CourseEntity.class);
	}

	@Override
	public CourseDTO convertEntityToDTO(CourseEntity entity) {
		return getMapper().map(entity,CourseDTO.class);
	}

}
