package com.etc.entity;

public class Message {
	private int messageId;
	private String senderName;
	private String receiveName;
	private String content;
	private String time;
	private int state;

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(int messageId, String senderName, String receiveName, String content, String time, int state) {
		super();
		this.messageId = messageId;
		this.senderName = senderName;
		this.receiveName = receiveName;
		this.content = content;
		this.time = time;
		this.state = state;
	}

	public Message(int messageId, String content, String time) {
		super();
		this.messageId = messageId;
		this.content = content;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", senderName=" + senderName + ", receiveName=" + receiveName
				+ ", content=" + content + ", time=" + time + ", state=" + state + "]";
	}

}
