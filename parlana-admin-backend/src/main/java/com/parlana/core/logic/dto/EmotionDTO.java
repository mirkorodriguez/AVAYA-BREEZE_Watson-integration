package com.parlana.core.logic.dto;

public class EmotionDTO {

    private Double sadness;
    private Double joy;
    private Double fear;
    private Double disgust;
    private Double anger;
    
	public Double getSadness() {
		return sadness;
	}
	public void setSadness(Double sadness) {
		this.sadness = sadness;
	}
	public Double getJoy() {
		return joy;
	}
	public void setJoy(Double joy) {
		this.joy = joy;
	}
	public Double getFear() {
		return fear;
	}
	public void setFear(Double fear) {
		this.fear = fear;
	}
	public Double getDisgust() {
		return disgust;
	}
	public void setDisgust(Double disgust) {
		this.disgust = disgust;
	}
	public Double getAnger() {
		return anger;
	}
	public void setAnger(Double anger) {
		this.anger = anger;
	}
	@Override
	public String toString() {
		return "EmotionDTO [sadness=" + sadness + ", joy=" + joy + ", fear=" + fear + ", disgust=" + disgust
				+ ", anger=" + anger + "]";
	}

}
