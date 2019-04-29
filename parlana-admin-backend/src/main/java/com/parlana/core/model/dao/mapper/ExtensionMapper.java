package com.parlana.core.model.dao.mapper;

import com.parlana.core.model.Extension;

public interface ExtensionMapper {
    int deleteByPrimaryKey(Long idExtension);

    int insert(Extension record);

    int insertSelective(Extension record);

    Extension selectByPrimaryKey(Long idExtension);

    int updateByPrimaryKeySelective(Extension record);

    int updateByPrimaryKey(Extension record);
}