package com.example.seproject.view;

import com.example.seproject.controller.AddUser;
import com.example.seproject.controller.DeleteUser;
import com.example.seproject.controller.EditInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class AdministratorPanel extends JFrame implements ActionListener {
	JButton deleteUser, addUser, selfInfo, logout;
	JPanel contain;
	String idd;

	public AdministratorPanel(String idd) {
		super("系统管理员");
		this.idd = idd;
		setLocation(300, 200);
		setSize(300, 340);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		selfInfo = new JButton("修改信息");
		addUser = new JButton("增加用户");
		deleteUser = new JButton("删除用户");
		logout = new JButton("退出登录");
		selfInfo.setBounds(70, 45, 140, 30);
		addUser.setBounds(70, 100, 140, 30);
		deleteUser.setBounds(70, 155, 140, 30);
		logout.setBounds(70, 210, 140, 30);
		contain.add(selfInfo);
		contain.add(addUser);
		contain.add(deleteUser);
		contain.add(logout);
		selfInfo.addActionListener(this);
		addUser.addActionListener(this);
		deleteUser.addActionListener(this);
		logout.addActionListener(this);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addUser) {
			new AddUser();
		} else if (e.getSource() == deleteUser) {
			new DeleteUser();
		} else if (e.getSource() == selfInfo) {
			new EditInfo(idd, 3);
		} else if (e.getSource() == logout) {
			this.dispose();
			new MainFrame();
		}
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
			System.exit(0);
		}
	}
}