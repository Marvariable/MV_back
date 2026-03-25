package com.marvariable.marvariable_spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.marvariable.marvariable_spring.entity.UserDetail;


public interface UserService extends UserDetailsService {
    UserDetail register(UserDetail user);
}