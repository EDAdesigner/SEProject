package com.example.seproject.view;

import com.example.seproject.controller.CheckInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

@Component
@Lazy
public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField idTextField;
	private JPasswordField passwdTextField;
	private JLabel idLabel, passwdLabel;
	private Choice chooice;
	private JButton logon,forget;
	private JPanel contain;
	private int count = 0;

	@Autowired
	private CheckInfo checkInfo;

	@Autowired
	private StudentsPanel studentsPanel;

	@Autowired
	private TeachersPanel teachersPanel;

	@Autowired
	private AdministratorPanel administratorPanel;

//	@Autowired
//	private AddUser addUser;

	@Autowired
	private CodeForgetPanel codeForgetPanel;
	@PostConstruct
	public void init() {

//		addUser.addUserToDatabase("administrator","001","管理员","男","000","未知","未知");
//		由于密码加密原因，若想在其他电脑上运行程序，将上面两行注释掉的代码取消注释，然后以账号001，密码123456，administrator身份登录，之后进行相关操作即可。

		setTitle("账号登陆");
		setLocation(300, 200);
		setSize(300, 340);
		contain = new JPanel();
		contain.setLayout(null);
		idLabel = new JLabel("ID号");
		passwdLabel = new JLabel("密码");
		idTextField = new JTextField();
		passwdTextField = new JPasswordField();
		logon = new JButton("登陆");
		forget = new JButton("忘记密码？");
		chooice = new Choice();
		chooice.addItem("student");
		chooice.addItem("teacher");
		chooice.addItem("administrator");
		idLabel.setBounds(42, 45, 75, 35);
		idTextField.setBounds(80, 45, 150, 35);
		passwdLabel.setBounds(40, 100, 75, 35);
		passwdTextField.setBounds(80, 100, 150, 35);
		chooice.setBounds(80, 160, 150, 35);
		logon.setBounds(102, 220, 70, 30);
		forget.setBounds(87, 250, 100, 30);
		contain.add(idLabel);
		contain.add(idTextField);
		contain.add(passwdLabel);
		contain.add(passwdTextField);
		contain.add(chooice);
		contain.add(logon);
		contain.add(forget);
		logon.addActionListener(this);
		forget.addActionListener(this);
		add(contain);
		setVisible(true);



		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logon) {
			String ch = (String) chooice.getSelectedItem();
			if (ch.equals("student")) {
				if (checkInfo.isMember("student", idTextField.getText(), new String(passwdTextField.getPassword())) == 1) {
					setVisible(false);
					studentsPanel.init(idTextField.getText());
				} else {
					handleLoginFailure();
				}
			} else if (ch.equals("teacher")) {
				if (checkInfo.isMember("teacher", idTextField.getText(), new String(passwdTextField.getPassword())) == 1) {
					setVisible(false);
					teachersPanel.init(idTextField.getText());
				} else {
					handleLoginFailure();
				}
			} else if (ch.equals("administrator")) {
				if (checkInfo.isMember("administrator", idTextField.getText(), new String(passwdTextField.getPassword())) == 1) {
					setVisible(false);
					administratorPanel.init(idTextField.getText());
				} else {
					handleLoginFailure();
				}
			}
		}else if(e.getSource() == forget){
			setVisible(false);
			codeForgetPanel.init();
		}
	}

	private void handleLoginFailure() {
		count += 1;
		if (count <= 5) {
			JOptionPane.showMessageDialog(null, "无此用户，或者密码输入错误！", "错误", JOptionPane.INFORMATION_MESSAGE);
		}
		if (count > 5) {
			JOptionPane.showMessageDialog(null, "错误次数超过5次！", "错误", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			setVisible(false);
			System.exit(0);
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