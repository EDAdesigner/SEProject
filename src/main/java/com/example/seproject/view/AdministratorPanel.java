package com.example.seproject.view;

import com.example.seproject.controller.AddUser;
import com.example.seproject.controller.ApplicationContextProvider;
import com.example.seproject.controller.DeleteUser;
import com.example.seproject.controller.EditInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

@Component
public class AdministratorPanel extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton deleteUser, addUser, selfInfo, logout;
	private JPanel contain;
	private String id;


	@Autowired
	private ApplicationContext applicationContext;

	public AdministratorPanel() {
		// Default constructor for Spring
	}
	public void init(String id) {
		this.id = id;
		setTitle("系统管理员");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addUser) {
			AddUser addUser = applicationContext.getBean(AddUser.class);
			addUser.init();
		} else if (e.getSource() == deleteUser) {
			DeleteUser deleteUser = applicationContext.getBean(DeleteUser.class);
			deleteUser.init();
		} else if (e.getSource() == selfInfo) {
			EditInfo editInfo = applicationContext.getBean(EditInfo.class);
			editInfo.init(id, 3);
		} else if (e.getSource() == logout) {
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