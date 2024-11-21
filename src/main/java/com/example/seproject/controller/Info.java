// src/main/java/com/example/seproject/controller/Info.java
package com.example.seproject.controller;

import com.example.seproject.Service.UserService;
import com.example.seproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

@Service
@Lazy
public class Info extends JFrame {
	private static final long serialVersionUID = 1L;
	JLabel idLabel, nameLabel, genderLabel, birLabel, insLabel, majorLabel;
	String id, name, pwd, gender, birthday, institute, major;
	JPanel stuInfoJPanel;

	User stu;
	User t;

	@Autowired
	UserService userService;

	public Info() {
		super("信息");
	}

	public void init(String idd, int flag) {
		this.id = idd;
		setTitle("信息");
		setSize(300, 340);
		setLocation(600, 400);
		stuInfoJPanel = new JPanel();
		stuInfoJPanel.setLayout(new GridLayout(20, 1));
		add(stuInfoJPanel);

		try {
			User user = userService.findUserByIdAndType(idd, flag == 1 ? "teacher" : "student");
			if (user != null) {
				id = user.getId();
				pwd = user.getPassword();
				name = user.getName();
				gender = user.getGender();
				birthday = user.getBirthday();
				institute = user.getInstitute();
				major = user.getMajor();

				if (flag == 1) {
					t = new User(id, pwd, name, gender, birthday, institute, major, "teacher");
					idLabel = new JLabel("账号:" + t.getId());
					nameLabel = new JLabel("姓名:" + t.getName());
					genderLabel = new JLabel("性别:" + t.getGender());
					birLabel = new JLabel("生日:" + t.getBirthday());
					insLabel = new JLabel("学院:" + t.getInstitute());
					majorLabel = new JLabel("系别:" + t.getMajor());
				} else {
					stu = new User(id, pwd, name, gender, birthday, institute, major, "student");
					idLabel = new JLabel("账号:" + stu.getId());
					nameLabel = new JLabel("姓名:" + stu.getName());
					genderLabel = new JLabel("性别:" + stu.getGender());
					birLabel = new JLabel("生日:" + stu.getBirthday());
					insLabel = new JLabel("学院:" + stu.getInstitute());
					majorLabel = new JLabel("系别:" + stu.getMajor());
				}

				stuInfoJPanel.add(idLabel);
				stuInfoJPanel.add(nameLabel);
				stuInfoJPanel.add(genderLabel);
				stuInfoJPanel.add(birLabel);
				stuInfoJPanel.add(insLabel);
				stuInfoJPanel.add(majorLabel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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