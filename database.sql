create database project;
use project;
-- 创建 courses 表
CREATE TABLE courses (
                         course_id VARCHAR(50) PRIMARY KEY,
                         course_name VARCHAR(100),
                         credit INT,
                         class_hours INT,
                         teacher_id VARCHAR(50),
                         teacher_name VARCHAR(100)
);

-- 创建 user 表
CREATE TABLE user (
                      id VARCHAR(50) PRIMARY KEY,
                      password VARCHAR(100) NOT NULL,
                      name VARCHAR(100) NOT NULL,
                      gender VARCHAR(10) NOT NULL,
                      birthday VARCHAR(30) NOT NULL,
                      institute VARCHAR(100) NOT NULL,
                      major VARCHAR(100) NOT NULL,
                      type VARCHAR(20) NOT NULL
);

-- 创建 enrollments 表
CREATE TABLE enrollments (
                             enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
                             student_id VARCHAR(50),
                             course_id VARCHAR(50),
                             FOREIGN KEY (student_id) REFERENCES user(id),
                             FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

-- 创建 grade 表
CREATE TABLE grade (
                       grade_id INT AUTO_INCREMENT PRIMARY KEY,
                       student_id VARCHAR(50) NOT NULL,
                       course_id VARCHAR(50) NOT NULL,
                       grade FLOAT,
                       FOREIGN KEY (student_id) REFERENCES user(user_id),
                       FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

