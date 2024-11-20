package com.example.seproject.controller;

import com.example.seproject.Service.GradeService;
import com.example.seproject.entity.Grade;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GradeInfo extends JFrame { 
	/**
	 * 学生根据学号查询所有成绩
	 */
	private static final long serialVersionUID = 1L;
	JPanel contain;
	JTextArea list;
	String id;

	GradeService gradeService = new GradeService();
	

	public GradeInfo(String id) {
		super("课程");
		this.id = id;
		setSize(600, 400);
		contain = new JPanel();
		setLocation(600, 400);
		list = new JTextArea();
		list.setEditable(false);
		contain.add(list);
		
		list.append("课程号" + "\t");
		list.append("课程名" + "\t");
		list.append("教师工号" + "\t");
		list.append("教师姓名" + "\t");
		list.append("学号" + "\t");
		list.append("学生姓名" + "\t");
		list.append("成绩" + "\n");

		try {
			List<Grade> grades = gradeService.getGradesByStudentId(id);
			for (Grade grade : grades) {
				list.append(grade.getCourseId() + "\t");
				list.append(grade.getCourseName() + "\t");
				list.append(grade.getTeacherId() + "\t");
				list.append(grade.getTeacherName() + "\t");
				list.append(grade.getStudentId() + "\t");
				list.append(grade.getStudentName() + "\t");
				list.append(grade.getGrade() + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		add(contain);
		setVisible(true);
	}
}
