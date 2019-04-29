package com.parlana.core.rest.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.parlana.core.logic.service.ExtensionService;
import com.parlana.core.model.CentralMaster;
import com.parlana.core.model.Extension;
import com.parlana.core.rest.request.dto.CentralMasterRequestDTO;
import com.parlana.core.rest.request.dto.ExtensionRequestDTO;
import com.parlana.core.rest.response.dto.CentralMasterResponseDTO;
import com.parlana.core.rest.response.dto.ExtensionResponseDTO;
import com.parlana.core.util.exception.BusinessException;
import com.parlana.core.util.exception.CentralMasterNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/s/extension")
public class ExtensionController {
	
	private static final Logger logger = LogManager.getLogger(ExtensionController.class);
	
	@Autowired
	private ExtensionService extensionService;
	
	@RequestMapping(value = "/putObj", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int registerExtension(
			@RequestBody ExtensionRequestDTO extensionRequestDTO) throws BusinessException {
		
		logger.debug("Registering extension: " + extensionRequestDTO.getExtensionNumber());
		
		Gson gson= new Gson();
		String tmpObj = gson.toJson(extensionRequestDTO);
		Extension extension = gson.fromJson(tmpObj,Extension.class);

		int insertResult = 0;
		
		insertResult = extensionService.registerExtension(extension);
		
		logger.info("insertResult: " + insertResult);
		
		if (insertResult == 0) {
			throw new BusinessException();
		}

		return insertResult;
	}

	@RequestMapping(value = "/updateObj", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int updateExtension(
			@RequestBody ExtensionRequestDTO extensionRequestDTO) throws BusinessException {
		
		logger.debug("Updating extension Id: " + extensionRequestDTO.getIdExtension());
		logger.debug("Updating extension Number: " + extensionRequestDTO.getExtensionNumber());
		
		Gson gson= new Gson();
		String tmpObj = gson.toJson(extensionRequestDTO);
		Extension extension = gson.fromJson(tmpObj,Extension.class);

		int insertResult = 0;
		
		insertResult = extensionService.updateExtension(extension);
		
		logger.info("insertResult: " + insertResult);
		
		if (insertResult == 0) {
			throw new BusinessException();
		}

		return insertResult;
	}
}
