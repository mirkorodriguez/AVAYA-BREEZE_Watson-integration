package com.parlana.core.logic.service;

import java.util.Map;

public interface EmailHandlerService {

	String sendEmail(Map<String,Object> params);
}
