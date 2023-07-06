package com.example.KhoaHoc_Springboot_CRUD.repository;

import com.example.KhoaHoc_Springboot_CRUD.entity.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Long> {

}
