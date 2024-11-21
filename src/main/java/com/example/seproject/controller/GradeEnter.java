package com.example.seproject.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.seproject.Service.CourseService;
import com.example.seproject.Service.GradeService;
import com.example.seproject.mapper.UserMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
@Lazy
@NoArgsConstructor
public class GradeEnter extends JFrame implements ActionListener {
	/*
	 * 教师登陆课程信息
	 */
	String idd;  // 教师号
	JPanel contain;
	JLabel id;
	JTextField idt, stuIdt, stuGradet, stuNamet;

	JButton submit, bn;

	@Autowired
	private GradeService gradeService;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			if (hasThisCourse(idt.getText()) == 1) {
				enter();   // 进入成绩输入界面

			} else {
				JOptionPane.showMessageDialog(null, "您未开设此课程！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (e.getSource() == bn) {
			if (hasThisStu() == 1) {   // 登陆成绩
				try {
					boolean success = gradeService.updateGrade(idt.getText(), stuIdt.getText(), stuNamet.getText(), stuGradet.getText());

					if (success) {
						JOptionPane.showMessageDialog(null, "成绩登录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "成绩登录失败！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "成绩登录过程中发生错误！", "提示", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "课程号为" + idt.getText() + "无此学生", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	//判断是否有学生
	int hasThisStu() {
		return gradeService.hasThisStu(stuIdt);
	}

	void enter() {
		JFrame fm = new JFrame("登录成绩");
		fm.setSize(300, 340);
		JPanel contain = new JPanel();
		fm.setLocation(600, 400);
		contain.setLayout(null);
		bn = new JButton("提交");
		JLabel stuId = new JLabel("学号");
		JLabel stuGrade = new JLabel("成绩");
		JLabel stuName = new JLabel("姓名");
		
		stuIdt = new JTextField();
		stuGradet = new JTextField();
		stuNamet = new JTextField();
		
		stuId.setBounds(38, 50, 75, 35);
		stuIdt.setBounds(80, 50, 150, 35);
		
		stuGrade.setBounds(38, 110, 75, 35);
		stuGradet.setBounds(80, 110, 150, 35);
		
		stuName.setBounds(38, 170, 75, 35);
		stuNamet.setBounds(80, 170, 150, 35);
		
		bn.setBounds(170, 220, 70, 30);
		contain.add(stuId);
		contain.add(stuIdt);
		contain.add(stuGrade);
		contain.add(stuGradet);
		contain.add(stuName);
		contain.add(stuNamet);
		contain.add(bn);
		fm.add(contain);
		bn.addActionListener(this);
		
	
		fm.setVisible(true);
		
	}

	@Autowired
	private CourseService courseService;

	int hasThisCourse(String courseId) {

		return courseService.hasThisCourse(courseId) ? 1 : 0;
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
		}
	}

	public void init(String idd) {
		this.idd = idd;
		setTitle("查看");
		setSize(300, 340);
		setLocation(600, 400);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		id = new JLabel("课程号");
		idt = new JTextField();
		submit = new JButton("提交");
		id.setBounds(38, 50, 75, 35);
		idt.setBounds(80, 50, 150, 35);
		submit.setBounds(102, 125, 70, 30);
		contain.add(id);
		contain.add(idt);
		contain.add(submit);
		submit.addActionListener(this);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}
}
