package com.example.KhoaHoc_Springboot_CRUD.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class KhoaHoc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nameCourse;
	private String nameTeacher;
	private String oldprice;
	private String newprice;
	private String tag1;
	private String tag2;
	private String decription;
	private String imageCourse;
	private String imageTeacher;


	// Constructors

	public KhoaHoc(Long id, String nameCourse, String nameTeacher, String oldprice, String newprice, String tag1, String tag2, String decription, String imageCourse, String imageTeacher) {
		this.id = id;
		this.nameCourse = nameCourse;
		this.nameTeacher = nameTeacher;
		this.oldprice = oldprice;
		this.newprice = newprice;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.decription = decription;
		this.imageCourse = imageCourse;
		this.imageTeacher = imageTeacher;
	}

	public KhoaHoc() {
	}
// Getter and Setter methods

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameCourse() {
		return nameCourse;
	}

	public void setNameCourse(String nameCourse) {
		this.nameCourse = nameCourse;
	}

	public String getNameTeacher() {
		return nameTeacher;
	}

	public void setNameTeacher(String nameTeacher) {
		this.nameTeacher = nameTeacher;
	}

	public String getOldprice() {
		return oldprice;
	}

	public void setOldprice(String oldprice) {
		this.oldprice = oldprice;
	}

	public String getNewprice() {
		return newprice;
	}

	public void setNewprice(String newprice) {
		this.newprice = newprice;
	}

	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public String getImageCourse() {
		return imageCourse;
	}

	public void setImageCourse(String imageCourse) {
		this.imageCourse = imageCourse;
	}

	public String getImageTeacher() {
		return imageTeacher;
	}

	public void setImageTeacher(String imageTeacher) {
		this.imageTeacher = imageTeacher;
	}

}
