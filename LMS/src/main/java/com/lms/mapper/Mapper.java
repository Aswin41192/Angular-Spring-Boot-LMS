package com.lms.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public abstract class Mapper<T,A,R> {
	
	public ModelMapper getMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}
	
	public abstract T convertDTOToEntity(A dto);
	public abstract R convertEntityToDTO(T entity);

}
