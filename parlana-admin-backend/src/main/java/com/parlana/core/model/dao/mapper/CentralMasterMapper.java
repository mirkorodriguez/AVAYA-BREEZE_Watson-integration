package com.parlana.core.model.dao.mapper;

import com.parlana.core.model.CentralMaster;

public interface CentralMasterMapper {
	
    int deleteByPrimaryKey(Long idCentralNumber);

    int insert(CentralMaster record);

    int insertSelective(CentralMaster record);

    CentralMaster selectByPrimaryKey(Long idCentralNumber);
    
    CentralMaster selectByCentralNumber(String CentralNumber);

	CentralMaster selectByCentralNumberAndCode(CentralMaster record);
	
    int updateByPrimaryKeySelective(CentralMaster record);

    int updateByPrimaryKey(CentralMaster record);

}