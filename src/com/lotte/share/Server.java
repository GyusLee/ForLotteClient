package com.lotte.share;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;

import com.lotte.client.ClientMain;

public class Server extends JFrame implements Runnable{

	ServerSocket server;
	Thread acceptThead;
	Vector<ServerThread> serverList=new Vector<ServerThread>();
	InetAddress inetAddress;
	ArrayList<Socket> socketList=new ArrayList<Socket>();
	String ip;
	public Join join;
	ClientMain clientMain;
	Socket client;
	public ServerThread st;
	public boolean serverFlag=true;
	
	public Server(String ip,ClientMain clientMain) {
		this.clientMain=clientMain;
		this.ip = ip;
		
		acceptThead=new Thread(this);					
		acceptThead.start();
	}
	
	public void startServer(){
		try {
			server=new ServerSocket(9997);
			join =  new Join(ip, true,clientMain,this);	
			while (serverFlag) {
				client = server.accept();
				socketList.add(client);
				st = new ServerThread(client, this);
				st.start();
				serverList.add(st);
				String ip=client.getInetAddress().getHostAddress();
			}			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public void disconnectionServer(){
		if(server!=null){
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void run() {
		startServer();
	}	

}
