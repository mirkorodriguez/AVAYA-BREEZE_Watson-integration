package com.parlana.core.logic.service;

import com.parlana.core.model.Extension;

public interface ExtensionService {

	int registerExtension(Extension record);

	int updateExtension(Extension record);
}
