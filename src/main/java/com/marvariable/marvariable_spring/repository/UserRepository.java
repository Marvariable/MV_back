package com.marvariable.marvariable_spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marvariable.marvariable_spring.entity.UserDetail;

public interface UserRepository extends JpaRepository<UserDetail, Integer> {
    Optional<UserDetail> findByEmail(String email);
}