package com.example.seproject.mapper;

import com.example.seproject.entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GradeMapper {

    @Update("UPDATE grades SET grade = #{grade} WHERE student_id = #{studentId} AND course_id = #{courseId} AND grade_id = #{gradeID}")
    int updateGrade(String studentId, String courseId, String grade, String semester);

    @Select("SELECT g.course_id, c.course_name, c.teacher_id, t.name as teacher_name, g.student_id, s.name as student_name, g.grade " +
            "FROM grade g " +
            "JOIN courses c ON g.course_id = c.course_id " +
            "JOIN user t ON c.teacher_id = t.user_id " +
            "JOIN user s ON g.student_id = s.user_id " +
            "WHERE g.student_id = #{studentId}")
    List<Grade> findGradesByStudentId(String studentId);
}
