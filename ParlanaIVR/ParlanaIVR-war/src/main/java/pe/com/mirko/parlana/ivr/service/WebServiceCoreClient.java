package pe.com.mirko.parlana.ivr.service;

import static pe.com.mirko.parlana.ivr.util.Constants.WS_CREDENTIALS_GETOBJ_URI;
import static pe.com.mirko.parlana.ivr.util.Constants.WS_NLP_PROCESS_URI;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.avaya.collaboration.util.logger.Logger;

import pe.com.mirko.parlana.ivr.AttributeStore;
import pe.com.mirko.parlana.ivr.util.SystemUtil;

public class WebServiceCoreClient {

	private static Logger logger = Logger.getLogger(WebServiceCoreClient.class);	
	
	public static Map<String, Object> getGenericCredentialsFromWS(String key) {
    	logger.info("key: " + key);
    	String endPointBase = "";
		try {
			endPointBase = AttributeStore.INSTANCE.getAttributeValue("WS_BASE_BACKEND_ENDPOINT");
		} catch (Exception e) {
			logger.info("Error obtaining global param WS_BASE_BACKEND_ENDPOINT");
			e.printStackTrace();
		}
		
    	String endPoint = endPointBase + WS_CREDENTIALS_GETOBJ_URI;
    	logger.info("endPoint: " + endPoint);
    	
    	String[] command = {"curl","-s","-H","Content-Type: application/json", endPoint,"-d","{ \"key\": \""+key+"\" }"};

	    for (String string : command) {
	    	logger.info(string);
		}
	    
	    String result = SystemUtil.processCommand(command);
	    JSONObject object = new JSONObject(result);
	    logger.info("\nResult: " + object);
	    logger.info("\n\n");
	    
	    HashMap<String,Object> outputParams = new HashMap<String,Object>();
	    
	    String gToken = object.getString("gToken");
	    String gStorageUri = object.getString("gStorageUri");
	    
	    outputParams.put("gToken", gToken.trim());
	    outputParams.put("gStorageUri", gStorageUri.trim());	    
	    
	    return outputParams;
	}

	public static Map<String, Object> processNLPFromWS(Map<String, Object> inputParams) {
		
	    String gToken = (String) inputParams.get("gToken");
	    
    	String endPointBase = "";
    	String centralCode = "";
		try {
			endPointBase = AttributeStore.INSTANCE.getAttributeValue("WS_BASE_BACKEND_ENDPOINT");
			centralCode = AttributeStore.INSTANCE.getAttributeValue("CENTRAL_CODE");
		} catch (Exception e) {
			logger.info("Error obtaining global param WS_BASE_BACKEND_ENDPOINT");
			e.printStackTrace();
		}
		
    	String endPoint = endPointBase + WS_NLP_PROCESS_URI;
    	logger.info("endPoint: " + endPoint);
    	
		String centralNumber = (String) inputParams.get("centralNumber");
		String callingNumber = (String) inputParams.get("callingNumber");
		String filenameOnGoogleStorage = (String) inputParams.get("filenameOnGoogleStorage");
    	
    	String[] command = {"curl","-s","-H","Content-Type: application/json", endPoint,"-d","{ \"gToken\": \"" + gToken + "\", " +
    			 																			 "  \"centralNumber\": \"" + centralNumber + "\", " +
    			 																			 "  \"centralCode\": \"" + centralCode + "\", " +
    			 																			 "  \"callingNumber\": \"" + callingNumber + "\", " +
    			 																			 "  \"filenameOnGoogleStorage\": \"" + filenameOnGoogleStorage + "\" " +
    			 																			 "}"};

	    String result = SystemUtil.processCommand(command);
	    JSONObject object = new JSONObject(result);
	    logger.info("\nResult: " + object);
	    logger.info("\n\n");
	    
	    HashMap<String,Object> allData = new HashMap<String,Object>();
	    allData.put("callEventFinalExtension", object.get("callEventFinalExtension"));
	    allData.put("callEventDatetime", object.get("callEventDatetime"));
	    allData.put("callEventIntentCode", object.get("callEventIntentCode"));
	    allData.put("callEventIntentConfidence", object.get("callEventIntentConfidence"));
	    allData.put("callEventFrom", object.get("callEventFrom"));
	    allData.put("callEventTo", object.get("callEventTo"));
	    allData.put("centralNumberPstn", object.get("centralNumberPstn"));
	    allData.put("callEventText", object.get("callEventText"));
	    allData.put("callEventFinalSentimentLabel", object.get("callEventFinalSentimentLabel"));
	    allData.put("callEventFinalSentimentScore", object.get("callEventFinalSentimentScore"));
	    
	    allData.put("extensionPersonEmail", object.get("extensionPersonEmail"));
	    allData.put("extensionPersonPhone", object.get("extensionPersonPhone"));	    
	    
	    return allData;
	}
	
}
