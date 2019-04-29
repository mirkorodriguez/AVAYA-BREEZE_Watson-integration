package com.parlana.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getCurrentDatetime() {
	
		String currentDatetime = new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(new Date());
		
		return currentDatetime;
	}
	
}
