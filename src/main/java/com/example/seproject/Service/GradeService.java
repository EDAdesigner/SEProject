package com.example.seproject.Service;

import com.example.seproject.entity.Grade;
import com.example.seproject.entity.User;
import com.example.seproject.mapper.GradeMapper;
import com.example.seproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private UserMapper userMapper;

    public boolean updateGrade(String gradeId, String studentId, String courseId, String grade) {
        try {
            int rowsUpdated = gradeMapper.updateGrade(studentId, courseId, grade, gradeId);
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int hasThisStu(JTextField stuIdt) {
        try {
            User student = userMapper.findUserByIdAndType(stuIdt.getText(), "student");
            return student != null ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Grade> getGradesByCourseId(String courseId) {
        return gradeMapper.findGradesByCourseId(courseId);
    }

    public List<Grade> getGradesByStudentId(String studentId) {
        return gradeMapper.findGradesByStudentId(studentId);
    }
}
