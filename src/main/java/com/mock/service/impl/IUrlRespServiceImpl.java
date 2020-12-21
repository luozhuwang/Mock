package com.mock.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mock.dao.IUrlRespDao;
import com.mock.entity.IUrlEntity;
import com.mock.entity.ResponeBase;
import com.mock.service.IUrlRespService;

@Service
public class IUrlRespServiceImpl implements IUrlRespService{
	private Logger log =LoggerFactory.getLogger(IUrlRespServiceImpl.class);
	
	@Autowired
	private IUrlRespDao iUrlRespDao;
	
	@Override
	public ResponeBase selectMockResp(IUrlEntity mockResp) {
		PageHelper.startPage(mockResp.getPageNum(), mockResp.getPageSize());
		List<IUrlEntity> mockUrlResps=iUrlRespDao.selectMockResp(mockResp.getUrlId());
		if(mockUrlResps.isEmpty()) {
			log.info("【mockUrlResp-UrlId】查询为空");
			return  ResponeBase.resultNull("【mockUrlResp-UrlId】查询为空");
		}
		PageInfo<IUrlEntity> pageinfo=new PageInfo<IUrlEntity>(mockUrlResps);
		return ResponeBase.successResult(pageinfo);
	}

	@Override
	public ResponeBase selectMockRespById(IUrlEntity mockResp) {
		
		IUrlEntity mockUrlResp=iUrlRespDao.selectMockRespById(mockResp);
		if(mockUrlResp == null) {
			log.info("通过Id查询【MockResp】为空");
			return  ResponeBase.resultNull("通过Id查询【MockResp】为空");
		}
		return ResponeBase.successResult(mockUrlResp);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase insertMockResp(IUrlEntity mockUrlResp) {
		int count=-1;
		int url_id=mockUrlResp.getUrlId();
		try {			
			count=iUrlRespDao.insertMockResp(mockUrlResp);
			if(count>0) {
				List<IUrlEntity> mockUrls=iUrlRespDao.selectMockResp(url_id);
				return ResponeBase.successResult(mockUrls);
			}else {	
				log.info("添加【Mock-响应数据】失败");
				return ResponeBase.failResult("添加【Mock-响应数据】失败", null);
			}
		}catch (Exception e) {
			log.info("服务异常，添加【Mock-响应数据】失败");
			return ResponeBase.failResult("服务异常，添加【Mock-响应数据】失败", null);
		}
		
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase updateMockResp(IUrlEntity mockUrlResp) {
		int count=-1;
		count=iUrlRespDao.updateMockResp(mockUrlResp);
		if(count>0) {
			return ResponeBase.resultNull("【Mock-UrlResp】修改成功");
		}else {			
			log.info("【Mock-UrlResp】修改失败");
			return ResponeBase.failResult("【Mock-UrlResp】修改失败", null);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase deleteMockRespById(int id) {
		int count=iUrlRespDao.deleteMockRespById(id);
		if(count>0) {
			return ResponeBase.resultNull("【Mock-UrlResp】删除成功");
		}else {			
			log.info("【Mock-Url失败】");
			return ResponeBase.failResult("【Mock-UrlResp】删除失败", null);
		}
	}

}
