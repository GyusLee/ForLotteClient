package com.lotte.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WaitingRoom extends JFrame implements ActionListener /*Runnable*/{

	ClientMain clientMain;
	JPanel p_north, p_list, p_center;
	JScrollPane scroll;
	JButton mem_list;
	JButton refresh;
	JButton make_room;
	JButton close_window;
	JButton idea_board;
	/*RoomInfo[] roomInfos = new RoomInfo[5];
	
	
	*/
	/*RoomInfo roomInfo;*/
	MakeRoom makeRoom;
	MemberList memberList;
	Thread th;
	int cnt=0;
	StringBuffer sb = new StringBuffer();
	public boolean flag=true;
	
	public WaitingRoom(ClientMain clientMain) {
		this.clientMain=clientMain;
		
		p_north =  new JPanel();
		p_north.setPreferredSize(new Dimension(600, 40));
		p_north.setLayout(new FlowLayout());
		
		mem_list = new JButton("접속자 목록");
		mem_list.setPreferredSize(new Dimension(110, 24));
		mem_list.addActionListener(this);
		
		refresh = new JButton("새로고침");
		refresh.setPreferredSize(new Dimension(100, 24));
		refresh.addActionListener(this);
		
		make_room = new JButton("방만들기");
		make_room.setPreferredSize(new Dimension(100, 24));
		make_room.addActionListener(this);
		
		close_window = new JButton("종료");
		close_window.setPreferredSize(new Dimension(100, 24));
		close_window.addActionListener(this);
		
		idea_board = new JButton("아이디어 게시판");
		close_window.setPreferredSize(new Dimension(100, 24));
		
		p_center = new JPanel();
		p_list = new JPanel();
		p_list.setBackground(Color.white);
		
		p_north.add(make_room);	
		p_north.add(mem_list);
		p_north.add(refresh);
		p_north.add(idea_board);
		p_north.add(close_window);
		
		
		//getRoomList();
		
		p_center.add(p_list);
		scroll = new JScrollPane(p_center,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBackground(Color.white);
		p_center.setBackground(Color.white);
		setLayout(new BorderLayout());
		
		add(p_north, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
		
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation( ((dim.width/2)-(getWidth()/2)-400), ((dim.height/2)-(getHeight()/2)-350));
		setSize(600,600);
		setTitle("대기실");
		setVisible(true);
		/*th=new Thread(this);
		th.start();*/
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disConnection();
			}

		});

	}
	
	
	public void disConnection(){
		sb.delete(0, sb.length());
		sb.append("{\"request\" : \"disconnect\",");
		sb.append("\"data\" : {");
		sb.append("\"id\" : \""+clientMain.user_id+"\"");
		sb.append("}}");
		clientMain.clientThread.sendMsg(sb);
	}
	
	/*
	public void getRoomList(){
		//��񿡼� �� ��� ��������
		sb.delete(0, sb.length());
		sb.append("{\"request\" : \"listrooms\",");
		sb.append("\"data\" : {");
		sb.append("\"id\" : \""+clientMain.user_id+"\"");
		sb.append("}}");
		clientMain.clientThread.sendMsg(sb);;
		p_list.setPreferredSize(new Dimension(550, 400+130*clientMain.memberList.size()));
		for (int i=0; i<clientMain.roomList.size(); i++){
			roomInfo = new RoomInfo(this,i);
			p_list.add(roomInfo);
		}
		p_list.updateUI();
		
	}	

	public void refresh(){
		sb.delete(0, sb.length());
		sb.append("{\"request\" : \"refresh\",");
		sb.append("\"data\" : {");
		sb.append("\"id\" : \""+clientMain.user_id+"\"");
		sb.append("}}");
		clientMain.clientThread.sendMsg(sb);
		p_list.removeAll();
		p_list.setPreferredSize(new Dimension(550, clientMain.roomList.size()*160));
		for (int i=0; i<clientMain.roomList.size(); i++){
			roomInfo = new RoomInfo(this, i);
			p_list.add(roomInfo);
		}
		p_list.updateUI();
	}
	
	*/
	public void showConnector(){
		sb.delete(0, sb.length());
		sb.append("{\"request\" : \"showconnector\",");
		sb.append("\"data\" : {");
		sb.append("\"id\" : \""+clientMain.user_id+"\"");
		sb.append("}}");
		clientMain.clientThread.sendMsg(sb);
		memberList.table.updateUI();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		p_north.updateUI();
		p_north.repaint();
		
		if(e.getSource() == mem_list){
			if(memberList == null)
				memberList = new MemberList(this.clientMain, this);
			if(memberList.openflag){
				showConnector();
				memberList.setVisible(memberList.openflag);
				memberList.openflag = false;
			}else if(!memberList.openflag){
					memberList.setVisible(memberList.openflag);
					memberList.openflag = true;
			}
		}
		
		else if(e.getSource() == make_room){
			if(makeRoom==null)
				makeRoom = new MakeRoom(clientMain, this);
			if( makeRoom.openflag ){
				makeRoom.setVisible(true);
				makeRoom.openflag = false;
			}else if ( makeRoom.openflag == false){
				makeRoom.setVisible(false);
				makeRoom.openflag = true;
			}
		}
		/*else if(e.getSource()==refresh){
			refresh();
		}*/
		else if (e.getSource() == close_window){
			int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "프로그램 종료", JOptionPane.YES_NO_OPTION);
			if(result == 0){
				disConnection();
			}
		}
	}
	
	
		//JOptionPane.showMessageDialog(this, i);

	
	/*
	@Override
	public void run() {
		while(true)
		{
			try {
				Thread.sleep(5000);
				cnt++;
				if(cnt==3)
					cnt=0;
				p_north.repaint();
				p_north.updateUI();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	*/

}
