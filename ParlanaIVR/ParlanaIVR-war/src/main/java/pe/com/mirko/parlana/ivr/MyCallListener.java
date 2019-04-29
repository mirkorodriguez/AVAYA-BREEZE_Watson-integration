package pe.com.mirko.parlana.ivr;

import static pe.com.mirko.parlana.ivr.util.Constants.WAV_AUDIO_WELCOME;

import com.avaya.collaboration.call.Call;
import com.avaya.collaboration.call.CallListenerAbstract;
import com.avaya.collaboration.call.Participant;
import com.avaya.collaboration.call.TheCallListener;
import com.avaya.collaboration.call.media.MediaServerInclusion;
import com.avaya.collaboration.util.logger.Logger;

@TheCallListener
public class MyCallListener extends CallListenerAbstract 
{
    private static Logger logger = Logger.getLogger(MyCallListener.class);
   
	public MyCallListener() 
	{
	}
	
	@Override
	public final void callIntercepted(final Call call) 
	{
        logger.info("Entered callIntercepted.");
        this.playWelcomeAndRecordCalling(call);
        //call.allow();
	}
	
	public void playWelcomeAndRecordCalling(final Call call)
    {
    	call.getCallPolicies().setMediaServerInclusion(MediaServerInclusion.AS_NEEDED);
        final MediaListenerWelcome mediaListenerWelcome = new MediaListenerWelcome(call);
        final Participant participant = call.getCallingParty();
        
        final MediaOperations mediaOperations = new MediaOperations(mediaListenerWelcome);
        String CENTRAL_AUDIO_LANG = "";
		try {
			CENTRAL_AUDIO_LANG = AttributeStore.INSTANCE.getAttributeValue("CENTRAL_AUDIO_LANG");
		} catch (Exception e) {
			e.printStackTrace();
		}
        mediaOperations.playAudio(participant,WAV_AUDIO_WELCOME + "_" + CENTRAL_AUDIO_LANG + ".wav");
    }
}
