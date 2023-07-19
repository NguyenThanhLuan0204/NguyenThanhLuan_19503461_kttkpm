package com.SpringBoot_JWT.controller;

import com.SpringBoot_JWT.authen.UserPrincipal;
import com.SpringBoot_JWT.entity.Role;
import com.SpringBoot_JWT.entity.Token;
import com.SpringBoot_JWT.entity.User;
import com.SpringBoot_JWT.service.TokenService;
import com.SpringBoot_JWT.service.UserService;
import com.SpringBoot_JWT.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        long millis = System.currentTimeMillis();
        user.setCreateAt(new Date(millis));
        user.setUpdateAt(new Date(millis));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1));
        user.setRoles(roles);
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        UserPrincipal userPrincipal =
                userService.findByUsername(user.getUsername());

        if (null == user || !new BCryptPasswordEncoder()
                .matches(user.getPassword(), userPrincipal.getPassword())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Account or password is not valid!");
        }

        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));

        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreateBy(userPrincipal.getUserId());
        tokenService.createToken(token);

        return ResponseEntity.ok(token.getToken());
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity hello() {
        return ResponseEntity.ok("hello");
    }

    @PutMapping("/user")
    @PreAuthorize("hasAnyAuthority('USER_UPDATE')")
    public ResponseEntity update() {
        return ResponseEntity.ok("This is update");
    }

//
//    Object principal = SecurityContextHolder
//            .getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//        UserPrincipal userPrincipal = (UserPrincipal) principal;
//    }

}