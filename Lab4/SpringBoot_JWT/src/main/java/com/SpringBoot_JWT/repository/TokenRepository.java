package com.SpringBoot_JWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot_JWT.entity.Token;

public interface TokenRepository
        extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
