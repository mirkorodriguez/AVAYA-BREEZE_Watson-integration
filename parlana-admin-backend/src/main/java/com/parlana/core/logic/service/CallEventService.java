package com.parlana.core.logic.service;

import java.util.List;

import com.parlana.core.model.CallEvent;

public interface CallEventService {

	int registerCallEvent(CallEvent record);

	List<CallEvent> getCallEventByCentralNumber(String callEventTo);

	List<CallEvent> getCallEventByCentralNumberAndCode(CallEvent callEvent);
}
