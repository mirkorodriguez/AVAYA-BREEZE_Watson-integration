package com.parlana.core.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.parlana.core.logic.service.EmailHandlerService;
import com.parlana.core.rest.request.dto.EmailRequestDTO;
import com.parlana.core.util.exception.BusinessException;

@CrossOrigin
@RestController
@RequestMapping("/s/email")
public class EmailController {
	
	private static final Logger logger = LogManager.getLogger(EmailController.class);
	
	@Autowired
	private EmailHandlerService emailHandlerService;

	@RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void registerCentralMaster(
			@RequestBody EmailRequestDTO emailRequestDTO) throws BusinessException {
		
		logger.debug("Sending email to: " + emailRequestDTO.getTo());
		
		Gson gson= new Gson();
		String tmpObj = gson.toJson(emailRequestDTO);
		
		logger.info("Received: " + tmpObj);
		
		Map<String,Object> emailData = new HashMap<String,Object>();
		
		emailHandlerService.sendEmail(emailData);		
	}
}
