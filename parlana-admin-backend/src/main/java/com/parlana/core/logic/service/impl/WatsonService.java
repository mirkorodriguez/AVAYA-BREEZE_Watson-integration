package com.parlana.core.logic.service.impl;

import static com.parlana.core.util.Constants.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.parlana.core.logic.dto.ClassifierDTO;
import com.parlana.core.logic.dto.EmotionDTO;
import com.parlana.core.logic.dto.SentimentDTO;
import com.parlana.core.rest.response.dto.ExtensionResponseDTO;
import com.parlana.core.util.SystemUtil;


public class WatsonService {
	
	private static final Logger logger = LogManager.getLogger(WatsonService.class);
	
	public static ClassifierDTO watsonClassifier(Map<String,Object> params){
		
		String text = (String) params.get("speechToText");
		String handle = (String) params.get("callingNumber");
	    String filename = HOME_RESOURCES_WATSON + "watson_input_classifier.json";
	    String newFilename = HOME_RESOURCES_WATSON + "watson_input_classifier_" + handle + ".json";
	    
	    try {
	    	SystemUtil.copyFile(new File(filename),new File(newFilename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    if(new File(newFilename).exists()) {
	    	SystemUtil.replaceTextInFile(new File(newFilename), "REPLACEME", text);	
	    }
	    
	    String[] command = {"curl","-X","POST","-u",WATSON_CONV_USERNAME + ":" + WATSON_CONV_PASSWORD,"-H","Content-Type: application/json","-d","@"+newFilename, WATSON_CONV_ENDPOINT};
	    	    
		String result = SystemUtil.processCommand(command);
		JSONObject object = new JSONObject(result);
		logger.info("Result: \n"+object);
	    JSONArray intents = object.getJSONArray("intents");

    	String intent = INTENT_DEFAULT;
	    double confidence = 0.0;

	    List<ExtensionResponseDTO> extensionResponseDTOList = (List<ExtensionResponseDTO>) params.get("extensionResponseDTOList"); 
	    
	    for (ExtensionResponseDTO extensionResponseDTO : extensionResponseDTOList) {
			
		}
	    ClassifierDTO classifierDTO = new ClassifierDTO();
    	classifierDTO.setIntent(intent);
	    classifierDTO.setConfidence(confidence);
	    classifierDTO.setExtensionNumber("");
	    classifierDTO.setExtensionAreaName("");
	    classifierDTO.setExtensionPersonFullname("");
	    classifierDTO.setExtensionPersonEmail("");
	    classifierDTO.setExtensionPersonPhone("");
	    
	    if(intents.toString().equals("[]")){
		    return classifierDTO;		    
	    }else {

		    intent = intents.getJSONObject(0).getString("intent");
		    confidence = intents.getJSONObject(0).getDouble("confidence");		    

		    for (ExtensionResponseDTO extensionResponseDTO : extensionResponseDTOList) {
		    	String extensionIntentCode = extensionResponseDTO.getExtensionIntentCode();
		    	if(intent.equals(extensionIntentCode.trim())){
				    Double threasholdIntent = Double.parseDouble(extensionResponseDTO.getExtensionIntentConfidence()); 
				    if(confidence < threasholdIntent) {
				    	return classifierDTO;
				    }else {
				    	
			    		String extensionNumber = extensionResponseDTO.getExtensionNumber();
			    		String extensionAreaName = extensionResponseDTO.getExtensionAreaName();
			    		String extensionPersonFullname = extensionResponseDTO.getExtensionPersonFullname();
			    		String extensionPersonEmail = extensionResponseDTO.getExtensionPersonEmail();
			    		String extensionPersonPhone = extensionResponseDTO.getExtensionPersonPhone();
					    
			        	classifierDTO.setIntent(intent);
			    	    classifierDTO.setConfidence(confidence);
					    classifierDTO.setExtensionNumber(extensionNumber);
					    classifierDTO.setExtensionAreaName(extensionAreaName);
					    classifierDTO.setExtensionPersonFullname(extensionPersonFullname);
					    classifierDTO.setExtensionPersonEmail(extensionPersonEmail);
					    classifierDTO.setExtensionPersonPhone(extensionPersonPhone);
				    }

				    break;
		    	}
			}
	    }
	    	    
	    (new File(newFilename)).delete();
		return classifierDTO;
	}
	
	public static SentimentDTO watsonNluSentiment(String text, String handle){
		String filename = HOME_RESOURCES_WATSON + "watson_input_nlu_sentiment.json";
		String newFilename = HOME_RESOURCES_WATSON + "watson_input_nlu_sentiment_" + handle + ".json";
	    try {
	    	SystemUtil.copyFile(new File(filename),new File(newFilename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    if(new File(newFilename).exists()) {
	    	SystemUtil.replaceTextInFile(new File(newFilename), "REPLACEME", text);	
	    }
		
		String[] command = {"curl","-X","POST","-H","Content-Type: application/json","-u",WATSON_NLU_USERNAME + ":" + WATSON_NLU_PASSWORD,"-d","@"+newFilename,WATSON_NLU_ENDPOINT};
		
		String result = SystemUtil.processCommand(command);
		JSONObject object = new JSONObject(result);
		logger.info("Result: \n " + object);
	    JSONObject sentiment = object.getJSONObject("sentiment");
	    JSONObject document = sentiment.getJSONObject("document");
	    Double sentimentScore = document.getDouble("score");
	    String sentimentLabel = document.getString("label");
	    SentimentDTO sentimentDTO = new SentimentDTO();
	    sentimentDTO.setSentimentScore(sentimentScore);
	    sentimentDTO.setSentimentLabel(sentimentLabel);
	    (new File(newFilename)).delete();
	    return sentimentDTO;
	}
	
	public static EmotionDTO watsonNluEmotion(String text, String handle){
		String filename = HOME_RESOURCES_WATSON + "watson_input_nlu_emotion.json";
		String newFilename = HOME_RESOURCES_WATSON + "watson_input_nlu_emotion_" + handle + ".json";
		
	    try {
	    	SystemUtil.copyFile(new File(filename),new File(newFilename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    if(new File(newFilename).exists()) {
	    	SystemUtil.replaceTextInFile(new File(newFilename), "REPLACEME", text);	
	    }
		
		String[] command = {"curl","-X","POST","-H","Content-Type: application/json","-u",WATSON_NLU_USERNAME + ":" + WATSON_NLU_PASSWORD,"-d","@"+newFilename,WATSON_NLU_ENDPOINT};
		
		String result = SystemUtil.processCommand(command);
		JSONObject object = new JSONObject(result);
		logger.info("Result: \n" + object);
		
	    JSONObject emotion = object.getJSONObject("emotion");
	    JSONObject document = emotion.getJSONObject("document");
	    JSONObject emotionDetail = document.getJSONObject("emotion");

	    Double sadness = emotionDetail.getDouble("sadness");
	    Double joy = emotionDetail.getDouble("joy");
	    Double fear = emotionDetail.getDouble("fear");
	    Double disgust = emotionDetail.getDouble("disgust");
	    Double anger = emotionDetail.getDouble("anger");
	    
	    EmotionDTO emotionDTO = new EmotionDTO();
	    emotionDTO.setSadness(sadness);
	    emotionDTO.setJoy(joy);
	    emotionDTO.setFear(fear);
	    emotionDTO.setDisgust(disgust);
	    emotionDTO.setAnger(anger);
	    
	    logger.info("--> TRISTEZA: " + sadness);
	    logger.info("--> ALEGRIA: " + joy);
	    logger.info("--> MIEDO: " + fear);
	    logger.info("--> DISGUSTO: " + disgust);
	    logger.info("--> ENFADO: " + anger);
	    
	    (new File(newFilename)).delete();
	    return emotionDTO;
	}

	public static String watsonTranslator(String text, String source, String target){
//		String filename = HOME_RESOURCES_WATSON + "watson_input_translator.json";
//		String newFilename = HOME_RESOURCES_WATSON + "watson_input_translator_tmp.json";
		
//	    try {
//			SystemUtil.copyFile(new File(filename),new File(newFilename));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	    
//	    if(new File(newFilename).exists()) {
//	    	SystemUtil.replaceTextInFile(new File(newFilename), "REPLACEME", text);	
//	    }
		
		//String[] command = {"curl","-X","POST","-H","Content-Type: application/json","-H","Accept: application/json","-u",WATSON_TRANSLATOR_USERNAME + ":" + WATSON_TRANSLATOR_PASSWORD,"-d","@"+newFilename,WATSON_TRANSLATOR_ENDPOINT};
	    //String[] command = {"curl","-X","POST","-H","Content-Type: application/json","-H","Accept: application/json","-u",WATSON_TRANSLATOR_USERNAME + ":" + WATSON_TRANSLATOR_PASSWORD,"-d","{ \"text\" : \"" + text + "\", \"source\" : \"" + source + "\", \"target\" : \"" + target + "\" }",WATSON_TRANSLATOR_ENDPOINT};
		String[] command = {"curl","-X","POST","-H","Content-Type: application/json","-H","Accept: application/json","-u",WATSON_TRANSLATOR_USERNAME + ":" + WATSON_TRANSLATOR_PASSWORD,"-d","{ \"text\" : \"" + text + "\", \"model_id\" : \"" + source + "-" + target + "-conversational\" }",WATSON_TRANSLATOR_ENDPOINT};

		String result = SystemUtil.processCommand(command);
		JSONObject object = new JSONObject(result);
		logger.info("Result: \n" + object);
		
		JSONArray translations = object.getJSONArray("translations");
		
//		(new File(newFilename)).delete();
	    return translations.getJSONObject(0).getString("translation");
	}

}
