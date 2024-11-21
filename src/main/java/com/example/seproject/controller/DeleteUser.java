package com.example.seproject.controller;


import com.example.seproject.Service.UserService;
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
public class DeleteUser extends JFrame implements ActionListener {
	/**
	 * 管理员删除用户
	 */
	JPanel contain;
	JLabel id;
	JTextField idt;
	Choice chooice;
	JButton submit;

	@Autowired
	private UserService userService;

	public DeleteUser() {
		super("删除用户");
		setSize(300, 340);
		setLocation(600, 400);
		contain = new JPanel();
		contain.setLayout(null);
		chooice = new Choice();
		chooice.addItem("student");
		chooice.addItem("teacher");
		chooice.addItem("administrator");
		id = new JLabel("帐号");
		submit = new JButton("提交");
		idt = new JTextField();
		id.setBounds(42, 45, 75, 35);
		idt.setBounds(80, 45, 150, 35);
		chooice.setBounds(80, 100, 150, 35);
		submit.setBounds(102, 150, 70, 30);
		contain.add(id);
		contain.add(idt);
		contain.add(chooice);
		contain.add(submit);
		submit.addActionListener(this);
		add(contain);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			String userType = (String) chooice.getSelectedItem();
			String userId = idt.getText();

			if (userService.checkIfUserExists(userType, userId)) {
				userService.deleteUser(userType, userId);
				JOptionPane.showMessageDialog(null, "删除" + userType + "成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "此" + userType + "不存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
		}
	}

	public void init() {
	}
}
