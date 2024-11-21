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
public class TeachersPanel extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String idd;
	private JPanel contain;
	private JButton infoButton, gradeButton, courseButton, editButton, courseView, sortGrade, exitButton;

	@Autowired
	private ApplicationContext applicationContext;


	public TeachersPanel() {
		// Default constructor for Spring
	}

	public void init(String idd) {
		this.idd = idd;
		setTitle("教师");
		setLocation(300, 200);
		setSize(300, 340);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		infoButton = new JButton("信息查询");
		gradeButton = new JButton("成绩登录");
		courseButton = new JButton("全部课程");
		editButton = new JButton("修改信息");
		courseView = new JButton("开课");
		exitButton = new JButton("退出登录");
		sortGrade = new JButton("成绩统计");

		infoButton.setBounds(70, 40, 140, 30);
		editButton.setBounds(70, 80, 140, 30);
		courseView.setBounds(70, 120, 140, 30);
		courseButton.setBounds(70, 160, 140, 30);
		gradeButton.setBounds(70, 200, 140, 30);
		sortGrade.setBounds(70, 240, 140, 30);
		exitButton.setBounds(70, 280, 140, 30);

		contain.add(infoButton);
		infoButton.addActionListener(this);
		contain.add(gradeButton);
		gradeButton.addActionListener(this);
		contain.add(courseView);
		courseView.addActionListener(this);
		contain.add(courseButton);
		courseButton.addActionListener(this);
		contain.add(editButton);
		editButton.addActionListener(this);
		contain.add(sortGrade);
		sortGrade.addActionListener(this);
		contain.add(exitButton);
		exitButton.addActionListener(this);

		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == infoButton) {
			Info info = applicationContext.getBean(Info.class);
			info.init(idd, 1);
		}
		if (e.getSource() == gradeButton) {
			GradeEnter gradeEnter = applicationContext.getBean(GradeEnter.class);
			gradeEnter.init(idd);
		}
		if (e.getSource() == courseButton) {
			CourseView courseView = applicationContext.getBean(CourseView.class);
			courseView.init(idd, 1);
		}
		if (e.getSource() == editButton) {
			EditInfo editInfo = applicationContext.getBean(EditInfo.class);
			editInfo.init(idd, 1);
		}
		if (e.getSource() == courseView) {
			AddCourse addCourse = applicationContext.getBean(AddCourse.class);
			addCourse.init(idd);
		}
		if (e.getSource() == sortGrade) {
			SortGradeFrame sortGradeFrame = applicationContext.getBean(SortGradeFrame.class);
			sortGradeFrame.init();
		}
		if (e.getSource() == exitButton) {
			this.dispose();
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