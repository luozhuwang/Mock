package com.mock.service;

import com.mock.entity.ICategoryEntity;
import com.mock.entity.ResponeBase;

public interface ICategoryService {
	/**
     * 	新增模块管理
     * @param ICategoryEntity
     * @return 结果
     */
	ResponeBase insertCategory(ICategoryEntity iCategoryEntity);
	
	/**
     * 	修改模块管理
     * @param ICategoryEntity
     * @return 结果
     */
	ResponeBase updateCategory(ICategoryEntity iCategoryEntity);
	/**
     * 查询模块管理
     * @param id
     * @return	模块管理集合
     */
	ResponeBase selectCategoryById(int id);
	/**
     * 查询模块管理
     * @param ICategoryEntity
     * @return	模块管理集合
     */
	ResponeBase selectCategory(ICategoryEntity iCategoryEntity);
	
	

}
