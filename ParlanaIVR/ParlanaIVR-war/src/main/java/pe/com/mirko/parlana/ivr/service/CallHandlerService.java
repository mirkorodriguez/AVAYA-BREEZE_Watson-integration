package pe.com.mirko.parlana.ivr.service;

import static pe.com.mirko.parlana.ivr.util.Constants.INTENT_DEFAULT;

import java.util.Map;

import com.avaya.collaboration.util.logger.Logger;

import pe.com.mirko.parlana.ivr.email.EmailSender;
import pe.com.mirko.parlana.ivr.sms.SmsSender;
import pe.com.mirko.parlana.ivr.util.RandomUtil;

public class CallHandlerService {

	private static Logger logger = Logger.getLogger(CallHandlerService.class);

	public static boolean completeCallFlow(Map<String, Object> allData) {
    	
//		logger.info("completeCallFlow Ini");
		
		boolean dropCall = true;
		String currentDatetime = (String) allData.get("callEventDatetime");
		String intentCode = (String) allData.get("callEventIntentCode");
		String intentConfidence = (String) allData.get("callEventIntentConfidence");
		String calledNumber = (String) allData.get("callEventFrom");
		String callingNumber = (String) allData.get("callEventTo");
		String centralNumberPstn = (String) allData.get("centralNumberPstn");
		String finalSpeechToText = (String) allData.get("callEventText");
		String sentimentLabel = (String) allData.get("callEventFinalSentimentLabel");
		String sentimentScore = (String) allData.get("callEventFinalSentimentScore");
		String extensionPersonEmail = (allData.get("extensionPersonEmail") != null) ? (String) allData.get("extensionPersonEmail") : "";
		String extensionPersonPhone = (allData.get("extensionPersonPhone") != null) ? (String) allData.get("extensionPersonPhone") : "";
		
		String smsTo = "";
		String smsBody = "";

		// Call flow
		if (INTENT_DEFAULT.equals(intentCode)){
			//Send SMS to Central Number PSTN
			smsTo = centralNumberPstn;
			smsBody = "Llamada realizada el " + currentDatetime + ", de " + callingNumber + " a " + calledNumber +
							 ", Ticket: TK" + RandomUtil.getTicketNumber(1000, 2000);
		}else{
			dropCall = false;
			//Send SMS
			smsTo = extensionPersonPhone;
			smsBody = "Llamada realizada el " + currentDatetime + ", de " + callingNumber + " a " + calledNumber
								+ ", con el INTENT: " + intentCode + " (" + intentConfidence + ")";
			//Send Mail to Extension
			String emailTo = extensionPersonEmail;
			String emailBody = "Llamada realizada el " + currentDatetime + ", de: " + callingNumber + " a "
								+ calledNumber + ", con la siguiente transcripción: \n\n\"" + finalSpeechToText + "\""
								+ ".\n\nINTENT: " + intentCode + " (" + intentConfidence + "), " + ".\n\nSentimiento: "
								+ sentimentLabel + " [" + sentimentScore + "]";
			String emailSubject = "[IVR] Notificación de Llamada de cliente";
			
			String msgBody = "Llamada realizada el " + currentDatetime + ", de: " + callingNumber + " a " + calledNumber + ", con la siguiente transcripción:";
			
			logger.info("Sending EMAIL to: " + emailTo);
			
			EmailSender.sendCallAlertEmail(emailTo, emailBody, emailSubject);
			
		}

		//Sending SMS
		if (smsTo != null && smsTo != "" && smsTo.trim().length() != 0){
			logger.info("Sending SMS To: " + smsTo);
			SmsSender.sendCallAlertSms(smsTo, smsBody);
		}
		
		
//		logger.info("completeCallFlow End: " + dropCall);
		return dropCall;

	}
}
