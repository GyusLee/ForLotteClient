package com.lotte.share;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.lotte.share.Join;

public class ScreenShotServer implements Runnable {


	public Thread acceptThread;
	public SrceenShotThread st;
	public ServerSocket server;
	int port = 9998;
	public Vector<SrceenShotThread> screenlist=new Vector<SrceenShotThread>();
	public ArrayList<Socket> joinList=new ArrayList<Socket>();
	public boolean flag=true;
	Join join;
	public ScreenShotServer(Join join) {
		this.join=join;
		acceptThread = new Thread(this);
		acceptThread.start();
	}


	public void run() {
		startServer();
	}


	public void startServer() {
		try {
			server = new ServerSocket(port);	

			while (flag) {
				Socket client = server.accept();
				System.out.println(client);
				joinList.add(client);
				
				st=new SrceenShotThread(client,this);
				st.start();
				screenlist.add(st);
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	public void searchServer(){
		for(int i=0;i<joinList.size();i++)
		{
			String guestIp=joinList.get(i).getInetAddress().getHostAddress();
			System.out.println(guestIp);
			screenlist.get(i).flagScreen=false;
			try {
				screenlist.get(i).out.close();
				screenlist.get(i).in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			screenlist.remove(screenlist);	
		}
		try {
			if(server!=null)
			{
				server.close();
				flag=false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void exitSocket(){
		for(int i=0;i<screenlist.size();i++)
		{
			screenlist.get(i).flagScreen=false;
		}
	}
}
