package com.SpringBoot_JWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot_JWT.entity.User;

public interface UserRepository
        extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
