package com.lotte.client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lotte.client.ClientThread;
import com.lotte.client.WaitingRoom;



public class ClientMain extends JFrame implements ActionListener{
	
	Socket socket = null;
	String host = "10.131.152.120";
	int port = 5555;
	
	JPanel p_id, p_pwd, p_bt, p_logo;
	JLabel l_id, l_pwd, l_logo;
	JTextField txt_id;
	JPasswordField txt_pwd;
	JButton bt_login, bt_cancel, bt_regist;
	
	public ClientThread clientThread;
	
	WaitingRoom waitingRoom;
	
	public ClientMain() {
		try {
			socket = new Socket(host, port);
			clientThread=new ClientThread(this);
			clientThread.start();
		} catch (IOException e) {
			System.out.println("[ Debug ] : Socket Connection Error.");
		}
		setupUI();
	}
	
	public void setupUI(){
		p_logo = new JPanel();
		l_logo = new JLabel("ForLotte");
		l_logo.setFont(new Font("돋움", Font.BOLD, 30));
		p_logo.add(l_logo);
		
		p_id = new JPanel();
		l_id = new JLabel("ID : ");
		txt_id = new JTextField(20);
		p_id.add(l_id);
		p_id.add(txt_id);
		
		p_pwd = new JPanel();
		l_pwd = new JLabel("PW : ");
		txt_pwd = new JPasswordField(20);
		p_pwd.add(l_pwd);
		p_pwd.add(txt_pwd);
		
		p_bt = new JPanel();
		bt_login = new JButton("로그인");
		bt_login.addActionListener(this);
		bt_regist = new JButton("회원가입");
		bt_cancel = new JButton("종료");
		bt_cancel.addActionListener(this);
		p_bt.add(bt_regist);
		p_bt.add(bt_login);
		p_bt.add(bt_cancel);
		
		add(p_logo);
		add(p_id);
		add(p_pwd);
		add(p_bt);
		setLayout(new FlowLayout());
		setSize(new Dimension(400, 250));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void exitWindow(){
		if( socket != null){
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("[ Debug ] : Socket is Closed"); 
			}
		}
		System.exit(0);
}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt_cancel){
			exitWindow();
		}else if(e.getSource() == bt_login){
			waitingRoom = new WaitingRoom(this);
			this.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new ClientMain();
	}
}














