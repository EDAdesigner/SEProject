package com.example.seproject.controller;

import com.example.seproject.Service.CourseService;
import com.example.seproject.entity.Course;

import javax.swing.*;
import java.util.List;

@SuppressWarnings("serial")
public class CourseView extends JFrame {
	/*
	 * 学生查询课程，教师查询所教授课程
	 */
	
	JPanel contain;
	JTextArea list;

	public CourseView(String id, int flag) {
		super("课程");
		setSize(330, 400);
		contain = new JPanel();
		setLocation(600, 400);
		list = new JTextArea();
		list.setEditable(false);
		contain.add(list);
		list.append("课程编号\t课程名\t学分\t学时\n");

		CourseService courseService = new CourseService();

		if (flag == 0) { // 学生查询课程
			List<Course> courses = courseService.getCoursesByStudentId(id);
			for (Course course : courses) {
				list.append(course.getCourseId() + "\t");
				list.append(course.getCourseName() + "\t");
				list.append(course.getCredit() + "\t");
				list.append(course.getHour() + "\n");
			}
		} else if (flag == 1) { // 教师查询自己教授课程
			List<Course> courses = courseService.getCoursesByTeacherId(id);
			for (Course course : courses) {
				list.append(course.getCourseId() + "\t");
				list.append(course.getCourseName() + "\t");
				list.append(course.getCredit() + "\t");
				list.append(course.getHour() + "\n");
			}
		}

		add(contain);
		setVisible(true);
	}
}
