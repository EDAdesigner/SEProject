package com.example.seproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    private int enrollmentId;
    private String studentId;
    private String courseId;

}