package pe.com.mirko.parlana.ivr.service;

import static pe.com.mirko.parlana.ivr.util.Constants.HOME_PATH_COGNITIVE_IVR;

import java.util.Map;
import java.util.UUID;
import com.avaya.collaboration.util.logger.Logger;
import com.avaya.zephyr.platform.dal.api.ServiceUtil;

import pe.com.mirko.parlana.ivr.util.SystemUtil;

public class GoogleService {

private static Logger logger = Logger.getLogger(GoogleService.class);

	public static String googleSpeechRecognitionUpload(String handle, Map<String,Object> params){

		final String myServiceName = ServiceUtil.getServiceDescriptor().getName();
        final StringBuilder sb = new StringBuilder();
		sb.append("recording")
        .append(myServiceName)
        .append("_")
        .append(handle)
        .append(".wav");
        
		String audioFilename = HOME_PATH_COGNITIVE_IVR + sb.toString();
	    String audioFilenameUpload = UUID.randomUUID().toString() + "_" + handle + ".wav";
	    String gToken = (String) params.get("gToken");
	    String gStorageUri = (String) params.get("gStorageUri");
    
	    //Uploading file to google storage
	    String[] commandUpload = {"curl","--upload-file",audioFilename,"-H","Authorization: Bearer " + gToken, gStorageUri + audioFilenameUpload};
	    
	    for (String string : commandUpload) {
	    	logger.info(string);
		}
	    
	    
	    String result = SystemUtil.processCommand(commandUpload);
	    logger.info("RESULT: \n" + result);
	    logger.info("FILE UPLOADED: " + audioFilenameUpload);
	    return audioFilenameUpload;
	}
}
