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
import com.parlana.core.logic.service.CallEventService;
import com.parlana.core.model.CallEvent;
import com.parlana.core.model.Extension;
import com.parlana.core.rest.request.dto.CallEventRequestDTO;
import com.parlana.core.rest.response.dto.CallEventResponseDTO;
import com.parlana.core.rest.response.dto.CentralMasterResponseDTO;
import com.parlana.core.rest.response.dto.ExtensionResponseDTO;
import com.parlana.core.util.exception.BusinessException;
import com.parlana.core.util.exception.CentralMasterNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/s/callEvent")
public class CallEventController {

	private static final Logger logger = LogManager.getLogger(CallEventController.class);
	
	@Autowired
	private CallEventService callEventService;

	@RequestMapping(value = "/getObjList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<CallEventResponseDTO> getCentralMasterByCentralNumber(
			@RequestBody CallEventRequestDTO callEventRequestDTO) throws BusinessException {
		
		String callEventTo = callEventRequestDTO.getCallEventTo();
		String callEventToCode = callEventRequestDTO.getCallEventToCode();
		logger.debug("Retrieving Call Event To: " + callEventTo + " and Code: " + callEventToCode);

		CallEvent callEventInput = new CallEvent();
		callEventInput.setCallEventTo(callEventTo);
		callEventInput.setCallEventToCode(callEventToCode);
		
		List<CallEvent> callEventList = callEventService.getCallEventByCentralNumberAndCode(callEventInput);
		List<CallEventResponseDTO> CallEventResponseDTOList = new ArrayList<CallEventResponseDTO>();
		
		if (callEventList != null && callEventList.size() != 0) {
			
			for (CallEvent callEvent : callEventList) {
				Gson gson = new Gson();
				String tmpObj = gson.toJson(callEvent);
				CallEventResponseDTO callEventResponseDTO = gson.fromJson(tmpObj,CallEventResponseDTO.class);
				CallEventResponseDTOList.add(callEventResponseDTO);
			}
			
		}else{
			throw new CentralMasterNotFoundException();
		}
		return CallEventResponseDTOList;
	}
	
	@RequestMapping(value = "/putObj", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int registerCallEvent(
			@RequestBody CallEventRequestDTO callEventRequestDTO) throws BusinessException {
		
		logger.debug("Registering call event FROM: " + callEventRequestDTO.getCallEventFrom());
		logger.debug("Registering call event TO: " + callEventRequestDTO.getCallEventTo());
		
		Gson gson= new Gson();
		String tmpObj = gson.toJson(callEventRequestDTO);
		CallEvent callEvent = gson.fromJson(tmpObj,CallEvent.class);

		int insertResult = 0;
		
		insertResult = callEventService.registerCallEvent(callEvent);
		
		logger.info("insertResult: " + insertResult);
		
		if (insertResult == 0) {
			throw new BusinessException();
		}

		return insertResult;
	}
}
