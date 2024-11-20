package com.example.seproject.Service;

import com.example.seproject.entity.Course;
import com.example.seproject.mapper.CourseMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public List<Course> getCoursesByStudentId(String studentId) {
        return courseMapper.findCoursesByStudentId(studentId);
    }

    public List<Course> getCoursesByTeacherId(String teacherId) {
        return courseMapper.findCoursesByTeacherId(teacherId);
    }

    public Course findCourseById(String courseId) {
        return courseMapper.findCourseById(courseId);
    }

    public void insertCourse(Course course) {
        courseMapper.insertCourse(course);
    }

    public boolean dropCourse(String studentId, String courseId) {
        try {
            courseMapper.deleteStudentFromCourse(studentId, courseId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasThisCourse(String courseId) {
        try {
            return courseMapper.findCourseById(courseId) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean courseExists(String courseId) {
        return courseMapper.findCourseById(courseId) != null;
    }
}