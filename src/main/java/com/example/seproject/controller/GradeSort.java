package com.example.seproject.controller;

import com.example.seproject.Service.CourseService;
import com.example.seproject.Service.GradeService;
import com.example.seproject.entity.Course;
import com.example.seproject.entity.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Lazy
public class GradeSort {
	/*
	 * 教师查询学生成绩统计结果功能
	 */

	private Course course;

	@Autowired
	private CourseService courseService;

	@Autowired
	private GradeService gradeService;

	public GradeSort() {
		// 无参构造函数
	}

	public int isValidate(float pass, float good, float excellent) { // 输入的成绩标准是否是在正常内[0, 100],以及pass<good<excellent
		if (pass < 0 || pass > 100 || good < 0 || good > 100 || excellent < 0 || excellent > 100
				|| pass >= good || pass >= excellent || good >= excellent) {
			return 1;
		}
		return 0;
	}

	public int hasCourse(String courseId){
		return courseService.hasThisCourse(courseId) ? 1 : 0;
	}


	public int[] sortGrade(String courseId, float pass, float good, float excellent) {
		int failCount = 0, passCount = 0, goodCount = 0, excellentCount = 0;

		List<Grade> grades = gradeService.getGradesByCourseId(courseId);

		for (Grade grade : grades) {
			float gradeValue = grade.getGrade();
			if (gradeValue < pass) {
				failCount++;
			} else if (gradeValue < good) {
				passCount++;
			} else if (gradeValue < excellent) {
				goodCount++;
			} else {
				excellentCount++;
			}
		}

		return new int[]{failCount, passCount, goodCount, excellentCount};
	}
}