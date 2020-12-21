package com.mock.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mock.dao.ICategoryDao;
import com.mock.entity.ICategoryEntity;
import com.mock.entity.ResponeBase;
import com.mock.service.ICategoryService;

@Service
public class ICategoryServiceImpl implements ICategoryService{
	private Logger log =LoggerFactory.getLogger(ICategoryServiceImpl.class);
	
	@Autowired
	private ICategoryDao iCategoryDao;
	
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase insertCategory(ICategoryEntity iCategoryEntity) {
		int count=-1;
		count=iCategoryDao.insertCategory(iCategoryEntity);
		if(count>0) {
			log.info("添加【模块】成功");
			List<ICategoryEntity> iCategoryEntitys=iCategoryDao.selectCategory(null);
			return ResponeBase.successResult(iCategoryEntitys);
		}else {	
			log.info("添加【模块】失败");
			return ResponeBase.failResult("添加【模块】失败", null);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase updateCategory(ICategoryEntity iCategoryEntity) {
		int count=-1;
		count=iCategoryDao.updateCategory(iCategoryEntity);
		if(count>0) {
			return ResponeBase.resultNull("修改【模块】成功");
		}else {			
			log.info("修改【模块】失败");
			return ResponeBase.failResult("修改【模块】失败", null);
		}
	}

	@Override
	public ResponeBase selectCategory(ICategoryEntity iCategoryEntity) {
		PageHelper.startPage(iCategoryEntity.getPageNum(), iCategoryEntity.getPageSize());
		List<ICategoryEntity> iCategoryEntitys=iCategoryDao.selectCategory(iCategoryEntity);
		if(iCategoryEntitys.isEmpty()) {
			log.info("查询【模块】为空");
			return  ResponeBase.resultNull("查询【模块】为空");
		}
		PageInfo<ICategoryEntity> pageinfo=new PageInfo<ICategoryEntity>(iCategoryEntitys);
		return ResponeBase.successResult(pageinfo);
	}

	@Override
	public ResponeBase selectCategoryById(int id) {
		ICategoryEntity iCategoryEntity=iCategoryDao.selectCategoryById(id);

		if(iCategoryEntity!=null) {
			log.info("通过ID查询【模块】成功");
			return ResponeBase.successResult(iCategoryEntity);
		}else {			
			log.info("通过ID查询【模块】失败");
			return ResponeBase.failResult("通过ID查询【模块】失败", null);
		}
	}

}
