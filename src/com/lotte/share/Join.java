package com.lotte.share;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.simple.parser.ParseException;

import com.lotte.client.ClientMain;
import com.lotte.client.ControllUI;


public class Join extends JFrame{
	Socket client;
	public String ip;
	int port = 8888;
	BufferedReader buffr;
	BufferedWriter buffw;
	public JoinThread gt;
	boolean flag = false;
	public ControllUI guestUI;
	boolean isHost;
	public ClientMain clientMain;
	public Server server;
	public Join(String ip, boolean isHost,ClientMain clientMain) {
		this.clientMain=clientMain;
		this.ip = ip;
		this.isHost = isHost;
		
		if(isHost){
			guestUI=new ControllUI(ip, true,this);
			connect();
		}else{
			guestUI=new ControllUI(ip, false,this);
			connect();
		}
	}
	public Join(String ip, boolean isHost,ClientMain clientMain,Server server) {
		this.server=server;
		this.clientMain=clientMain;
		this.ip = ip;
		this.isHost = isHost;
		
		if(isHost){
			guestUI=new ControllUI(ip, true,this);
			connect();
		}else{
			guestUI=new ControllUI(ip, false,this);
			connect();
		}
	}

	public void connect() {
		try {
			client = new Socket(ip, port);
			System.out.println(client);
			System.out.println(isHost);
			gt=new JoinThread(this,guestUI);
			gt.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void joinRequest(){
		try {
			gt.requestHost();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void joinCreateRoom()
	{
		gt.requestCreateRoom();
	}

}
