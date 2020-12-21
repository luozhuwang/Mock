package com.mock.service;

import com.mock.entity.IUrlEntity;
import com.mock.entity.ResponeBase;

public interface IUrlRespService {
	/**
     * 	跟据URLId查询返回体列表
     * @param	url_id
     * @return urlEntity
     * */
	ResponeBase selectMockResp(IUrlEntity mockResp);
	/**
     * 	跟据Id查询返回体
     * @param	id
     * @return urlEntity
     * */
	ResponeBase	selectMockRespById(IUrlEntity mockResp);
	 /**
     * 	新增mock Response路径
     * @param mockUrl url路径
     * @return 结果
     */
	ResponeBase insertMockResp(IUrlEntity mockUrlResp);
    
    /**
     * 	修改url路径
     *
     * @param IUrlEntity url路径
     * @return 结果
     */
	ResponeBase updateMockResp(IUrlEntity mockUrlResp);

    /**
     * 	通过ID删除URL信息
     * */
	ResponeBase	deleteMockRespById(int id);
}
