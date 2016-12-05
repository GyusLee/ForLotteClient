package com.lotte.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClientThread extends Thread{

	BufferedReader buffr;
	BufferedWriter buffw;
	StringBuffer sb = new StringBuffer();
	boolean flag;
	ClientMain clientMain;
	
	public ClientThread(ClientMain	clientMain) {
		flag=true;
		this.clientMain=clientMain;
		try {
			buffr = new BufferedReader(new InputStreamReader(clientMain.socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(clientMain.socket.getOutputStream()));
		} catch (IOException e) {
			System.out.println("[ Debug ] : Socket is Closed");
		}
	}

	public void sendMsg(StringBuffer sb) {
		try {
			buffw.write(sb.toString()+"\n");
			buffw.flush();
		} catch (IOException e) {
			System.out.println("[ Debug ] : Error on Buffer");
		}
	}

	public void listen() {
		try {
			String response = buffr.readLine();
			//clientMain.result(response);
		} catch (IOException e) {
			System.out.println("[ Debug ] : Error on Buffer Listener");
		}
	}

	public void run() {
		while(flag){
			listen();
		}
	}
}
