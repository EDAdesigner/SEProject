package com.example.seproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnrollmentMapper {

    @Insert("INSERT INTO enrollments (student_id, course_id) VALUES (#{studentId}, #{courseId})")
    void insertEnrollment(String studentId, String courseId);
}
