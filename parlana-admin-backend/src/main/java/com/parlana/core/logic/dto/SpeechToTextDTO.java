package com.parlana.core.logic.dto;

public class SpeechToTextDTO {

	private String transcript;
    private Double confidence;
    
	public String getTranscript() {
		return transcript;
	}
	public void setTranscript(String transcript) {
		this.transcript = transcript;
	}
	public Double getConfidence() {
		return confidence;
	}
	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}
 
}
