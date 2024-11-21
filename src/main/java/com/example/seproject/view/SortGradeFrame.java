package com.example.seproject.view;

import com.example.seproject.controller.GradeSort;
import com.example.seproject.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
@Component
@Lazy
public class SortGradeFrame extends JFrame implements ActionListener{
	
	JPanel contain;
	JLabel id, pass, good, excellent;
	JTextField idt, passt, goodt, excellentt;
	
	JButton submit;

	@Autowired
	private GradeSort gradeSort;
	
	int[] result = null;

	public void init() {
		setTitle("输入课程号和成绩标准");
		setSize(300, 300);
		setLocation(600, 400);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		id = new JLabel("课程号");
		idt = new JTextField();

		pass = new JLabel("及格");
		passt = new JTextField();
		good = new JLabel("良好");
		goodt = new JTextField();
		excellent = new JLabel("优秀");
		excellentt = new JTextField();

		submit = new JButton("提交");
		id.setBounds(38, 50, 75, 35);
		idt.setBounds(80, 50, 150, 35);

		pass.setBounds(38, 90, 75, 35);
		passt.setBounds(80, 90, 150, 35);
		good.setBounds(38, 130, 75, 35);
		goodt.setBounds(80, 130, 150, 35);
		excellent.setBounds(38, 170, 75, 35);
		excellentt.setBounds(80, 170, 150, 35);

		submit.setBounds(102, 210, 70, 30);
		contain.add(id);
		contain.add(idt);

		contain.add(pass);
		contain.add(passt);
		contain.add(good);
		contain.add(goodt);
		contain.add(excellent);
		contain.add(excellentt);

		contain.add(submit);
		submit.addActionListener(this);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		idt.setText("");
		passt.setText("");
		goodt.setText("");
		excellentt.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (((JButton) e.getSource()).getText() == "提交") { // 确保是提交按钮触发
			String id = idt.getText();
			String pass = passt.getText();
			String good = goodt.getText();
			String excellent = excellentt.getText();

			// 使用 isEmpty 方法检查输入框是否为空
			if (id.isEmpty() || pass.isEmpty() || good.isEmpty() || excellent.isEmpty()) {
				JOptionPane.showMessageDialog(null, "信息不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				try {
					float passValue = Float.parseFloat(pass);
					float goodValue = Float.parseFloat(good);
					float excellentValue = Float.parseFloat(excellent);

					if (gradeSort.isValidate(passValue, goodValue, excellentValue) == 1) {
						JOptionPane.showMessageDialog(null, "输入的成绩标准不合法！", "提示", JOptionPane.INFORMATION_MESSAGE);
					} else {
						if (gradeSort.hasCourse(idt.getText()) == 0) {
							JOptionPane.showMessageDialog(null, "此课程不存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
						} else {
							this.result = gradeSort.sortGrade(idt.getText(), passValue, goodValue, excellentValue);
							showResult();
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "成绩标准必须是数字！", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	
	
	void showResult(){
		
		JFrame fm = new JFrame("成绩统计结果");
		fm.setSize(300, 340);
		JPanel contain = new JPanel();
		fm.setLocation(600, 400);
		contain.setLayout(null);
		
		JLabel fail = new JLabel("不及格");
		JLabel pass = new JLabel("及格");
		JLabel good = new JLabel("良好");
		JLabel excellent = new JLabel("优秀");
		
		JTextField failt = new JTextField();
		JTextField passt = new JTextField();
		JTextField goodt = new JTextField();
		JTextField excellentt = new JTextField();
		
		fail.setBounds(38, 90, 75, 35);
		failt.setBounds(80, 90, 150, 35);
		pass.setBounds(38, 130, 75, 35);
		passt.setBounds(80, 130, 150, 35);
		good.setBounds(38, 170, 75, 35);
		goodt.setBounds(80, 170, 150, 35);
		excellent.setBounds(38, 210, 75, 35);
		excellentt.setBounds(80, 210, 150, 35);
		
		contain.add(fail);
		contain.add(failt);
		contain.add(pass);
		contain.add(passt);
		contain.add(good);
		contain.add(goodt);
		contain.add(excellent);
		contain.add(excellentt);
		fm.add(contain);
		
		failt.setText(Integer.toString(this.result[0])+"人");
		passt.setText(Integer.toString(this.result[1])+"人");
		goodt.setText(Integer.toString(this.result[2])+"人");
		excellentt.setText(Integer.toString(this.result[3])+"人");
		
		
		fm.setVisible(true);
		
	}

}
