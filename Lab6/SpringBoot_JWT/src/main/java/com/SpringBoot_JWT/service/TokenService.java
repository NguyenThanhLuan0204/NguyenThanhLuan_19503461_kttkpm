package com.SpringBoot_JWT.service;

import com.SpringBoot_JWT.entity.Token;

public interface TokenService {
    Token createToken(Token token);

    Token findByToken(String token);
}
