package com.parlana.core.logic.dto;

public class ClassifierDTO {
    
	private String intent;
    private Double confidence;
    
    //Data
    private String extensionNumber;
    private String extensionAreaName;
    private String extensionPersonFullname;
    private String extensionPersonEmail;
    private String extensionPersonPhone;

    private String ticketNumber;

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public Double getConfidence() {
		return confidence;
	}

	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}

	public String getExtensionNumber() {
		return extensionNumber;
	}

	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}

	public String getExtensionAreaName() {
		return extensionAreaName;
	}

	public void setExtensionAreaName(String extensionAreaName) {
		this.extensionAreaName = extensionAreaName;
	}

	public String getExtensionPersonFullname() {
		return extensionPersonFullname;
	}

	public void setExtensionPersonFullname(String extensionPersonFullname) {
		this.extensionPersonFullname = extensionPersonFullname;
	}

	public String getExtensionPersonEmail() {
		return extensionPersonEmail;
	}

	public void setExtensionPersonEmail(String extensionPersonEmail) {
		this.extensionPersonEmail = extensionPersonEmail;
	}

	public String getExtensionPersonPhone() {
		return extensionPersonPhone;
	}

	public void setExtensionPersonPhone(String extensionPersonPhone) {
		this.extensionPersonPhone = extensionPersonPhone;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	@Override
	public String toString() {
		return "ClassifierDTO [intent=" + intent + ", confidence=" + confidence + ", extensionNumber=" + extensionNumber
				+ ", extensionAreaName=" + extensionAreaName + ", extensionPersonFullname=" + extensionPersonFullname
				+ ", extensionPersonEmail=" + extensionPersonEmail + ", extensionPersonPhone=" + extensionPersonPhone
				+ ", ticketNumber=" + ticketNumber + "]";
	}
	
}
