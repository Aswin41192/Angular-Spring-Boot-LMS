package com.lms.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lms.entity.CoursePartEntity;

@Repository
public interface CoursePartRepository extends PagingAndSortingRepository<CoursePartEntity, Long> {
	Optional<CoursePartEntity> findByPartId(String partId);
	Optional<Iterable<CoursePartEntity>> findByCourseId(Long courseId);
}
