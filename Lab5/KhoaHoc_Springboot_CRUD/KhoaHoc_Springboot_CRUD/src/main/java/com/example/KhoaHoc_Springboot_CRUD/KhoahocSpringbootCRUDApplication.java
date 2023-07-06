package com.example.KhoaHoc_Springboot_CRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KhoahocSpringbootCRUDApplication {

    public static void main(String[] args) {

        SpringApplication.run(KhoahocSpringbootCRUDApplication.class, args);
    }

}
