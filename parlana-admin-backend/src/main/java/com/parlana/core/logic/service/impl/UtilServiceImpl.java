package com.parlana.core.logic.service.impl;

import static com.parlana.core.util.Constants.*;

import org.springframework.stereotype.Service;
import com.parlana.core.logic.service.UtilService;
import com.parlana.core.util.SystemUtil;

@Service("utilService")
public class UtilServiceImpl implements UtilService {

	public String generateGoogleToken() {
		
		//SystemUtil.setEnv(GOOGLE_APPLICATION_CREDENTIALS, GCLOD_TOKEN_JSON);

		//Execute gcloud 
		String result = "";
		try {
			result = SystemUtil.executeShell();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
