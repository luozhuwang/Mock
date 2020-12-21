package com.mock.dao;

import java.util.List;
import java.util.Map;

import com.mock.entity.ILogEntity;
import com.mock.entity.IUrlEntity;

public interface ILogDao {
	/**
     * 查询url路径列表
     *
     * @param IUrlEntity url路径
     * @return url路径集合
     */
    List<ILogEntity> selectMockLogList(ILogEntity iLogEntity);
    /**
     * 查询url路径列表
     * @param	url_id
     * @return urlEntity
     * */
    ILogEntity	selectMockLogById(int log_id);

    /**
     * 	新增url路径
     *
     * @param mockUrl url路径
     * @return 结果
     */
    Integer insertMockLog(ILogEntity iLogEntity);
    
    /**
     * 日期进行统计
     * */
    List<Map<String, Object>> selectMockLogListByDate(ILogEntity iLogEntity);
    /**
     * 请求名称进行统计
     * */
    List<Map<String, Object>> selectMockLogListByName(ILogEntity iLogEntity);
}
