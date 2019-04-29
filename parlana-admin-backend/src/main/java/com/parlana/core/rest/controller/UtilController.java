package com.parlana.core.rest.controller;

import static com.parlana.core.util.Constants.*;

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
import com.parlana.core.logic.service.UtilService;
import com.parlana.core.model.CentralMaster;
import com.parlana.core.model.Extension;
import com.parlana.core.rest.request.dto.CentralMasterRequestDTO;
import com.parlana.core.rest.request.dto.UtilRequestDTO;
import com.parlana.core.rest.request.dto.UtilResponseDTO;
import com.parlana.core.rest.response.dto.CentralMasterResponseDTO;
import com.parlana.core.rest.response.dto.ExtensionResponseDTO;
import com.parlana.core.util.exception.BusinessException;
import com.parlana.core.util.exception.CentralMasterNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/s/util")
public class UtilController {

	private static final Logger logger = LogManager.getLogger(UtilController.class);
		
	@Autowired
	private UtilService utilservice;
	
	@RequestMapping(value = "/getObj", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UtilResponseDTO getGenericCredentials(
			@RequestBody UtilRequestDTO utilRequestDTO) throws BusinessException {
		
		String key = utilRequestDTO.getKey(); 
		logger.debug("GET Token for: " + utilRequestDTO.getIp() + " credential:  " + key);

		UtilResponseDTO utilResponseDTO = new UtilResponseDTO();
		if(KEY.equals(key)) {
			utilResponseDTO.setgToken(utilservice.generateGoogleToken());
			utilResponseDTO.setgStorageUri(GOOGLE_STORAGE_URI);
		}else {
			throw new BusinessException();
		}

		return utilResponseDTO;
	}

}
