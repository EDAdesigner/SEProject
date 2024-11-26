package com.example.seproject.controller;

import com.example.seproject.Service.UserService;
import com.example.seproject.entity.User;
import com.example.seproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

@Service
@Lazy
public class AddUser extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField idt, namet, birthdayt, institutet, majort;
	private JLabel id, name, birthday, institute, major;
	private JButton submit;
	private JPanel contain;
	private Choice chooice;
	private CheckboxGroup group;
	private Checkbox check1, check2;

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userService;


	public AddUser() {
	}

	@Override
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
							JOptionPane.showMessageDialog(null, "成功添加一个教师！", "提示", JOptionPane.INFORMATION_MESSAGE);
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
			userMapper.insertUser(new User(userId,userService.encode("123456"), name, gender, birthday, institute, major, userType));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error adding user to database", e);
		}
	}

	public String getSelectedGender() {
		return group.getSelectedCheckbox().getLabel();
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
		}
	}

	public void init() {
		setTitle("添加用户");
		setLocation(300, 200);
		setSize(300, 340);
		contain = new JPanel();
		contain.setLayout(null);
		id = new JLabel("ID号");
		name = new JLabel("姓名");
		birthday = new JLabel("生日");
		institute = new JLabel("学院");
		major = new JLabel("专业");
		idt = new JTextField();
		namet = new JTextField();
		birthdayt = new JTextField();
		institutet = new JTextField();
		majort = new JTextField();
		submit = new JButton("提交");
		chooice = new Choice();
		chooice.addItem("student");
		chooice.addItem("teacher");
		chooice.addItem("administrator");
		group = new CheckboxGroup();
		check1 = new Checkbox("男", group, true);
		check2 = new Checkbox("女", group, false);
		id.setBounds(42, 10, 75, 35);
		idt.setBounds(80, 10, 150, 35);
		name.setBounds(42, 55, 75, 35);
		namet.setBounds(80, 55, 150, 35);
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
}