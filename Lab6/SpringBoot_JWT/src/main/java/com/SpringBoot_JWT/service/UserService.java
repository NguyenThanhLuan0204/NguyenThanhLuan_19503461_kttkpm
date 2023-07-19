package com.SpringBoot_JWT.service;

import com.SpringBoot_JWT.authen.UserPrincipal;
import com.SpringBoot_JWT.entity.User;

public interface UserService {
    User createUser(User user);
    UserPrincipal findByUsername(String username);
}
