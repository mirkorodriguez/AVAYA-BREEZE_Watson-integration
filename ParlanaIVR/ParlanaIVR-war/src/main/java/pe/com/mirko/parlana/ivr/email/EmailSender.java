package pe.com.mirko.parlana.ivr.email;

import com.avaya.collaboration.bus.CollaborationBusException;
import com.avaya.collaboration.email.EmailFactory;
import com.avaya.collaboration.email.EmailRequest;

public class EmailSender {
    
	public static void sendCallAlertEmail(String emailTo, String emailBody, String emailSubject)
    {
		if(emailTo == null || emailTo == "" || emailTo.trim().length() == 0) {
			return;
		}
		
        final EmailRequest emailRequest = EmailFactory.createEmailRequest();
        emailRequest.getTo().add(emailTo);
        emailRequest.setSubject(emailSubject);
        emailRequest.setTextBody(emailBody);
        
        try {
			emailRequest.send();
		} catch (CollaborationBusException e) {
			e.printStackTrace();
		}        
    }

}
