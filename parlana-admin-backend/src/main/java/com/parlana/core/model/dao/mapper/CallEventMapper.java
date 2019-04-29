package com.parlana.core.model.dao.mapper;

import java.util.List;

import com.parlana.core.model.CallEvent;

public interface CallEventMapper {
    int deleteByPrimaryKey(Long idCallEvent);

    int insert(CallEvent record);

    int insertSelective(CallEvent record);

    CallEvent selectByPrimaryKey(Long idCallEvent);

	List<CallEvent> selectByCallEventToAndCode(CallEvent record);
	
    List<CallEvent> selectByCallEventTo(String callEventTo);
    
    int updateByPrimaryKeySelective(CallEvent record);

    int updateByPrimaryKey(CallEvent record);

}