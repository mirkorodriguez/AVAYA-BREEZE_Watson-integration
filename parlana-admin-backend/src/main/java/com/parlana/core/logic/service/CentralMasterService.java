package com.parlana.core.logic.service;

import com.parlana.core.model.CentralMaster;

public interface CentralMasterService {

	CentralMaster getCentralMasterByCentralNumber(String centralNumber);
	
	CentralMaster getCentralMasterByCentralNumberAndCode(CentralMaster record); 
	
	int registerCentralMaster(CentralMaster record);
	
	int updateCentralMaster(CentralMaster record);
}
