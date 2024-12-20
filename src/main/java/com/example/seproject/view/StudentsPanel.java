package com.example.seproject.view;

import com.example.seproject.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

@Component
@Lazy
public class StudentsPanel extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contain;
	private String id;
	private JButton infoButton, gradeButton, courseButton, editButton, selectCourseButton, dropCourseButton, exitButton;

	@Autowired
	private ApplicationContext applicationContext;

	public StudentsPanel() {
		// Default constructor for Spring
	}

	public void init(String id) {
		this.id = id;
		setTitle("学生");
		setLocation(300, 200);
		setSize(300, 400);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		infoButton = new JButton("信息查询");
		gradeButton = new JButton("成绩查询");
		courseButton = new JButton("课程查询");
		editButton = new JButton("修改信息");
		selectCourseButton = new JButton("选课");
		dropCourseButton = new JButton("退课");
		exitButton = new JButton("退出登录");

		infoButton.setBounds(70, 40, 140, 30);
		gradeButton.setBounds(70, 80, 140, 30);
		courseButton.setBounds(70, 120, 140, 30);
		editButton.setBounds(70, 160, 140, 30);
		selectCourseButton.setBounds(70, 200, 140, 30);
		dropCourseButton.setBounds(70, 240, 140, 30);
		exitButton.setBounds(70, 280, 140, 30);

		contain.add(infoButton);
		contain.add(gradeButton);
		contain.add(courseButton);
		contain.add(editButton);
		contain.add(selectCourseButton);
		contain.add(dropCourseButton);
		contain.add(exitButton);

		infoButton.addActionListener(this);
		gradeButton.addActionListener(this);
		courseButton.addActionListener(this);
		editButton.addActionListener(this);
		selectCourseButton.addActionListener(this);
		dropCourseButton.addActionListener(this);
		exitButton.addActionListener(this);

		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == infoButton) {
			Info info = applicationContext.getBean(Info.class);
			info.init(id, 0);
		}
		if (e.getSource() == gradeButton) {
			GradeInfo gradeInfo = applicationContext.getBean(GradeInfo.class);
			gradeInfo.init(id);
		}
		if (e.getSource() == courseButton) {
			CourseView courseView = applicationContext.getBean(CourseView.class);
			courseView.init(id, 0);
		}
		if (e.getSource() == editButton) {
			EditInfo editInfo = applicationContext.getBean(EditInfo.class);
			editInfo.init(id, 0);
		}
		if (e.getSource() == selectCourseButton) {
			new SelectCourse(id);
		}
		if (e.getSource() == dropCourseButton) {
			new DropCourse(id);
		}
		if (e.getSource() == exitButton) {
			this.dispose();
			// Use Spring to get MainFrame bean
			MainFrame mainFrame = ApplicationContextProvider.getApplicationContext().getBean(MainFrame.class);
			mainFrame.setVisible(true);
		}
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
			System.exit(0);
		}
	}
}