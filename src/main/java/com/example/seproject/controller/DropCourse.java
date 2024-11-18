package com.example.seproject.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DropCourse extends JFrame implements ActionListener {
    JPanel contain;
    JButton submit;
    JLabel courseIdLabel;
    JTextField courseIdField;
    String studentId;

    public DropCourse(String studentId) {
        super("退课");
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
            } else {
                String file = System.getProperty("user.dir") + "/data/course_student" + courseId + "_student.txt";
                ArrayList<String> students = new ArrayList<>();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String s;
                    while ((s = br.readLine()) != null) {
                        if (!s.equals(studentId)) {
                            students.add(s);
                        }
                    }
                    br.close();

                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    for (String student : students) {
                        bw.write(student);
                        bw.newLine();
                    }
                    bw.close();
                    JOptionPane.showMessageDialog(null, "退课成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            setVisible(false);
        }
    }
}