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
import com.parlana.core.logic.service.CentralMasterService;
import com.parlana.core.model.CentralMaster;
import com.parlana.core.model.Extension;
import com.parlana.core.rest.request.dto.CentralMasterRequestDTO;
import com.parlana.core.rest.response.dto.CentralMasterResponseDTO;
import com.parlana.core.rest.response.dto.ExtensionResponseDTO;
import com.parlana.core.util.exception.BusinessException;
import com.parlana.core.util.exception.CentralMasterNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/s/centralMaster")
public class CentralMasterController {

	private static final Logger logger = LogManager.getLogger(CentralMasterController.class);
	
	@Autowired
	private CentralMasterService centralMasterService;
	
	@RequestMapping(value = "/getObj", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CentralMasterResponseDTO getCentralMasterByCentralNumber(
			@RequestBody CentralMasterRequestDTO centralMasterRequestDTO) throws BusinessException {
		
		String centralNumber = centralMasterRequestDTO.getCentralNumber();
		String centralCode = centralMasterRequestDTO.getCentralCode();
		String centralPass = centralMasterRequestDTO.getCentralAccessPwd();
		logger.debug("Central Number: " + centralNumber);
		logger.debug("Central Code: " + centralCode);
		logger.debug("Central Pass: " + centralPass);

		Gson gson= new Gson();
		String tmpObj = gson.toJson(centralMasterRequestDTO);
		CentralMaster centralMasterInput = gson.fromJson(tmpObj,CentralMaster.class);
		CentralMaster centralMasterOutput = centralMasterService.getCentralMasterByCentralNumberAndCode(centralMasterInput);

		logger.debug("centralMasterOutput: " + centralMasterOutput);
		
		CentralMasterResponseDTO centralMasterResponseDTO = new CentralMasterResponseDTO();
		
		if (centralMasterOutput != null && centralMasterOutput.getCentralAccessPwd().equals(centralPass)) {
			centralMasterResponseDTO.setCentralNumber(centralMasterOutput.getCentralNumber());
			centralMasterResponseDTO.setCentralCode(centralMasterOutput.getCentralCode());
//			centralMasterResponseDTO.setCentralAccessPwd(centralMasterOutput.getCentralAccessPwd());
			centralMasterResponseDTO.setCentralLang(centralMasterOutput.getCentralLang());
			centralMasterResponseDTO.setCentralNumberPstn(centralMasterOutput.getCentralNumberPstn());
			centralMasterResponseDTO.setCentralSmgrIp(centralMasterOutput.getCentralSmgrIp());
			centralMasterResponseDTO.setIdCentralNumber(centralMasterOutput.getIdCentralNumber());
			
			List<Extension> extensionList = centralMasterOutput.getExtensionList();
			
			List<ExtensionResponseDTO> extensionResponseDTOList = new ArrayList<ExtensionResponseDTO>();
			
			for (Extension extension : extensionList) {
				gson = new Gson();
				tmpObj = gson.toJson(extension);
				ExtensionResponseDTO extensionResponseDTO = gson.fromJson(tmpObj,ExtensionResponseDTO.class);
				extensionResponseDTOList.add(extensionResponseDTO);
			}
			centralMasterResponseDTO.setExtensionResponseDTOList(extensionResponseDTOList);
			
			//Adding token
			//centralMasterResponseDTO.setToken(utilservice.generateGoogleToken());
		}else{
			throw new CentralMasterNotFoundException();
		}
		return centralMasterResponseDTO;
	}
	
	
	@RequestMapping(value = "/putObj", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int registerCentralMaster(
			@RequestBody CentralMasterRequestDTO centralMasterRequestDTO) throws BusinessException {
		
		logger.debug("Registering Central Number: " + centralMasterRequestDTO.getCentralNumber() + " and Central Code: " + centralMasterRequestDTO.getCentralCode());
		
		Gson gson= new Gson();
		String tmpObj = gson.toJson(centralMasterRequestDTO);
		CentralMaster centralMaster = gson.fromJson(tmpObj,CentralMaster.class);

		int insertResult = 0;
		
		insertResult = centralMasterService.registerCentralMaster(centralMaster);
		
		logger.info("insertResult: " + insertResult);
		
		if (insertResult == 0) {
			throw new BusinessException();
		}

		return insertResult;
	}

	@RequestMapping(value = "/updateObj", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int updateCentralMaster(
			@RequestBody CentralMasterRequestDTO centralMasterRequestDTO) throws BusinessException {
		
		logger.debug("Updating Central Id: " + centralMasterRequestDTO.getIdCentralNumber());
		logger.debug("Updating Central Number: " + centralMasterRequestDTO.getCentralNumber());
		
		Gson gson= new Gson();
		String tmpObj = gson.toJson(centralMasterRequestDTO);
		CentralMaster centralMaster = gson.fromJson(tmpObj,CentralMaster.class);

		int insertResult = 0;
		
		//Avoid pass updating
		centralMaster.setCentralAccessPwd(null);
		insertResult = centralMasterService.updateCentralMaster(centralMaster);
		
		logger.info("insertResult: " + insertResult);
		
		if (insertResult == 0) {
			throw new BusinessException();
		}

		return insertResult;
	}
}
