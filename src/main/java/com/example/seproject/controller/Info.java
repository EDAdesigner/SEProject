package com.example.seproject.controller;

import com.example.seproject.Service.UserService;
import com.example.seproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

public class Info extends JFrame {    
	/**
	 * 用户查询个人信息
	 */
	private static final long serialVersionUID = 1L;
	JLabel idLabel, nameLabel, genderLabel, birLabel, insLabel, majorLabel;
	String id, name, pwd, gender, birthday, institute, major;
	JPanel stuInfoJPanel;
	
	User stu;
	User t;
	UserService userService = new UserService();

	public Info(String id, int flag) {
		super("信息");
		this.id = id;
		setSize(300, 340);
		setLocation(600, 400);
		stuInfoJPanel = new JPanel();
		stuInfoJPanel.setLayout(new GridLayout(20, 1));
		add(stuInfoJPanel);

		try {
			User user = userService.findUserByIdAndType(id, flag == 1 ? "student" : "teacher");
			if (user != null) {
				id = user.getId();
				pwd = user.getPwd();
				name = user.getName();
				gender = user.getGender();
				birthday = user.getBirthday();
				institute = user.getInstitute();
				major = user.getMajor();

				if (flag == 1) {
					stu = new User(id, pwd, name, gender, birthday, institute, major, "student");
					idLabel = new JLabel("账号:" + stu.getId());
					nameLabel = new JLabel("姓名:" + stu.getName());
					genderLabel = new JLabel("性别:" + stu.getGender());
					birLabel = new JLabel("生日:" + stu.getBirthday());
					insLabel = new JLabel("学院:" + stu.getInstitute());
					majorLabel = new JLabel("系别:" + stu.getMajor());
				} else {
					t = new User(id, pwd, name, gender, birthday, institute, major, "teacher");
					idLabel = new JLabel("账号:" + t.getId());
					nameLabel = new JLabel("姓名:" + t.getName());
					genderLabel = new JLabel("性别:" + t.getGender());
					birLabel = new JLabel("生日:" + t.getBirthday());
					insLabel = new JLabel("学院:" + t.getInstitute());
					majorLabel = new JLabel("系别:" + t.getMajor());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		stuInfoJPanel.add(idLabel);
		stuInfoJPanel.add(nameLabel);
		stuInfoJPanel.add(genderLabel);
		stuInfoJPanel.add(birLabel);
		stuInfoJPanel.add(insLabel);
		stuInfoJPanel.add(majorLabel);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		setVisible(true);
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
		}
	}
}
