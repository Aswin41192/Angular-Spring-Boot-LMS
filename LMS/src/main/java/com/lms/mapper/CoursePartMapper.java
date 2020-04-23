package com.lms.mapper;

import org.springframework.stereotype.Component;

import com.lms.dto.CoursePartResponseDTO;
import com.lms.dto.CoursePartRequestDTO;
import com.lms.entity.CoursePartEntity;

@Component
public class CoursePartMapper extends Mapper<CoursePartEntity,CoursePartRequestDTO,CoursePartResponseDTO> {

	@Override
	public CoursePartEntity convertDTOToEntity(CoursePartRequestDTO dto) {
		return getMapper().map(dto, CoursePartEntity.class);
	}

	@Override
	public CoursePartResponseDTO convertEntityToDTO(CoursePartEntity entity) {
		return getMapper().map(entity, CoursePartResponseDTO.class);
	}

}
