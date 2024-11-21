package com.example.seproject.controller;

import com.example.seproject.Service.CourseService;
import com.example.seproject.entity.Course;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@SuppressWarnings("serial")
@Service
@Lazy
@NoArgsConstructor
public class CourseView extends JFrame {
	/*
	 * 学生查询课程，教师查询所教授课程
	 */
	
	JPanel contain;
	JTextArea list;

	@Autowired
	CourseService courseService;

	public void init(String id, int flag) {
		setTitle("课程");
		setSize(330, 400);
		contain = new JPanel();
		setLocation(600, 400);
		list = new JTextArea();
		list.setEditable(false);
		contain.add(list);
		list.append("课程编号\t课程名\t学分\t学时\n");

		if (flag == 0) { // 学生查询课程
			List<Course> courses = courseService.getCoursesByStudentId(id);
			for (Course course : courses) {
				list.append(course.getCourseId() + "\t");
				list.append(course.getCourseName() + "\t");
				list.append(course.getCredit() + "\t");
				list.append(course.getClassHours() + "\n");
			}
		} else if (flag == 1) { // 教师查询自己教授课程
			List<Course> courses = courseService.getCoursesByTeacherId(id);
			for (Course course : courses) {
				list.append(course.getCourseId() + "\t");
				list.append(course.getCourseName() + "\t");
				list.append(course.getCredit() + "\t");
				list.append(course.getClassHours() + "\n");
			}
		}

		add(contain);
		setVisible(true);
	}
}
