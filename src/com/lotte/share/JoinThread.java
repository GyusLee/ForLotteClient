package com.lotte.share;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.lotte.client.ControllUI;


public class JoinThread extends Thread {
	Join join;
	BufferedReader buffr;
	BufferedWriter buffw;
	ControllUI ui;
	boolean joinflag=true;
	
	StringBuffer sb = new StringBuffer();
	
	public JoinThread(Join join, ControllUI ui) {
		this.join = join;
		this.ui = ui;
		try {
			buffr = new BufferedReader(new InputStreamReader(join.client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(join.client.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readRequest() {
		String msg;
		try {
			msg = buffr.readLine();
			JSONParser parser = new JSONParser();
			JSONObject jsonObejct = (JSONObject) parser.parse(msg);
			if (jsonObejct.get("response").equals("admin")) {
				if (jsonObejct.get("result").equals("ok")) {

					join.guestUI.btn_sreenServer.setEnabled(true);
					join.guestUI.btn_joinServer.setEnabled(false);
					join.guestUI.btn_requestServer.setEnabled(false);
					String hostIp = jsonObejct.get("host").toString();
					if(ui.acceptScreen!=null)
						if(ui.acceptScreen.ip!=hostIp)
							join.guestUI.setIp(hostIp);
				} else if (jsonObejct.get("result").equals("false")) {
					if (jsonObejct.get("alarm").equals("true")) {
						
						join.guestUI.btn_sreenServer.setEnabled(false);
						join.guestUI.btn_joinServer.setEnabled(false);
						join.guestUI.btn_requestServer.setEnabled(true);
						String hostIp = jsonObejct.get("host").toString();
						if(ui.acceptScreen!=null)
							if(ui.acceptScreen.ip!=hostIp)
								join.guestUI.setIp(hostIp);
			
					}
				}
				if(join.guestUI.screenShot!=null)
					join.guestUI.screenShot.exitSocket();
				if(join.guestUI.acceptScreen!=null)
					join.guestUI.acceptScreen.exitAccept();	
			}
			else if(jsonObejct.get("response").equals("createRoom"))
			{
				String hostIp = jsonObejct.get("host").toString();
				if (jsonObejct.get("create").equals("true"))
				{
					join.guestUI.btn_sreenServer.setEnabled(false);
				}else if(jsonObejct.get("create").equals("false"))
				{
					join.guestUI.btn_joinServer.setEnabled(true);
				}
			}
			
			else if(jsonObejct.get("response").equals("towaitingroom"))
			{
				
				if (jsonObejct.get("ishost").equals("true"))
				{
					if(join.guestUI.screenShot!=null){
						join.guestUI.screenShot.exitSocket();
					}
					
					sb.append("{\"request\" : \"deleteroom\",");
					sb.append("\"data\" : {");
					sb.append("\"id\" : \""+join.clientMain.user_id+"\",");
					sb.append("\"room_num\" : \""+join.clientMain.chatClient.room_num+"\"");
					sb.append("}}");
					join.clientMain.clientThread.sendMsg(sb);
					
					join.clientMain.chatClient.disConnection();
					join.clientMain.chatClient.dispose();
					joinflag=false;
					join.guestUI.screenUI.dispose();
					join.guestUI.dispose();
					join.server.serverFlag=false;
					join.server.disconnectionServer();
					
				}
				else if(jsonObejct.get("ishost").equals("false")){
					sb.delete(0, sb.length());
					sb.append("{\"request\" : \"sub_cur_join\",");
					sb.append("\"data\" : {");
					sb.append("\"id\" : \""+join.clientMain.user_id+"\",");
					sb.append("\"room_num\" : \""+join.clientMain.chatClient.room_num+"\"");
					sb.append("}}");
					join.clientMain.clientThread.sendMsg(sb);
					join.clientMain.chatClient.disConnection();
					join.clientMain.chatClient.dispose();
					joinflag=false;
					join.guestUI.screenUI.dispose();
					join.guestUI.dispose();
					
				}
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void requestHost() throws ParseException {
		StringBuffer sb = new StringBuffer();
		sb.append("{");

		sb.append("\"request\":\"admin\",");
		try {
			sb.append("\"ip\":\"" + join.client.getInetAddress().getLocalHost().getHostAddress() + "\",");
			sb.append("\"access\":\"" + join.flag + "\"");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sb.append("}");

		try {
			buffw.write(sb.toString() + "\n");
			buffw.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void requestCreateRoom(){
		StringBuffer sb = new StringBuffer();
		sb.append("{");

		sb.append("\"request\":\"createRoom\",");
		try {
			sb.append("\"ip\":\"" + join.client.getInetAddress().getLocalHost().getHostAddress() + "\"");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sb.append("}");

		try {
			buffw.write(sb.toString() + "\n");
			buffw.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void toWaitingRoom(){
		System.out.println("towaiting room method start");
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"request\":\"towaitingroom\",");
		try {
			sb.append("\"ip\":\"" + join.client.getInetAddress().getLocalHost().getHostAddress() + "\"");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sb.append("}");
		System.out.println("towairing room method last");
		try {
			buffw.write(sb.toString() + "\n");
			buffw.flush();
			System.out.println("towairing room method end");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void run() {
		while (joinflag)
			readRequest();
	}

}
