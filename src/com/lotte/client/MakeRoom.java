package com.lotte.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.lotte.share.Server;


public class MakeRoom extends JFrame implements ActionListener, ItemListener{

	JLabel l_title, l_ispw, l_pw, l_max;
	JTextField title;
	JPasswordField pw;
	JCheckBox ispw, nopw;
	JCheckBox two, three, four;
	JButton bt_create, bt_cancel;
	JPanel p_title, p_ispw, p_pw, p_max, p_bt;
	boolean openflag = true;
	JPanel center;
	ClientMain clientMain;
	StringBuffer sb = new StringBuffer();

	WaitingRoom waitingRoom;
	Toolkit toolkit;
	
	public MakeRoom(ClientMain clientMain,WaitingRoom waitingRoom) {
		this.clientMain = clientMain;
		this.waitingRoom = waitingRoom;
		
		p_title=new JPanel();
		p_title.setPreferredSize(new Dimension(400, 30));
		
		l_title = new JLabel("방 제목");
		l_title.setPreferredSize(new Dimension(80, 25));
		
		title = new JTextField(20);
		title.setPreferredSize(new Dimension(280, 25));
		
		p_title.add(l_title);
		p_title.add(title);
		
		p_pw = new JPanel();
		p_pw.setPreferredSize(new Dimension(400, 30));
		l_pw = new JLabel("비밀번호");
		l_pw.setPreferredSize(new Dimension(100, 25));
		
		pw = new JPasswordField(15);
		pw.setPreferredSize(new Dimension(280, 25));
		p_pw.add(l_pw);
		p_pw.add(pw);
		
		p_ispw = new JPanel();
		p_ispw.setPreferredSize(new Dimension(400, 30));
		l_ispw = new JLabel("비번 여부");
		l_ispw.setPreferredSize(new Dimension(120, 25));
		
		ispw = new JCheckBox("비번방");
		ispw.setSelected(true);
		ispw.setPreferredSize(new Dimension(100, 30));
		
		nopw = new JCheckBox("일반방");
		nopw.setPreferredSize(new Dimension(100,30));
		p_ispw.add(l_ispw);
		p_ispw.add(ispw);
		p_ispw.add(nopw);
		
		p_max = new JPanel();
		p_max.setPreferredSize(new Dimension(400, 30));
		l_max = new JLabel("최대인원");
		l_max.setPreferredSize(new Dimension(80, 25));
		
		two = new JCheckBox("2");
		two.setPreferredSize(new Dimension(50,30));
		two.setSelected(true);
		
		three = new JCheckBox("3");
		three.setPreferredSize(new Dimension(50,30));
		
		four = new JCheckBox("4");
		four.setPreferredSize(new Dimension(50,30));
		
		two.addItemListener(this);
		three.addItemListener(this);
		four.addItemListener(this);
		
		p_max.add(l_max);
		p_max.add(two);
		p_max.add(three);
		p_max.add(four);
		
		p_bt = new JPanel();
		p_bt.setPreferredSize(new Dimension(400, 40));
		bt_create = new JButton("만들기");
		bt_create.setPreferredSize(new Dimension(150, 30));
		bt_create.addActionListener(this);
		
		bt_cancel = new JButton("취소");
		bt_cancel.addActionListener(this);
		bt_cancel.setPreferredSize(new Dimension(150, 30));
		
		p_bt.add(bt_create);
		p_bt.add(bt_cancel);
		
		setLayout(new FlowLayout());
		add(p_title);
		add(p_ispw);
		add(p_pw);
		add(p_max);
		add(p_bt);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				openflag = false;
				setVisible(false);
			}
		});
		setTitle("방만들기");
		setSize(400, 250);
		setLocationRelativeTo(null);
		setVisible(openflag);
	}

	public void checkCreateRoom() {
		String isPw = null;
		String maxPeople = null;
		String pwd = new String(pw.getPassword());

		if (ispw.isSelected()) {
			isPw = "true";
		} else if (nopw.isSelected()) {
			isPw = "false";
		} else {
			JOptionPane.showMessageDialog(this, "");
			title.setText("");
			pw.setText("");
			openflag = false;
//			waitingRoom.checkFlag();
			return;
		}

		if (checkPassword(pwd, isPw) == false) {
			title.setText("");
			pw.setText("");
			openflag = false;
	//		waitingRoom.checkFlag();
			return;
		}

		if (two.isSelected()) {
			maxPeople = "2";
		} else if (three.isSelected()) {
			maxPeople = "3";
		} else if (four.isSelected()) {
			maxPeople = "4";
		}

		if (maxPeople == "2" || maxPeople == "3" || maxPeople == "4") {
			createRoom(isPw, pwd, maxPeople);
		} else {
			JOptionPane.showMessageDialog(this, "", "", JOptionPane.CANCEL_OPTION);
			title.setText("");
			pw.setText("");
			openflag = false;
	//		waitingRoom.checkFlag();
			return;
		}
	}

	public void createRoom(String isPw, String pwd, String maxPeople) {
		if( pwd.equals("Password")){
			pwd = null;
		}
		sb.delete(0, sb.length());
		sb.append("{\"request\" : \"create_room\",");
		sb.append("\"data\" : {");
		sb.append("\"title\" : \"" + title.getText() + "\",");
		sb.append("\"ispw\" : \"" + isPw + "\",");
		sb.append("\"password\" : \"" + pwd + "\",");
		sb.append("\"max\" : \"" + maxPeople + "\",");
		sb.append("\"host\" : \"" + clientMain.user_id + "\"");
		sb.append("}}");

		clientMain.clientThread.sendMsg(sb);
	}

	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == ispw) {
			nopw.setSelected(false);
		} else if (e.getSource() == nopw) {
			ispw.setSelected(false);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_cancel) {
			openflag = false;
		//	waitingRoom.checkFlag();
			setVisible(openflag);
		} else if (e.getSource() == bt_create) {
			checkCreateRoom();
			setVisible(openflag);
		}

	}

	public boolean checkPassword(String pw, String ispw) {
		if (ispw.equals("true")) {
			int result = pw.length();
			if (result < 4) {
				JOptionPane.showMessageDialog(this, "비밀번호는 4자리 이상 입력하세요.");
				return false;
			}
		}
		return true;
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (e.getSource() == ispw) {
				nopw.setSelected(false);
				pw.setEnabled(true);
			} else if (e.getSource() == nopw) {
				ispw.setSelected(false);
				pw.setEnabled(false);
			}

			if (e.getSource() == two) {
				three.setSelected(false);
				four.setSelected(false);
			} else if (e.getSource() == three) {
				two.setSelected(false);
				four.setSelected(false);
			} else if (e.getSource() == four) {
				three.setSelected(false);
				two.setSelected(false);
			}
		}
	}
}
