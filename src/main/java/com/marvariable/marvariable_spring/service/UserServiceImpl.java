package com.marvariable.marvariable_spring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marvariable.marvariable_spring.repository.UserRepository;
import com.marvariable.marvariable_spring.security.CustomUserDetails;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public com.marvariable.marvariable_spring.entity.UserDetail register(
            com.marvariable.marvariable_spring.entity.UserDetail user) {

        if (userRepository.count() > 0) {
            throw new RuntimeException("The administrator already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.marvariable.marvariable_spring.entity.UserDetail user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Administrator not found"));

        return new CustomUserDetails(user);
    }
}