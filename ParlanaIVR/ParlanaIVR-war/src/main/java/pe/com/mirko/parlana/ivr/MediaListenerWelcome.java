package pe.com.mirko.parlana.ivr;

import static pe.com.mirko.parlana.ivr.util.Constants.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.avaya.collaboration.businessdata.api.NoAttributeFoundException;
import com.avaya.collaboration.businessdata.api.ServiceNotFoundException;
import com.avaya.collaboration.call.Call;
import com.avaya.collaboration.call.Participant;
import com.avaya.collaboration.call.media.DigitCollectorOperationCause;
import com.avaya.collaboration.call.media.MediaListenerAbstract;
import com.avaya.collaboration.call.media.MediaServerInclusion;
import com.avaya.collaboration.call.media.PlayOperationCause;
import com.avaya.collaboration.call.media.RecordOperationCause;
import com.avaya.collaboration.call.media.SendDigitsOperationCause;
import com.avaya.collaboration.util.logger.Logger;

import pe.com.mirko.parlana.ivr.service.CallHandlerService;
import pe.com.mirko.parlana.ivr.service.GoogleService;
import pe.com.mirko.parlana.ivr.service.WebServiceCoreClient;

public final class MediaListenerWelcome extends MediaListenerAbstract
{

    private final Call call;
    private final Logger logger = Logger.getLogger(getClass());
    
    public MediaListenerWelcome(final Call call)
    {
        this.call = call;
    }

    @Override
    public void playCompleted(final UUID requestId, final PlayOperationCause cause)
    {
        logger.info("Play completed with the cause " + cause);
		
        if (cause == PlayOperationCause.COMPLETE)
        {
        	logger.info("------ Recording - BEGIN --------");
            
        	// Start recording the caller.
            final MediaOperations mediaOperations = new MediaOperations(this);
            mediaOperations.record(call.getCallingParty());
            return;
        }
        
    }

    @Override
    public void digitsCollected(final UUID requestId, final String digits, final DigitCollectorOperationCause cause)
    {
        logger.info("digitsCollected not expected, requestId = " + requestId);
    }

    @Override
    public void sendDigitsCompleted(final UUID requestId, final SendDigitsOperationCause cause)
    {
        logger.info("sendDigitsCompleted not expected, requestId = " + requestId);
    }

    @Override
    public void recordCompleted(final UUID requestId, final RecordOperationCause cause)
    {
    	logger.info("recordCompleted not expected, requestId = " + requestId + " , cause = " + cause);
    	 
    	if (cause == RecordOperationCause.TERMINATION_KEY_PRESSED)
         {  		 
    		logger.info("-------- Recording - END ----------\n\n");
    		
    		call.setAttribute("PROCESS_STATUS", PROCESS_STATUS_STARTED);
    		//Process 1
        	call.getCallPolicies().setMediaServerInclusion(MediaServerInclusion.AS_NEEDED);
            final MediaListenerBacksound mediaListenerBacksound = new MediaListenerBacksound(call);
            final Participant participant = call.getCallingParty();
            final MediaOperations mediaOperations = new MediaOperations(mediaListenerBacksound);
            mediaOperations.playAudio(participant,WAV_AUDIO_BACKSOUND);
            
    		//Process 2
        	logger.info("=========== NLP - BEGIN ==========\n\n");
        	Map<String, Object> credentials = this.callCredentialsFromWS();
        	String fileUploaded = this.callUploadAudioFile(credentials);
        	Map<String, Object> nlpOutputParams = this.callNLPServicesFromWS(credentials, fileUploaded);        	
 	    	logger.info("============= NLP - END ============\n\n");
 	    	call.setAttribute("PROCESS_STATUS", PROCESS_STATUS_FINISHED);
 	    	
 	    	this.handleCallFlow(nlpOutputParams);
         }
 
    }

	private Map<String, Object> callCredentialsFromWS() {
    	logger.info("+++++++++++++++++++++++++++++++++++");
    	logger.info("|        GET CREDENTIAL           |");
    	logger.info("+++++++++++++++++++++++++++++++++++\n");
		return WebServiceCoreClient.getGenericCredentialsFromWS(KEY);
	}
	
	private String callUploadAudioFile(Map<String, Object> credentials) {
		logger.info("credentials: " + credentials);
    	logger.info("+++++++++++++++++++++++++++++++++++");
    	logger.info("|        UPLOADING FILE           |");
    	logger.info("+++++++++++++++++++++++++++++++++++\n");
		String callingNumber = call.getCallingParty().getHandle();
		String callingPresentedHandle = call.getCallingParty().getPresentedHandle();
		String callingDisplayName = call.getCallingParty().getDisplayName();
		String callingPresentedDisplayName = call.getCallingParty().getPresentedDisplayName();
		
		logger.info("callingNumber: " + callingNumber);
		logger.info("callingPresentedHandle: " + callingPresentedHandle);
		logger.info("callingDisplayName: " + callingDisplayName);
		logger.info("callingPresentedDisplayName: " + callingPresentedDisplayName);
		
		String audioFilenameFromGoogleS3 = GoogleService.googleSpeechRecognitionUpload(callingNumber.replaceAll("\\+",""), credentials);
		logger.info("audioFilenameFromGoogleS3: " + audioFilenameFromGoogleS3);
		return audioFilenameFromGoogleS3;
	}

	private Map<String, Object> callNLPServicesFromWS(Map<String, Object> credentials, String fileUploaded) {
		logger.info("credentials: " + credentials);
    	logger.info("+++++++++++++++++++++++++++++++++++");
    	logger.info("|        NLP PROCESSING           |");
    	logger.info("+++++++++++++++++++++++++++++++++++\n");
    	String callingNumber = call.getCallingParty().getHandle();
    	String calledNumber = call.getCalledParty().getHandle();
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("gToken", (String) credentials.get("gToken"));
		inputParams.put("centralNumber", calledNumber);
		inputParams.put("callingNumber", callingNumber.replaceAll("\\+",""));
		inputParams.put("filenameOnGoogleStorage", fileUploaded);
		return WebServiceCoreClient.processNLPFromWS(inputParams);
	}  

	private void handleCallFlow(Map<String, Object> allData) {
		logger.info(" ---------------- Call Flow Ini --------------");
		boolean dropCall = CallHandlerService.completeCallFlow(allData);
		String finalIntent = (String) allData.get("callEventIntentCode");
		logger.info("finalIntent: " + finalIntent);
		Object callTo = allData.get("callEventFinalExtension");
		
		logger.info("callTo: " + callTo);
		
    	call.getCallPolicies().setMediaServerInclusion(MediaServerInclusion.AS_NEEDED);
        final MediaListenerIntent mediaListenerIntent = new MediaListenerIntent(call, dropCall, callTo);
        final Participant participant = call.getCallingParty();
        
        final MediaOperations mediaOperations = new MediaOperations(mediaListenerIntent);
        String CENTRAL_AUDIO_LANG = "";
		try {
			CENTRAL_AUDIO_LANG = AttributeStore.INSTANCE.getAttributeValue("CENTRAL_AUDIO_LANG");
		} catch (Exception e) {
			e.printStackTrace();
		}
        mediaOperations.playAudio(participant,finalIntent.toLowerCase() + "_" + CENTRAL_AUDIO_LANG.trim() + ".wav");

	}

	
}
