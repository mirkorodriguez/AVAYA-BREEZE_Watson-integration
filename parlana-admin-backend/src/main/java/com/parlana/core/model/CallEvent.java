package com.parlana.core.model;

public class CallEvent {
    private Long idCallEvent;

    private String callEventDatetime;

    private String callEventFrom;

    private String callEventTo;
    
    private String callEventToCode;

    private String callEventAudioWav;

    private String callEventText;
    
    private String callEventTextConfidence;

    private String callEventIntentCode;

    private String callEventIntentConfidence;

    private String callEventFinalExtension;

    private String callEventFinalSentimentLabel;

    private String callEventFinalSentimentScore;

    private String callEventFinalEmotionSadness;

    private String callEventFinalEmotionJoy;

    private String callEventFinalEmotionFear;

    private String callEventFinalEmotionDisgust;

    private String callEventFinalEmotionAnger;

    public Long getIdCallEvent() {
        return idCallEvent;
    }

    public void setIdCallEvent(Long idCallEvent) {
        this.idCallEvent = idCallEvent;
    }

    public String getCallEventToCode() {
		return callEventToCode;
	}

	public void setCallEventToCode(String callEventToCode) {
		this.callEventToCode = callEventToCode;
	}

	public String getCallEventDatetime() {
        return callEventDatetime;
    }

    public void setCallEventDatetime(String callEventDatetime) {
        this.callEventDatetime = callEventDatetime == null ? null : callEventDatetime.trim();
    }

    public String getCallEventFrom() {
        return callEventFrom;
    }

    public void setCallEventFrom(String callEventFrom) {
        this.callEventFrom = callEventFrom == null ? null : callEventFrom.trim();
    }

    public String getCallEventTo() {
        return callEventTo;
    }

    public void setCallEventTo(String callEventTo) {
        this.callEventTo = callEventTo == null ? null : callEventTo.trim();
    }

    public String getCallEventAudioWav() {
        return callEventAudioWav;
    }

    public void setCallEventAudioWav(String callEventAudioWav) {
        this.callEventAudioWav = callEventAudioWav == null ? null : callEventAudioWav.trim();
    }

    public String getCallEventText() {
        return callEventText;
    }

    public void setCallEventText(String callEventText) {
        this.callEventText = callEventText == null ? null : callEventText.trim();
    }
    
    public String getCallEventTextConfidence() {
		return callEventTextConfidence;
	}

	public void setCallEventTextConfidence(String callEventTextConfidence) {
		this.callEventTextConfidence = callEventTextConfidence;
	}

	public String getCallEventIntentCode() {
        return callEventIntentCode;
    }

    public void setCallEventIntentCode(String callEventIntentCode) {
        this.callEventIntentCode = callEventIntentCode == null ? null : callEventIntentCode.trim();
    }

    public String getCallEventIntentConfidence() {
        return callEventIntentConfidence;
    }

    public void setCallEventIntentConfidence(String callEventIntentConfidence) {
        this.callEventIntentConfidence = callEventIntentConfidence == null ? null : callEventIntentConfidence.trim();
    }

    public String getCallEventFinalExtension() {
        return callEventFinalExtension;
    }

    public void setCallEventFinalExtension(String callEventFinalExtension) {
        this.callEventFinalExtension = callEventFinalExtension == null ? null : callEventFinalExtension.trim();
    }

    public String getCallEventFinalSentimentLabel() {
        return callEventFinalSentimentLabel;
    }

    public void setCallEventFinalSentimentLabel(String callEventFinalSentimentLabel) {
        this.callEventFinalSentimentLabel = callEventFinalSentimentLabel == null ? null : callEventFinalSentimentLabel.trim();
    }

    public String getCallEventFinalSentimentScore() {
        return callEventFinalSentimentScore;
    }

    public void setCallEventFinalSentimentScore(String callEventFinalSentimentScore) {
        this.callEventFinalSentimentScore = callEventFinalSentimentScore == null ? null : callEventFinalSentimentScore.trim();
    }

    public String getCallEventFinalEmotionSadness() {
        return callEventFinalEmotionSadness;
    }

    public void setCallEventFinalEmotionSadness(String callEventFinalEmotionSadness) {
        this.callEventFinalEmotionSadness = callEventFinalEmotionSadness == null ? null : callEventFinalEmotionSadness.trim();
    }

    public String getCallEventFinalEmotionJoy() {
        return callEventFinalEmotionJoy;
    }

    public void setCallEventFinalEmotionJoy(String callEventFinalEmotionJoy) {
        this.callEventFinalEmotionJoy = callEventFinalEmotionJoy == null ? null : callEventFinalEmotionJoy.trim();
    }

    public String getCallEventFinalEmotionFear() {
        return callEventFinalEmotionFear;
    }

    public void setCallEventFinalEmotionFear(String callEventFinalEmotionFear) {
        this.callEventFinalEmotionFear = callEventFinalEmotionFear == null ? null : callEventFinalEmotionFear.trim();
    }

    public String getCallEventFinalEmotionDisgust() {
        return callEventFinalEmotionDisgust;
    }

    public void setCallEventFinalEmotionDisgust(String callEventFinalEmotionDisgust) {
        this.callEventFinalEmotionDisgust = callEventFinalEmotionDisgust == null ? null : callEventFinalEmotionDisgust.trim();
    }

	public String getCallEventFinalEmotionAnger() {
		return callEventFinalEmotionAnger;
	}

	public void setCallEventFinalEmotionAnger(String callEventFinalEmotionAnger) {
		this.callEventFinalEmotionAnger = callEventFinalEmotionAnger;
	}

}