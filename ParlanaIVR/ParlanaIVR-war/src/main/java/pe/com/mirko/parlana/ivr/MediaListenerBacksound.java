package pe.com.mirko.parlana.ivr;

import static pe.com.mirko.parlana.ivr.util.Constants.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

public final class MediaListenerBacksound extends MediaListenerAbstract
{

    private final Call call;
    private final Logger logger = Logger.getLogger(getClass());
    
    public MediaListenerBacksound(final Call call)
    {
        this.call = call;
    }

    @Override
    public void playCompleted(final UUID requestId, final PlayOperationCause cause)
    {
        logger.info("Play completed with the cause " + cause);
		
//        if (cause == PlayOperationCause.COMPLETE)
//        {
//            String processStatus = (String) call.getAttribute("PROCESS_STATUS");
//            while (!PROCESS_STATUS_FINISHED.equals(processStatus))
//            { 
//            	//Continue playing background
//    	    	call.getCallPolicies().setMediaServerInclusion(MediaServerInclusion.AS_NEEDED);
//    	        final MediaListenerBacksound mediaListenerBacksound = new MediaListenerBacksound(call);
//    	        final Participant participant = call.getCallingParty();
//    	        final MediaOperations mediaOperations = new MediaOperations(mediaListenerBacksound);
//    	        mediaOperations.playAudio(participant,WAV_AUDIO_BACKSOUND);
//            }
//        }
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
    	 
    }
	
}
