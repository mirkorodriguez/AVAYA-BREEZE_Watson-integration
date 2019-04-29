package com.parlana.core.rest.request.dto;

public class EmailRequestDTO {

	private String  to;
	private String 	subject;
	private String  toName;
	private String  msgBody;
	private String  transcript;
	private String  intent;
	private String  sentimentLabel;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getTranscript() {
		return transcript;
	}
	public void setTranscript(String transcript) {
		this.transcript = transcript;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getSentimentLabel() {
		return sentimentLabel;
	}
	public void setSentimentLabel(String sentimentLabel) {
		this.sentimentLabel = sentimentLabel;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
