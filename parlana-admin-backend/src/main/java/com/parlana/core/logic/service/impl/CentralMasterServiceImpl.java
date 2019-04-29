package com.parlana.core.logic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parlana.core.logic.service.CentralMasterService;
import com.parlana.core.model.CentralMaster;
import com.parlana.core.model.dao.mapper.CentralMasterMapper;

@Service("centralMasterService")
public class CentralMasterServiceImpl implements CentralMasterService{

	@Autowired
	private CentralMasterMapper centralMasterMapper;

	public CentralMaster getCentralMasterByCentralNumber(String centralNumber) {
		return centralMasterMapper.selectByCentralNumber(centralNumber);
	}

	public CentralMaster getCentralMasterByCentralNumberAndCode(CentralMaster record) {
		return centralMasterMapper.selectByCentralNumberAndCode(record);
	}

	public int registerCentralMaster(CentralMaster record) {
		return centralMasterMapper.insertSelective(record);
	}

	public int updateCentralMaster(CentralMaster record) {
		return centralMasterMapper.updateByPrimaryKeySelective(record);
	}
	
}
