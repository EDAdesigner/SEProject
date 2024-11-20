package com.example.seproject.Service;

import com.example.seproject.mapper.EnrollmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentMapper enrollmentMapper;

    public boolean enrollStudent(String studentId, String courseId) {
        try {
            enrollmentMapper.insertEnrollment(studentId, courseId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}