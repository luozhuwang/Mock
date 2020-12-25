package com.mock.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mock.dao.IBasicDao;
import com.mock.entity.BasicDataEntity;
import com.mock.entity.ResponeBase;
import com.mock.service.IBasicService;

@Service
public class IBasicServiceImpl implements IBasicService{
private Logger logger=LoggerFactory.getLogger(IBasicServiceImpl.class);
	
	@Autowired
	private IBasicDao iBasicDao;

	@Override
	public ResponeBase selectBasicData(BasicDataEntity basicDataEntity) {
		List<BasicDataEntity> BasicDatas=iBasicDao.selectBasicData(basicDataEntity);
		if(BasicDatas.isEmpty()) {
			logger.info("查询【基础数据】为空");
			return  ResponeBase.resultNull("查询【基础数据】为空");
		}
		return ResponeBase.successResult(BasicDatas);
	}
	
}
