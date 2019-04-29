package com.parlana.core.logic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parlana.core.logic.service.CallEventService;
import com.parlana.core.model.CallEvent;
import com.parlana.core.model.dao.mapper.CallEventMapper;

@Service("callEventService")
public class CallEventServiceImpl implements CallEventService {

	@Autowired
	private CallEventMapper callEventMapper;
	
	public int registerCallEvent(CallEvent record) {
		return callEventMapper.insertSelective(record);
	}

	public List<CallEvent> getCallEventByCentralNumber(String callEventTo) {
		return callEventMapper.selectByCallEventTo(callEventTo);
	}

	public List<CallEvent> getCallEventByCentralNumberAndCode(CallEvent record) {
		return callEventMapper.selectByCallEventToAndCode(record);
	}

}
