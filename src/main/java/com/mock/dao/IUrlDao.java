package com.mock.dao;

import java.util.List;

import com.mock.entity.IUrlEntity;

public interface IUrlDao {
	/**
     * 	查询url路径列表
     *
     * @param IUrlEntity url路径
     * @return url路径集合
     */
    List<IUrlEntity> selectMockUrlList(IUrlEntity mockUrl);
    /**
     * 	查询url路径列表
     * @param	url_id
     * @return urlEntity
     * */
    IUrlEntity	selectMockUrlById(int url_id);
    /**
     * 	查询url路径列表
     * @param	url_id
     * @return urlEntity
     * */
    IUrlEntity	selectMockUrlByUrl(String url);
    /**
     * 	校验Url是否唯一
     * @param	url
     * */
    Integer isUniqueUrl(String url);

    /**
     * 	新增url路径
     *
     * @param mockUrl url路径
     * @return 结果
     */
    Integer insertMockUrl(IUrlEntity mockUrl);

    /**
     * 	修改url路径
     *
     * @param IUrlEntity url路径
     * @return 结果
     */
    Integer updateMockUrl(IUrlEntity mockUrl);

    /**
     * 	通过ID删除URL信息
     * */
    Integer	deleteMockUrlById(int url_id);

}
