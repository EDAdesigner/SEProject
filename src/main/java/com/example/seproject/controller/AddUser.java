package com.example.seproject.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import com.example.seproject.entity.User;
import com.example.seproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@SuppressWarnings("serial")
@Component
@Lazy
public class AddUser extends JFrame implements ActionListener {
	/*
	 * 教务管理员添加用户，可以添加学生，教师，管理员
	 */
	JPanel contain;
	JLabel id, name, birthday, institute, major;
	JTextField idt, namet, birthdayt, institutet, majort;
	Checkbox check1, check2;
	CheckboxGroup group;
	JButton submit;
	Choice chooice;

	@Autowired
	private UserMapper userMapper;

	public AddUser() {
		super("添加用户");
		setSize(300, 350);
		setLocation(600, 400);
		contain = new JPanel();
		contain.setLayout(null);
		id = new JLabel("帐号");
		name = new JLabel("姓名");
		group = new CheckboxGroup();
		check1 = new Checkbox("male", group, true);
		check2 = new Checkbox("female", group, false);
		birthday = new JLabel("生日");
		institute = new JLabel("学院");
		major = new JLabel("专业");		
		
		submit = new JButton("提交");
		chooice = new Choice();
		chooice.addItem("student");
		chooice.addItem("teacher");
		chooice.addItem("administrator");
		
		idt = new JTextField();
		namet = new JTextField();
		
		birthdayt = new JTextField();
		institutet = new JTextField();
		majort = new JTextField();
		
		id.setBounds(42, 45, 75, 35);
		idt.setBounds(80, 45, 150, 35);
		// name.setBounds(40, 100, 75, 35);
		// namet.setBounds(80, 100, 150, 35);
		
		
		name.setBounds(42, 20, 75, 35);
		namet.setBounds(80, 20, 150, 35);
		check1.setBounds(80, 67, 80, 40);
		check2.setBounds(160, 67, 80, 40);
		birthday.setBounds(42, 100, 75, 35);
		birthdayt.setBounds(80, 100, 150, 35);
		institute.setBounds(40, 145, 75, 35);
		institutet.setBounds(80, 145, 150, 35);
		major.setBounds(40, 220, 75, 35);
		majort.setBounds(80, 220, 150, 35);
		
	
		chooice.setBounds(80, 180, 150, 35);
		submit.setBounds(102, 260, 70, 30);
		contain.add(id);
		contain.add(idt);
		contain.add(name);
		contain.add(namet);
		
		contain.add(birthday);
		contain.add(birthdayt);
		contain.add(institute);
		contain.add(institutet);
		contain.add(major);
		contain.add(majort);
		contain.add(check1);
		contain.add(check2);
		
		contain.add(chooice);
		contain.add(submit);
		submit.addActionListener(this);
		add(contain);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			if ((idt.getText().equals("")) || (namet.getText().equals("")) || (birthdayt.getText().equals(""))
					|| (institutet.getText().equals("")) || (majort.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "信息不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				String ch = (String) chooice.getSelectedItem();
				try {
					if (ch.equals("student")) {
						if (checkIfUserExists("student", idt.getText())) {
							JOptionPane.showMessageDialog(null, "此学生已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
						} else {
							addUserToDatabase("student", idt.getText(), namet.getText(), getSelectedGender(), birthdayt.getText(), institutet.getText(), majort.getText());
							JOptionPane.showMessageDialog(null, "成功添加一个学生！", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (ch.equals("teacher")) {
						if (checkIfUserExists("teacher", idt.getText())) {
							JOptionPane.showMessageDialog(null, "此教师已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
						} else {
							addUserToDatabase("teacher", idt.getText(), namet.getText(), getSelectedGender(), birthdayt.getText(), institutet.getText(), majort.getText());
							JOptionPane.showMessageDialog(null, "成功添加一个老师！", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						if (checkIfUserExists("administrator", idt.getText())) {
							JOptionPane.showMessageDialog(null, "此教务员已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
						} else {
							addUserToDatabase("administrator", idt.getText(), namet.getText(), getSelectedGender(), birthdayt.getText(), institutet.getText(), majort.getText());
							JOptionPane.showMessageDialog(null, "成功添加一个教务员！", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "添加失败", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public boolean checkIfUserExists(String userType, String userId) {
		// Implement the logic to check if the user exists in the database
		// This is a placeholder implementation and should be replaced with actual database query logic
		try {
			User user = userMapper.findUserByIdAndType(userId, userType);
			return user != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void addUserToDatabase(String userType, String userId, String name, String gender, String birthday, String institute, String major) {
		try {
			userMapper.insertUser(new User(userId, "123456" ,name, gender, birthday, institute, major, userType));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error adding user to database", e);
		}
	}

	public String getSelectedGender() {
		return group.getSelectedCheckbox().getLabel();
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
		}
	}
}
