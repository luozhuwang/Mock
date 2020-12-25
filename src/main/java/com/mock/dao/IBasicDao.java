package com.mock.dao;

import java.util.List;

import com.mock.entity.BasicDataEntity;

public interface IBasicDao {

	List<BasicDataEntity> selectBasicData(BasicDataEntity basicDataEntity);
}
