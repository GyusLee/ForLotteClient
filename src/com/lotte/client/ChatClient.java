package com.lotte.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ChatClient extends JFrame {
	JPanel p_center;
	JPanel p_south;
	JTextArea area;
	JScrollPane scroll;
	JTextField txt;
	JButton bt;
	
	StringBuffer sb;
	ClientMain clientMain;
	public int room_num;
	Socket client;
	String user_id;
	String user_Name;
	public ChatClient(ClientMain clientMain,int room_num) {
		this.clientMain=clientMain;
		this.room_num=room_num;
		this.client=clientMain.socket;
		this.user_id=clientMain.user_id;
		this.user_Name=clientMain.user_name;

		sb=new StringBuffer();
		p_center=new JPanel();
		area=new JTextArea();
		area.setPreferredSize(new Dimension(160, 470));
		area.setEditable(false);
		scroll=new JScrollPane(area);
		p_south=new JPanel();
		p_south.setPreferredSize(new Dimension(160, 100));
		txt=new JTextField(15);
		//setTitle(Integer.toString(room_num));
		txt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==KeyEvent.VK_ENTER){
					
					sb.delete(0, sb.length());
					sb.append("{\"request\" : \"chatting\",");
					sb.append("\"data\" : [{");
					sb.append("\"msg\" : \""+user_Name+"("+user_id+")"+":"+txt.getText()+"\",");
					sb.append("\"room_num\" : \""+room_num+"\"");
					sb.append("}]}");
					clientMain.clientThread.sendMsg(sb);
					txt.setText("");
								
				}
			}
		});
		bt=new JButton();

		bt.setPreferredSize(new Dimension(75, 35));
		bt.setBorderPainted(false);
		bt.setBackground(new Color(0, 0, 0, 0));
		bt.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				bt.updateUI();
				p_south.updateUI();
				p_south.repaint();
			}

			public void mouseExited(MouseEvent e) {
				bt.updateUI();
				p_south.updateUI();
				p_south.repaint();
			}
		});

		bt.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				bt.updateUI();
				p_south.updateUI();
				p_south.repaint();
			}

			public void mouseExited(MouseEvent e) {
				bt.updateUI();
				p_south.updateUI();
				p_south.repaint();
			}
		});
		bt.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				bt.updateUI();
				p_south.updateUI();
				p_south.repaint();
			}
		});
		bt.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				bt.updateUI();
				p_south.updateUI();
				p_south.repaint();

			}

			public void focusGained(FocusEvent e) {
				bt.updateUI();
				p_south.updateUI();
				p_south.repaint();

			}
		});
		
		
		p_center.add(scroll);
		//p_center.setPreferredSize(new Dimension(500, 500));
		add(p_center);
		
		p_south.add(txt);
		p_south.add(bt);
		add(p_south,BorderLayout.SOUTH);

		setBounds(900, 100, 200, 500);
		setResizable(false);
		setVisible(true);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				clientMain.clientThread.flag=false;
				disConnection();
				if(client!=null){
					try {
						client.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

		});
	}
	
	public ImageIcon createImage(String path){
		return new ImageIcon(this.getClass().getClassLoader().getResource(path));
	}
	
	public void disConnection(){
		sb.delete(0, sb.length());
		sb.append("{\"request\" : \"disconnectchat\",");
		sb.append("\"data\" : {");
		sb.append("\"room_num\" : \""+room_num+"\"");
		sb.append("}}");
		clientMain.clientThread.sendMsg(sb);
	}
}
