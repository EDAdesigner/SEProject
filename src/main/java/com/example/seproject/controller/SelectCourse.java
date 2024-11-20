package com.example.seproject.controller;

import com.example.seproject.Service.CourseService;
import com.example.seproject.Service.EnrollmentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;

@SuppressWarnings("serial")
public class SelectCourse extends JFrame implements ActionListener {
    JPanel contain;
    JButton submit;
    JLabel courseIdLabel;
    JTextField courseIdField;
    String studentId;

    CourseService courseService = new CourseService();
    EnrollmentService enrollmentService = new EnrollmentService();

    public SelectCourse(String studentId) {
        super("选课");
        this.studentId = studentId;
        setSize(300, 200);
        setLocation(600, 400);
        contain = new JPanel();
        contain.setLayout(null);
        courseIdLabel = new JLabel("课程号");
        courseIdField = new JTextField();
        submit = new JButton("提交");

        courseIdLabel.setBounds(42, 45, 75, 35);
        courseIdField.setBounds(80, 45, 150, 35);
        submit.setBounds(102, 100, 70, 30);

        contain.add(courseIdLabel);
        contain.add(courseIdField);
        contain.add(submit);
        submit.addActionListener(this);
        add(contain);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {
            String courseId = courseIdField.getText();
            if (courseId.equals("")) {
                JOptionPane.showMessageDialog(null, "课程号不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else if (!courseService.courseExists(courseId)) {
                JOptionPane.showMessageDialog(null, "课程号不存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                boolean success = enrollmentService.enrollStudent(studentId, courseId);
                if (success) {
                    JOptionPane.showMessageDialog(null, "选课成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "选课失败！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private boolean courseExists(String courseId) {
        return courseService.courseExists(courseId);
    }

    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }
}