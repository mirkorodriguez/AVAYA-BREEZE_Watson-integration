package com.parlana.core.logic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parlana.core.logic.service.ExtensionService;
import com.parlana.core.model.Extension;
import com.parlana.core.model.dao.mapper.ExtensionMapper;

@Service("extensionService")
public class ExtensionServiceImpl implements ExtensionService {

	@Autowired
	private ExtensionMapper extensionMapper;
	
	public int registerExtension(Extension record) {
		return extensionMapper.insertSelective(record);
	}

	public int updateExtension(Extension record) {
		return extensionMapper.updateByPrimaryKeySelective(record);
	}

}
