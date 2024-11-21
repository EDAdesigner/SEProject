package com.example.seproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Data
public class Course {
	
	private String courseId;
	private String courseName;
	private String teacherId;
	private String teacherName;
	private Integer credit;
	private Integer classHours;
	private float fail, pass, good, excellent;
	
	public Course(String courseId, float pass, float good, float excellent) {
		super();
		this.courseId = courseId;
		this.pass = pass;
		this.good = good;
		this.excellent = excellent;
	}

	public Course(String courseId, String courseName, String teacherId,
			String teacherName, Integer credit, Integer hour) {

		this.courseId = courseId;
		this.courseName = courseName;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.credit = credit;
		this.classHours = hour;
	}

	public Course(String courseId, String courseName, String teacherId,
			String teacherName, float fial, float pass, float good,
			float excellent) {

		this.courseId = courseId;
		this.courseName = courseName;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.fail = fial;
		this.pass = pass;
		this.good = good;
		this.excellent = excellent;
	}
}
