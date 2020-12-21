package com.mock.dao;

import java.util.List;

import com.mock.entity.ICategoryEntity;
import com.mock.entity.IUrlEntity;

public interface ICategoryDao {
	/**
     * 	新增模块管理
     * @param ICategoryEntity
     * @return 结果
     */
    Integer insertCategory(ICategoryEntity iCategoryEntity);
    /**
     * 	修改模块管理
     * @param ICategoryEntity
     * @return 结果
     */
    Integer updateCategory(ICategoryEntity iCategoryEntity);
    /**
     * 查询模块管理
     * @param id
     * @return	模块管理集合
     */
    ICategoryEntity selectCategoryById(int id);
    /**
     * 查询模块管理
     * @param ICategoryEntity
     * @return	模块管理集合
     */
    List<ICategoryEntity> selectCategory(ICategoryEntity iCategoryEntity);
}
