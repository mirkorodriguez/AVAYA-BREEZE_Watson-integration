package pe.com.mirko.parlana.ivr.sms;

import com.avaya.collaboration.bus.CollaborationBusException;
import com.avaya.collaboration.sms.SmsFactory;
import com.avaya.collaboration.sms.SmsRequest;

public class SmsSender {

	public static void sendCallAlertSms(String smsTo, String smsBody) {
		final SmsRequest smsRequest = SmsFactory.createSmsRequest(smsTo, smsBody);
		try {
			smsRequest.send();
		} catch (final CollaborationBusException e) {
			e.printStackTrace();
		}

	}
}
