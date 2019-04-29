package com.parlana.core.logic.service.impl;

import static com.parlana.core.util.Constants.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.parlana.core.logic.dto.SpeechToTextDTO;
import com.parlana.core.util.SystemUtil;

public class GoogleService {

	private static final Logger logger = LogManager.getLogger(GoogleService.class);
	
	public static SpeechToTextDTO googleSpeechRecognition(Map<String, Object> params){

	    String filenameOnGoogleStorage = (String) params.get("filenameOnGoogleStorage");
		String googleTmpToken = (String)params.get("gToken");
    	String googleTmpLang = (String)params.get("centralLang");
	    String callingNumber = (String) params.get("callingNumber");

		String filename = HOME_RESOURCES_GOOGLE + "google_speech_payload.json";
	    String newFilename = HOME_RESOURCES_GOOGLE + "google_speech_payload_" + callingNumber + ".json";
	    
	    try {
	    	SystemUtil.copyFile(new File(filename),new File(newFilename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    	    
	    if(new File(newFilename).exists()) {
	    	logger.info("FILE: " + (new File(newFilename)).getName() + ", EXISTS: true");
		    SystemUtil.replaceTextInFile(new File(newFilename), "REPLACEME", GOOGLE_STORAGE_GS_URI + filenameOnGoogleStorage);
		    SystemUtil.replaceTextInFile(new File(newFilename), "LANGUAGE", googleTmpLang);
	    }

		
	    String[] command = {"curl","-s","-H","Content-Type: application/json","-H","Authorization: Bearer " + googleTmpToken, GOOGLE_SPEECH_API_URI,"-d","@" + newFilename};
	    String result = SystemUtil.processCommand(command);
	    
	    logger.info("RESULT: \n" + result);
	    if(result == null || result == "" || result.length() == 0) {
	    	SpeechToTextDTO r = new SpeechToTextDTO();
		    r.setTranscript("");
		    r.setConfidence(0.0);
	    	return r;
	    }
	    
	    JSONObject object = new JSONObject(result);
	    JSONArray results = object.getJSONArray("results");
	    
	    Double confidence = 0.0;
	    String transcript = "";
	    
	    if(results != null && results.length() != 0){
		    for (int i=0; i < results.length(); i++) {
		    	JSONArray alternatives = results.getJSONObject(i).getJSONArray("alternatives");
		    	Double confidencetmp = alternatives.getJSONObject(0).getDouble("confidence");
		    	transcript = transcript + alternatives.getJSONObject(0).getString("transcript");
		    	if(confidencetmp > confidence){
		    	    confidence = alternatives.getJSONObject(0).getDouble("confidence");	    		
		    	}
			}	    	
	    }
	    
	    (new File(newFilename)).delete();
	    
	    SpeechToTextDTO speechToTextDTO = new SpeechToTextDTO();
	    speechToTextDTO.setTranscript(transcript);
	    speechToTextDTO.setConfidence(confidence);
		return speechToTextDTO;
	}
}
