package com.example.seproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private int gradeId;
    private String studentId;
    private String courseId;
    private float grade;
    private String courseName;
    private String teacherId;
    private String teacherName;
    private String studentName;
}
