package com.mock.service;

import com.mock.entity.ResponeBase;

import java.util.List;
import java.util.Map;

import com.mock.entity.ILogEntity;

public interface ILogService {

	/**
     * 	查询请求日志列表
     * @param ILogEntity
     * @return url路径集合
     */
	ResponeBase selectMockLogList(ILogEntity iLogEntity);
    /**
     * 	通过ID查询请求日志路径列表
     * @param	logId
     * */
	ResponeBase	selectMockLogById(int logId);
	 
    /**
     *	 新增mock请求日志
     * @param ILogEntity
     * @return 结果
     */
	ResponeBase insertMockLog(ILogEntity iLogEntity);
	 /**
     * 日期进行统计
     * */
	ResponeBase selectMockLogListByDate(ILogEntity iLogEntity);
    /**
     * 请求名称进行统计
     * */
	ResponeBase selectMockLogListByName(ILogEntity iLogEntity);
  
	
}
