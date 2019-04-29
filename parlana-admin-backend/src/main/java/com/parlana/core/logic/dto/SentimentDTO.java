package com.parlana.core.logic.dto;

public class SentimentDTO {

    private String sentimentLabel;
	private Double sentimentScore;
	
	public String getSentimentLabel() {
		return sentimentLabel;
	}
	public void setSentimentLabel(String sentimentLabel) {
		this.sentimentLabel = sentimentLabel;
	}
	public Double getSentimentScore() {
		return sentimentScore;
	}
	public void setSentimentScore(Double sentimentScore) {
		this.sentimentScore = sentimentScore;
	}
	@Override
	public String toString() {
		return "SentimentDTO [sentimentLabel=" + sentimentLabel + ", sentimentScore=" + sentimentScore + "]";
	}

}
