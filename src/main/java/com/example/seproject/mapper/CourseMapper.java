package com.example.seproject.mapper;

import com.example.seproject.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("select * from courses where id = #{courseId}")
    Course findCourseById(String id);

    @Insert("INSERT INTO courses (course_id, course_name, credit, class_hours, teacher_id, teacher_name) VALUES (#{courseId}, #{courseName}, #{credit}, #{classHours}, #{teacherId}, #{teacherName})")
    void insertCourse(Course course);

    @Select("SELECT c.course_id, c.course_name, c.credit, c.class_hour FROM courses c JOIN enrollment e ON c.course_id = e.course_id WHERE e.student_id = #{studentId}")
    List<Course> findCoursesByStudentId(String studentId);

    @Select("SELECT course_id, course_name, credit, class_hour FROM courses WHERE teacher_id = #{teacherId}")
    List<Course> findCoursesByTeacherId(String teacherId);

    @Delete("DELETE FROM enrollment WHERE student_id = #{studentId} AND course_id = #{courseId}")
    void deleteStudentFromCourse(String studentId, String courseId);

}