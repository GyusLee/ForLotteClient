package com.lotte.client;

import javax.swing.table.AbstractTableModel;

public class MemberModel extends AbstractTableModel{
	String[] columnTitle = {
			"아이디(이름)"			
	};
	MemberList memberList;
	public MemberModel(MemberList memberList) {
		this.memberList=memberList;
	}
	
	public String getColumnName(int column) {
		return columnTitle[column];
	}
	
	public int getColumnCount() {
		return columnTitle.length;
	}

	public int getRowCount() {
		return memberList.waitingRoom.clientMain.conList.size();
	}

	public Object getValueAt(int row	, int col) {
		String obj = memberList.waitingRoom.clientMain.conList.get(row)[col+1]+"("+memberList.waitingRoom.clientMain.conList.get(row)[col]+")";
		return obj;
	}

}
