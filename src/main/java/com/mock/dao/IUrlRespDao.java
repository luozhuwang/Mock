package com.mock.dao;

import java.util.List;

import com.mock.entity.IUrlEntity;

public interface IUrlRespDao {
	/**
     * 	跟据URLId查询返回体列表
     * @param	url_id
     * @return urlEntity
     * */
	List<IUrlEntity> selectMockResp(int url_id);
	/**
     * 	跟据Id查询返回体
     * @param	id
     * @return urlEntity
     * */
	IUrlEntity	selectMockRespById(IUrlEntity mockResp);
	 /**
     * 	新增mock Response路径
     * @param mockUrl url路径
     * @return 结果
     */
    Integer insertMockResp(IUrlEntity mockUrlResp);
    
    /**
     * 	修改url路径
     *
     * @param IUrlEntity url路径
     * @return 结果
     */
    Integer updateMockResp(IUrlEntity mockUrlResp);

    /**
     * 	通过ID删除URL信息
     * */
    Integer	deleteMockRespById(int id);
}
