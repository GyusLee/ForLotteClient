package com.lotte.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.lotte.share.AcceptScreen;
import com.lotte.share.Join;
import com.lotte.share.ScreenShotServer;

public class ControllUI extends JFrame implements ActionListener {

	public JPanel controller;
	public JButton btn_sreenServer;
	public JButton btn_requestServer;
	public JButton btn_joinServer;
	public JButton btn_waitingroom;

	public JButton btn_screenOn;
	public JButton btn_chatOn;
	JLabel design1, design2, design3;

	public ScreenUI screenUI;
	public String ip;
	public ScreenShotServer screenShot;
	public AcceptScreen acceptScreen;
	Join join;
	boolean screenFlag = true;
	boolean chatFlag = true;
	boolean first = false;
	Image img;
	Toolkit toolkit;
	StringBuffer sb;
	public ControllUI(String ip, boolean host, Join join) {
		this.ip = ip;
		this.join = join;
		sb=new StringBuffer();
		screenUI = new ScreenUI();
		design1 = new JLabel();
		design2 = new JLabel();
		design3 = new JLabel();
		design1.setPreferredSize(new Dimension(200, 20));
		design2.setPreferredSize(new Dimension(8, 35));
		design3.setPreferredSize(new Dimension(8, 35));
		toolkit = Toolkit.getDefaultToolkit();
		img = toolkit.createImage(this.getClass().getClassLoader().getResource("team.png"));
		controller = new JPanel();
		controller.setPreferredSize(new Dimension(200, 250));
		btn_sreenServer = new JButton();
		btn_sreenServer.setPreferredSize(new Dimension(75, 35));
		btn_sreenServer.setBorderPainted(false);
		btn_sreenServer.setBackground(new Color(0, 0, 0, 0));
		btn_sreenServer.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_sreenServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_sreenServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});

		btn_sreenServer.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_sreenServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_sreenServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_sreenServer.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				btn_sreenServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_sreenServer.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				btn_sreenServer.updateUI();
				controller.updateUI();
				controller.repaint();

			}

			public void focusGained(FocusEvent e) {
				btn_sreenServer.updateUI();
				controller.updateUI();
				controller.repaint();

			}
		});

		btn_joinServer = new JButton();
		btn_joinServer.setPreferredSize(new Dimension(75, 35));
		btn_joinServer.setBorderPainted(false);
		btn_joinServer.setBackground(new Color(0, 0, 0, 0));
		btn_joinServer.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_joinServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_joinServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});

		btn_joinServer.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_joinServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_joinServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_joinServer.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				btn_sreenServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_joinServer.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				btn_joinServer.updateUI();
				controller.updateUI();
				controller.repaint();

			}

			public void focusGained(FocusEvent e) {
				btn_joinServer.updateUI();
				controller.updateUI();
				controller.repaint();

			}
		});

		btn_requestServer = new JButton();
		btn_requestServer.setPreferredSize(new Dimension(87, 35));
		btn_requestServer.setBorderPainted(false);
		btn_requestServer.setBackground(new Color(0, 0, 0, 0));
		btn_requestServer.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_requestServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_requestServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});

		btn_requestServer.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_requestServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_requestServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_requestServer.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				btn_requestServer.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_requestServer.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				btn_requestServer.updateUI();
				controller.updateUI();
				controller.repaint();

			}

			public void focusGained(FocusEvent e) {
				btn_requestServer.updateUI();
				controller.updateUI();
				controller.repaint();

			}
		});

		btn_screenOn = new JButton();
		btn_screenOn.setPreferredSize(new Dimension(75, 35));
		btn_screenOn.setBorderPainted(false);
		btn_screenOn.setBackground(new Color(0, 0, 0, 0));
		btn_screenOn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_screenOn.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_screenOn.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});

		btn_screenOn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_screenOn.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_screenOn.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_screenOn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				btn_screenOn.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_screenOn.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				btn_screenOn.updateUI();
				controller.updateUI();
				controller.repaint();

			}

			public void focusGained(FocusEvent e) {
				btn_screenOn.updateUI();
				controller.updateUI();
				controller.repaint();

			}
		});

		btn_chatOn = new JButton();

		btn_chatOn.setPreferredSize(new Dimension(75, 35));
		btn_chatOn.setBorderPainted(false);
		btn_chatOn.setBackground(new Color(0, 0, 0, 0));
		btn_chatOn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_chatOn.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_chatOn.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});

		btn_chatOn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_chatOn.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_chatOn.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_chatOn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				btn_chatOn.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_chatOn.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				btn_chatOn.updateUI();
				controller.updateUI();
				controller.repaint();

			}

			public void focusGained(FocusEvent e) {
				btn_chatOn.updateUI();
				controller.updateUI();
				controller.repaint();

			}
		});

		btn_waitingroom = new JButton();

		btn_waitingroom.setPreferredSize(new Dimension(75, 35));
		btn_waitingroom.setBorderPainted(false);
		btn_waitingroom.setBackground(new Color(0, 0, 0, 0));
		btn_waitingroom.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_waitingroom.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_waitingroom.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});

		btn_waitingroom.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn_waitingroom.updateUI();
				controller.updateUI();
				controller.repaint();
			}

			public void mouseExited(MouseEvent e) {
				btn_waitingroom.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_waitingroom.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				btn_waitingroom.updateUI();
				controller.updateUI();
				controller.repaint();
			}
		});
		btn_waitingroom.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				btn_waitingroom.updateUI();
				controller.updateUI();
				controller.repaint();

			}

			public void focusGained(FocusEvent e) {
				btn_waitingroom.updateUI();
				controller.updateUI();
				controller.repaint();

			}
		});
		controller.add(design1);
		controller.add(btn_sreenServer);
		controller.add(design2);
		controller.add(btn_joinServer);

		controller.add(btn_screenOn);
		controller.add(design3);
		controller.add(btn_chatOn);

		controller.add(btn_requestServer);
		controller.add(btn_waitingroom);

		add(controller);
		if (host) {
			btn_sreenServer.setEnabled(true);
			btn_joinServer.setEnabled(false);
			btn_requestServer.setEnabled(false);
		} else {
			btn_sreenServer.setEnabled(false);
			btn_joinServer.setEnabled(false);
			btn_requestServer.setEnabled(true);
		}

		btn_sreenServer.addActionListener(this);
		btn_joinServer.addActionListener(this);
		btn_requestServer.addActionListener(this);
		btn_waitingroom.addActionListener(this);
		btn_chatOn.addActionListener(this);
		btn_screenOn.addActionListener(this);
		setBounds(900, 600, 200, 250);
		setTitle("Controller");
		setResizable(false);
		setVisible(true);

	}


	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_sreenServer)) {
			screenShot = new ScreenShotServer(join);
			acceptScreen = new AcceptScreen(screenUI, ip);
			join.joinCreateRoom();
			btn_sreenServer.setEnabled(false);
			screenUI.setVisible(false);

		} else if (obj.equals(btn_joinServer)) {
			acceptScreen = new AcceptScreen(screenUI, ip);
			screenUI.setVisible(true);
			btn_joinServer.setEnabled(false);
		} else if (obj.equals(btn_requestServer)) {
			join.joinRequest();

		} else if (obj.equals(btn_waitingroom)) {
			if (screenShot != null) {
				join.gt.toWaitingRoom();
				join.clientMain.waitingRoom.setVisible(true);
			} else {
				sb.delete(0, sb.length());
				sb.append("{\"request\" : \"sub_cur_join\",");
				sb.append("\"data\" : {");
				sb.append("\"id\" : \"" + join.clientMain.user_id + "\",");
				sb.append("\"room_num\" : \"" + join.clientMain.chatClient.room_num + "\"");
				sb.append("}}");
				join.clientMain.clientThread.sendMsg(sb);
				join.clientMain.chatClient.disConnection();
				join.guestUI.screenUI.dispose();
				join.clientMain.chatClient.dispose();
				this.dispose();
				join.clientMain.waitingRoom.setVisible(true);
			}
		} else if (obj.equals(btn_screenOn)) {
			if (screenFlag) {
				screenUI.setVisible(false);
				screenFlag = false;
			} else {
				screenUI.setVisible(true);
				screenFlag = true;
			}
		} else if (obj.equals(btn_chatOn)) {
			if (chatFlag) {
				join.clientMain.chatClient.setVisible(false);
				chatFlag = false;
			} else {
				join.clientMain.chatClient.setVisible(true);
				chatFlag = true;
			}
		}
	}
}
