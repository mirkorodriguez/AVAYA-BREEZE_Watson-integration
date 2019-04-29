
package pe.com.mirko.parlana.ivr;

import static pe.com.mirko.parlana.ivr.util.Constants.MAX_RECORD_TIME;
import static pe.com.mirko.parlana.ivr.util.Constants.TERMINATION_KEY;

import java.util.UUID;

import com.avaya.collaboration.businessdata.api.NoAttributeFoundException;
import com.avaya.collaboration.businessdata.api.ServiceNotFoundException;
import com.avaya.collaboration.call.Participant;
import com.avaya.collaboration.call.media.MediaFactory;
import com.avaya.collaboration.call.media.MediaListener;
import com.avaya.collaboration.call.media.MediaService;
import com.avaya.collaboration.call.media.PlayItem;
import com.avaya.collaboration.call.media.RecordItem;
import com.avaya.collaboration.util.logger.Logger;
import com.avaya.zephyr.platform.dal.api.ServiceUtil;

public class MediaOperations
{
    private final MediaListener mediaListener;
    private final MediaService mediaService;
    private static final Logger logger = Logger.getLogger(MediaOperations.class);

    public MediaOperations(final MediaListener mediaListener)
    {
        this.mediaListener = mediaListener;
        this.mediaService = MediaFactory.createMediaService();
    }

    public void playAudio(final Participant participant,final String filename)
    {
    	
        try
        {
            final PlayItem playItem = MediaFactory.createPlayItem();
            final String source = formWelcomePlayUrl(filename);
            playItem.setSource(source)
                    .setInterruptible(false)
                    .setIterateCount(1);
            final UUID requestId = mediaService.play(participant, playItem, mediaListener);
            logger.info("play source = " + source + ", requestId = " + requestId);
        }
        catch (final Exception exception)
        {
            logger.error("play caught exception: ", exception);
            throw new IllegalStateException(exception);
        }
    }

    public void record(final Participant participant)
    {
    	final String handle = participant.getHandle();
        final String storageUrl = formRecordingStoreUrl(handle.replaceAll("\\+",""));
        final RecordItem recordItem = MediaFactory.createRecordItem();
        recordItem.setMaxDuration(MAX_RECORD_TIME)
                .setTerminationKey(TERMINATION_KEY)
                .setFileUri(storageUrl);
        mediaService.record(participant, recordItem, mediaListener);
        logger.info("record storage URL = " + storageUrl);
    }
    
    public String formWelcomePlayUrl(final String filename)
    {
        final TrafficInterfaceAddressRetrieverImpl addressRetriever = new TrafficInterfaceAddressRetrieverImpl();
        final String trafficInterfaceAddress = addressRetriever.getTrafficInterfaceAddress();
        final String myServiceName = ServiceUtil.getServiceDescriptor().getName();
        final StringBuilder sb = new StringBuilder();
        sb.append("http://")
                .append(trafficInterfaceAddress)
                .append("/services/")
                .append(myServiceName)
                .append("/")
                .append(filename);
        return sb.toString();
    }
    
//    private String formRecordingStoreUrl(String handle)
//    {
//        final TrafficInterfaceAddressRetrieverImpl addressRetriever = new TrafficInterfaceAddressRetrieverImpl();
////        final String trafficInterfaceAddress = addressRetriever.getTrafficInterfaceAddress();
//        final String myServiceName = ServiceUtil.getServiceDescriptor().getName();
//        final StringBuilder sb = new StringBuilder();
//        String endPointBase = "";
//		try {
//			endPointBase = AttributeStore.INSTANCE.getAttributeValue("WS_BASE_BACKEND_ENDPOINT");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        sb.append(endPointBase)
//        		.append("/parlana-admin-backend")
//                .append("/StoreRecordingServlet")
//                .append("/recording")
//                .append(myServiceName)
//                .append("_")
//                .append(handle)
//                .append(".wav");
//        return sb.toString();
//    }

    private String formRecordingStoreUrl(String handle)
    {
        final TrafficInterfaceAddressRetrieverImpl addressRetriever = new TrafficInterfaceAddressRetrieverImpl();
        final String trafficInterfaceAddress = addressRetriever.getTrafficInterfaceAddress();
        final String myServiceName = ServiceUtil.getServiceDescriptor().getName();
        final StringBuilder sb = new StringBuilder();
        sb.append("http://")
                .append(trafficInterfaceAddress)
                .append("/services/")
                .append(myServiceName)
                .append("/StoreRecordingServlet/")
                .append("recording")
                .append(myServiceName)
                .append("_")
                .append(handle)
                .append(".wav");
        return sb.toString();
    }

}
