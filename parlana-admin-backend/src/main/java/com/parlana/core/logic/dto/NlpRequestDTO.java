package com.parlana.core.logic.dto;

public class NlpRequestDTO {

	private String callingNumber;
	private String centralNumber;
	private String centralCode;
	private String filenameOnGoogleStorage;
	private String gToken;
	
	public String getCallingNumber() {
		return callingNumber;
	}
	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}
	public String getCentralNumber() {
		return centralNumber;
	}
	public void setCentralNumber(String centralNumber) {
		this.centralNumber = centralNumber;
	}
	public String getCentralCode() {
		return centralCode;
	}
	public void setCentralCode(String centralCode) {
		this.centralCode = centralCode;
	}
	public String getFilenameOnGoogleStorage() {
		return filenameOnGoogleStorage;
	}
	public void setFilenameOnGoogleStorage(String filenameOnGoogleStorage) {
		this.filenameOnGoogleStorage = filenameOnGoogleStorage;
	}
	public String getgToken() {
		return gToken;
	}
	public void setgToken(String gToken) {
		this.gToken = gToken;
	}
	
}
