package com.example.seproject.controller;

import com.example.seproject.Service.UserService;
import com.example.seproject.view.AdministratorPanel;
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
public class EditInfo extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField instt, namet, birtht, majort;
	private JPasswordField pass1t, pass2t;
	private JLabel name, birth, inst, major, pass1, pass2;
	private JButton submit;
	private JPanel contain;
	private CheckboxGroup group;
	private Checkbox check1, check2;
	private int flag;
	private String id;

	@Autowired
	private UserService userService;
	@Autowired
	private ApplicationContext applicationContext;

	public void init(String id, int flag) {
		this.id = id;
		this.flag = flag;
		if(flag == 1){
			instt.setText(userService.findUserByIdAndType(id,"teacher").getInstitute());
			namet.setText(userService.findUserByIdAndType(id,"teacher").getName());
			birtht.setText(userService.findUserByIdAndType(id,"teacher").getBirthday());
			majort.setText(userService.findUserByIdAndType(id,"teacher").getMajor());
			String check = userService.findUserByIdAndType(id,"teacher").getGender();
			if(check.equals("男")){
				check1.setState(true);
				check2.setState(false);
			}else {
				check1.setState(false);
				check2.setState(true);
			}
		}else if(flag == 0){
			instt.setText(userService.findUserByIdAndType(id,"student").getInstitute());
			namet.setText(userService.findUserByIdAndType(id,"student").getName());
			birtht.setText(userService.findUserByIdAndType(id,"student").getBirthday());
			majort.setText(userService.findUserByIdAndType(id,"student").getMajor());
			String check = userService.findUserByIdAndType(id,"student").getGender();
			if(check.equals("男")){
				check1.setState(true);
				check2.setState(false);
			}else {
				check1.setState(false);
				check2.setState(true);
			}
		}else if(flag == 3){
			instt.setText(userService.findUserByIdAndType(id,"administrator").getInstitute());
			namet.setText(userService.findUserByIdAndType(id,"administrator").getName());
			birtht.setText(userService.findUserByIdAndType(id,"administrator").getBirthday());
			majort.setText(userService.findUserByIdAndType(id,"administrator").getMajor());
			String check = userService.findUserByIdAndType(id,"administrator").getGender();
			if(check.equals("男")){
				check1.setState(true);
				check2.setState(false);
			}else {
				check1.setState(false);
				check2.setState(true);
			}
		}
		setVisible(true);
	}

	public EditInfo() {
		setTitle("编辑信息");
		setLocation(300, 200);
		setSize(300, 400);
		contain = new JPanel();
		contain.setLayout(null);
		name = new JLabel("姓名");
		birth = new JLabel("生日");
		inst = new JLabel("学院");
		major = new JLabel("专业");
		pass1 = new JLabel("新密码");
		pass2 = new JLabel("确认密码");
		submit = new JButton("提交");
		group = new CheckboxGroup();
		check1 = new Checkbox("男", group, true);
		check2 = new Checkbox("女", group, false);
		instt = new JTextField();
		namet = new JTextField();
		birtht = new JTextField();
		majort = new JTextField();
		pass1t = new JPasswordField();
		pass2t = new JPasswordField();
		name.setBounds(42, 20, 75, 35);
		namet.setBounds(80, 20, 150, 35);
		check1.setBounds(80, 60, 80, 40);
		check2.setBounds(160, 60, 80, 40);
		birth.setBounds(42, 100, 75, 35);
		birtht.setBounds(80, 100, 150, 35);
		inst.setBounds(40, 145, 75, 35);
		instt.setBounds(80, 145, 150, 35);
		major.setBounds(40, 190, 75, 35);
		majort.setBounds(80, 190, 150, 35);
		pass1.setBounds(36, 235, 75, 35);
		pass1t.setBounds(80, 235, 150, 35);
		pass2.setBounds(28, 280, 75, 35);
		pass2t.setBounds(80, 280, 150, 35);
		submit.setBounds(102, 325, 70, 30);
		contain.add(name);
		contain.add(namet);
		contain.add(check1);
		contain.add(check2);
		contain.add(birth);
		contain.add(birtht);
		contain.add(inst);
		contain.add(instt);
		contain.add(major);
		contain.add(majort);
		contain.add(pass1);
		contain.add(pass1t);
		contain.add(pass2);
		contain.add(pass2t);
		contain.add(submit);
		submit.addActionListener(this);
		add(contain);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public EditInfo(String idd, int flag) {
		this();
		this.id = idd;
		this.flag = flag;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			if ((instt.getText().equals("")) || (birtht.getText().equals(""))
					|| (namet.getText().equals(""))
					|| (pass1t.getText().equals(""))
					|| (pass2t.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "信息不能为空！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (!(pass1t.getText().equals(pass2t.getText()))) {
					JOptionPane.showMessageDialog(null, "新密码与确认密码不同！", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (pass1t.getText().length() < 6) {
					JOptionPane.showMessageDialog(null, "密码长度至少为6位！", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String gender = check1.getState() ? "男" : "女";
					try {
						boolean success = false;
						if (flag == 0) { // 学生修改信息
							success = userService.updateStudentInfo(id, pass2t.getText(), namet.getText(), gender, birtht.getText(), instt.getText(), majort.getText());
						} else if (flag == 1) { // 教师修改信息
							success = userService.updateTeacherInfo(id, pass2t.getText(), namet.getText(), gender, birtht.getText(), instt.getText(), majort.getText());
						} else if (flag == 3) { // 教务员修改信息
							success = userService.updateAdministratorInfo(id, pass2t.getText(), namet.getText(), gender, birtht.getText(), instt.getText(), majort.getText());
						}
						if (success) {
							JOptionPane.showMessageDialog(null, "修改成功！", "提示",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "修改失败！", "提示",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "修改过程中发生错误！", "提示",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
			AdministratorPanel administratorPanel = applicationContext.getBean(AdministratorPanel.class);
			administratorPanel.init(id);
		}
	}
}