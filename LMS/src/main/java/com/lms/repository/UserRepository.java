package com.lms.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lms.entity.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByUserId(String userId);
}
