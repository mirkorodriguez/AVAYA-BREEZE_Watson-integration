package pe.com.mirko.parlana.ivr;

import java.util.UUID;

import com.avaya.collaboration.call.Call;
import com.avaya.collaboration.call.media.DigitCollectorOperationCause;
import com.avaya.collaboration.call.media.MediaListenerAbstract;
import com.avaya.collaboration.call.media.PlayOperationCause;
import com.avaya.collaboration.call.media.RecordOperationCause;
import com.avaya.collaboration.call.media.SendDigitsOperationCause;
import com.avaya.collaboration.util.logger.Logger;

public final class MediaListenerIntent extends MediaListenerAbstract
{
    private final Call call;
    private final boolean dropAfterPlayComplete;
    private final Object callTo;
    private final Logger logger = Logger.getLogger(getClass());
    
    public MediaListenerIntent(final Call call, final boolean dropAfterPlayComplete, final Object callTo)
    {
    	logger.info("dropAfterPlayComplete: " + dropAfterPlayComplete + " callTo " + callTo);
        this.call = call;
        this.dropAfterPlayComplete = dropAfterPlayComplete;
        this.callTo = callTo;
    }

    @Override
    public void playCompleted(final UUID requestId, final PlayOperationCause cause)
    {
        logger.info("Play INTENT completed with the cause " + cause);
		
        if (cause == PlayOperationCause.COMPLETE)
        {
    		if(!dropAfterPlayComplete) {
			String callingTo = (String) callTo;
			logger.info("Calling to: " + callingTo);
			call.divertTo(callingTo);
			}else {
				logger.info("Dropping Call");
				call.drop();
			}
			logger.info(" ---------------- Call Flow Fin --------------");
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
    }
	
}
