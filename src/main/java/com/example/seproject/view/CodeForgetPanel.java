package com.example.seproject.view;

import com.example.seproject.controller.ApplicationContextProvider;
import com.example.seproject.controller.CheckInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

@Component
@Lazy
public class CodeForgetPanel extends JFrame implements ActionListener {
    private JPanel contain;
    private JButton confirm,back;
    @Autowired
    private CheckInfo checkInfo;
    private JLabel idLabel,nameLabel;
    private JTextField idField,nameField;
    public void init(){
        setTitle("忘记密码");
        setLocation(300, 200);
        setSize(300, 340);
        contain = new JPanel();
        contain.setLayout(null);
        confirm = new JButton("确认");
        back = new JButton("返回");
        idLabel = new JLabel("ID号");
        nameLabel = new JLabel("姓名");
        idField = new JTextField();
        nameField = new JTextField();
        idLabel.setBounds(42, 45, 75, 35);
        idField.setBounds(80, 45, 150, 35);
        nameLabel.setBounds(40, 100, 75, 35);
        nameField.setBounds(80, 100, 150, 35);
        confirm.setBounds(102, 220, 70, 30);
        back.setBounds(102,250,70,30);
        contain.add(idLabel);
        contain.add(idField);
        contain.add(nameLabel);
        contain.add(nameField);
        contain.add(confirm);
        contain.add(back);
        confirm.addActionListener(this);
        back.addActionListener(this);
        add(contain);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == confirm){
            if(checkInfo.checkUserIdAndName(idField.getText(), nameField.getText()) == 0){
                JOptionPane.showMessageDialog(null,"无此用户","错误", JOptionPane.INFORMATION_MESSAGE);
            }else
                JOptionPane.showMessageDialog(null,"重置密码已发送至校园邮箱","完成",JOptionPane.INFORMATION_MESSAGE);
        }else if(e.getSource() == back){
            setVisible(false);
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
