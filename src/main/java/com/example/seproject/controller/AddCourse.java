package com.example.seproject.controller;


import com.example.seproject.entity.Course;
import com.example.seproject.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


@SuppressWarnings("serial")
@Service
@Lazy
public class AddCourse extends JFrame implements ActionListener {
	/*
	 * 教师增加课程
	 */

	@Autowired
	private CourseMapper courseMapper;
	
	JPanel contain;
	JButton submit;
	JLabel id, name, gredit, classH, teacherId, teacherName;
	JTextField idt, namet, greditt, classHt, teacherIdt, teacherNamet;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			if ((idt.getText().equals("")) || (namet.getText().equals("")) || (greditt.getText().equals(""))
					|| (classHt.getText().equals("")) || teacherIdt.getText().equals("") || teacherNamet.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "信息不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				try {
					Course existingCourse = courseMapper.findCourseById(idt.getText());
					if (existingCourse != null) {
						JOptionPane.showMessageDialog(null, "此课程已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
					} else {
						Course course = new Course(idt.getText(), namet.getText(), greditt.getText(), classHt.getText(), Integer.parseInt(teacherIdt.getText()), Integer.parseInt(teacherNamet.getText()));;
						courseMapper.insertCourse(course);
						JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "添加失败,请检查学号是否重复", "错误", JOptionPane.ERROR_MESSAGE);
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

	public void init(String idd) {
		setTitle("增加课程");
		setSize(400, 400);
		setLocation(600, 400);
		contain = new JPanel();
		contain.setLayout(null);
		id = new JLabel("课程号");
		name = new JLabel("课程名");
		gredit = new JLabel("学分");
		classH = new JLabel("学时");
		teacherId = new JLabel("教师");
		teacherName = new JLabel("教师号");
		submit = new JButton("提交");
		idt = new JTextField();
		namet = new JTextField();
		greditt = new JTextField();
		classHt = new JTextField();
		teacherIdt = new JTextField();
		teacherNamet = new JTextField();

		id.setBounds(42, 35, 75, 35);
		idt.setBounds(80, 35, 150, 35);
		name.setBounds(40, 90, 75, 35);
		namet.setBounds(80, 90, 150, 35);
		gredit.setBounds(45, 145, 75, 35);
		greditt.setBounds(80, 145, 150, 35);
		classH.setBounds(45, 200, 75, 35);
		classHt.setBounds(80, 200, 150, 35);
		teacherId.setBounds(45, 245, 75, 35);
		teacherIdt.setBounds(85, 245, 150, 35);
		teacherName.setBounds(45, 280, 75, 35);
		teacherNamet.setBounds(80, 280, 75, 35);
		submit.setBounds(102, 320, 70, 30);

		contain.add(id);
		contain.add(idt);
		contain.add(name);
		contain.add(namet);
		contain.add(gredit);
		contain.add(greditt);
		contain.add(classH);
		contain.add(classHt);
		contain.add(teacherId);
		contain.add(teacherIdt);
		contain.add(teacherName);
		contain.add(teacherNamet);
		contain.add(submit);

		submit.addActionListener(this);
		add(contain);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}
}
