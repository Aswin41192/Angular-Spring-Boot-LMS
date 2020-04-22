package com.lms.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lms.entity.CourseEntity;

public interface CourseRepository extends PagingAndSortingRepository<CourseEntity, Long> {
	Optional<CourseEntity> findByCourseId(String courseId);
	boolean existsByCourseId(String courseId);
}
