package com.mock.service;

import com.mock.entity.ResponeBase;
import com.mock.entity.IUrlEntity;

public interface IUrlService {

	/**
     * 查询url路径列表
     * @param IUrlEntity url路径
     * @return url路径集合
     */
	ResponeBase selectMockUrlList(IUrlEntity mockUrl);
    /**
     * 查询url路径列表
     * @param	urlId
     * @return urlEntity
     * */
	ResponeBase	selectMockUrlById(int urlId);

	/**
     * 查询url路径列表
     * @param	url_id
     * @return urlEntity
     * */
	ResponeBase	selectMockUrlByUrl(String url);
	
	boolean isUniqueUrl(String url);
	 
    /**
     * 新增url路径
     * @param mockUrl url路径
     * @return 结果
     */
	ResponeBase insertMockUrl(IUrlEntity mockUrl);

    /**
     * 修改url路径
     * @param mockUrl url路径
     * @return 结果
     */
	ResponeBase updateMockUrl(IUrlEntity mockUrl);

    /**
     * 通过ID删除URL信息
     * */
	ResponeBase	deleteMockUrlById(int urlId);
	
}
