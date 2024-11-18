package com.example.seproject.controller;

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
            } else if (!courseExists(courseId)) {
                JOptionPane.showMessageDialog(null, "课程号不存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String file = System.getProperty("user.dir") + "/data/course_student" + courseId + "_student.txt";
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                    bw.write(studentId);
                    bw.newLine();
                    JOptionPane.showMessageDialog(null, "选课成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private boolean courseExists(String courseId) {
        String file = System.getProperty("user.dir") + "/data/course.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[0].equals(courseId)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }
}