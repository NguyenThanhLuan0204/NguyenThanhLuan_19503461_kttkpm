package com.example.KhoaHoc_Springboot_CRUD.controller;
import com.example.KhoaHoc_Springboot_CRUD.entity.KhoaHoc;
import com.example.KhoaHoc_Springboot_CRUD.repository.KhoaHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/khoahoc")
public class KhoaHocController {

    @Autowired
    private KhoaHocRepository khoaHocRepository;

    @GetMapping("/{id}")
    public KhoaHoc getKhoaHoc(@PathVariable Long id) {
        try {
            return khoaHocRepository.findById(id)
                    .orElseThrow(() -> new Exception("KhoaHoc not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/")
    public List<KhoaHoc> getKhoaHocAll() {
        try {
            return khoaHocRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/")
    public KhoaHoc createKhoaHoc(@RequestBody KhoaHoc khoaHoc) {
        return khoaHocRepository.save(khoaHoc);
    }

    @PutMapping("/{id}")
    public KhoaHoc updateKhoaHoc(@PathVariable Long id, @RequestBody KhoaHoc khoaHocData) {
        KhoaHoc khoaHoc = null;
        try {
            khoaHoc = khoaHocRepository.findById(id)
                    .orElseThrow(() -> new Exception("KhoaHoc not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        khoaHoc.setNameCourse(khoaHocData.getNameCourse());
        khoaHoc.setNameTeacher(khoaHocData.getNameTeacher());
        khoaHoc.setOldprice(khoaHocData.getOldprice());
        khoaHoc.setNewprice(khoaHocData.getNewprice());
        khoaHoc.setTag1(khoaHocData.getTag1());
        khoaHoc.setTag2(khoaHocData.getTag2());
        khoaHoc.setImageCourse(khoaHocData.getImageCourse());
        khoaHoc.setImageTeacher(khoaHocData.getImageTeacher());
        khoaHoc.setDecription(khoaHocData.getDecription());


        return khoaHocRepository.save(khoaHoc);
    }

    @DeleteMapping("/{id}")
    public void deleteKhoaHoc(@PathVariable Long id) {
        khoaHocRepository.deleteById(id);
    }
}
