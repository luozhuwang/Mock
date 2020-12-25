package com.mock.service;

import com.mock.entity.BasicDataEntity;
import com.mock.entity.ResponeBase;

public interface IBasicService {
	/**
     * 	查询基础数据列表
     * @param ILogEntity
     * @return url路径集合
     */
	ResponeBase selectBasicData(BasicDataEntity basicDataEntity);
}
