package com.lotte.client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.lotte.client.ClientThread;
import com.lotte.client.WaitingRoom;
import com.lotte.share.Server;

public class ClientMain extends JFrame implements ActionListener {

	Socket socket = null;
	String host = "10.131.152.120";
	int port = 5555;

	JPanel p_id, p_pwd, p_bt, p_logo;
	JLabel l_id, l_pwd, l_logo;
	JTextField txt_id;
	JPasswordField txt_pwd;
	JButton bt_login, bt_cancel, bt_regist;

	public String user_id;
	String user_name;
	String host_ip;

	public ClientThread clientThread;
	public ChatClient chatClient;
	
	WaitingRoom waitingRoom;
	StringBuffer sb = new StringBuffer();

	
	public ArrayList<String[]> conList=new ArrayList<String[]>();
	
	public ClientMain() {
		try {
			socket = new Socket(host, port);
			clientThread = new ClientThread(this);
			clientThread.start();
		} catch (IOException e) {
			System.out.println("[ Debug ] : Socket Connection Error.");
		}
		setupUI();
	}

	public void setupUI() {
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

	public void exitWindow() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("[ Debug ] : Socket is Closed");
			}
		}
		System.exit(0);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_cancel) {
			exitWindow();
		} else if (e.getSource() == bt_login) {
			login();
		}
	}

	public static void main(String[] args) {
		new ClientMain();
	}

	public void login() {
		String buff = new String(txt_pwd.getPassword());
		sb.delete(0, sb.length());
		sb.append("{\"request\" : \"login\",");
		sb.append("\"data\" : {");
		sb.append("\"id\" : \"" + txt_id.getText() + "\",");
		sb.append("\"password\" : \"" + buff + "\"");
		sb.append("}}");
		clientThread.sendMsg(sb);
	}

public void result(String response){
		
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject=(JSONObject)jsonParser.parse(response);
			
			if(jsonObject.get("response").equals("login")){
				JSONObject obj=(JSONObject)jsonObject.get("data");
				if(obj.get("success").equals("true")){
					user_id = obj.get("id").toString();
					user_name = obj.get("name").toString();
					

					if(user_id.equals("admin")){
						//searchId=new ManageMain(this);
						JOptionPane.showMessageDialog(this, "관리자로 로그인");
					}else{
						waitingRoom = new WaitingRoom(this);
						JOptionPane.showMessageDialog(this, user_id+"님 환영합니다.");
					}
					this.setVisible(false);
				}
				else if(obj.get("success").equals("false")){
					JOptionPane.showMessageDialog(this, "로그인 실패");
				}
			}
			else if (jsonObject.get("response").equals("logedin")){
				JSONObject obj=(JSONObject)jsonObject.get("data");
				if(obj.get("success").equals("false")){
					JOptionPane.showMessageDialog(this, "해당 아이디는 이미 로그인 상태입니다.");
				}
			}
			else if (jsonObject.get("response").equals("disconnect")){
				JSONObject obj=(JSONObject)jsonObject.get("data");
				if(obj.get("success").equals("true")){
					exitWindow();
				}
			}
			
			else if (jsonObject.get("response").equals("showconnector")) {
				if (jsonObject.get("success").equals("true")) {
					JSONArray jsonArray=(JSONArray)jsonObject.get("data");
					conList=new ArrayList<String[]>();
					for(int i=0;i<jsonArray.size();i++){
						String[] data=new String[2];
						JSONObject obj=(JSONObject)jsonArray.get(i);
						data[0]=(String)obj.get("name");
						data[1]=(String)obj.get("ID");
						conList.add(data);
					}
					
				} else if (jsonObject.get("success").equals("false")) {
					JOptionPane.showMessageDialog(waitingRoom, "접속자 목록 불러오기 실패");
				}
			}
			else if (jsonObject.get("response").equals("create_room")){
				JSONObject obj=(JSONObject)jsonObject.get("data");
				if(obj.get("success").equals("true")){
					
					host_ip = (String)obj.get("host_ip");
					Server sub_server = new Server(host_ip,this);
					chatClient=new ChatClient(this, 1);
					waitingRoom.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(waitingRoom, "");
				}
			}
			
		} catch (ParseException e) {
			System.out.println("[ Debug ] : Error occured on JSON Parsing ");
		}
	}

}
