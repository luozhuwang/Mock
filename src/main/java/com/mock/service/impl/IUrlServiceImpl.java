package com.mock.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mock.dao.IUrlDao;
import com.mock.entity.ResponeBase;
import com.mock.entity.IUrlEntity;
import com.mock.service.IUrlService;

@Service
public class IUrlServiceImpl implements IUrlService{
	private Logger log =LoggerFactory.getLogger(IUrlServiceImpl.class);
	
	@Autowired
	private IUrlDao iUrlDao;

	@Override
	public ResponeBase selectMockUrlList(IUrlEntity mockUrl) {
		PageHelper.startPage(mockUrl.getPageNum(), mockUrl.getPageSize());
		List<IUrlEntity> mockUrls=iUrlDao.selectMockUrlList(mockUrl);
		if(mockUrls.isEmpty()) {
			log.info("查询【MockUrl】列表为空");
			return  ResponeBase.resultNull("【MockUrl】列表为空");
		}
		PageInfo<IUrlEntity> pageinfo=new PageInfo<IUrlEntity>(mockUrls);
		return ResponeBase.successResult(pageinfo);
	}

	@Override
	public ResponeBase selectMockUrlById(int urlId) {
		IUrlEntity mockUrl=iUrlDao.selectMockUrlById(urlId);
		if(mockUrl == null) {
			log.info("【MockUrl-Id】查询为空");
			return  ResponeBase.resultNull("【MockUrl-Id】查询为空");
		}
		return ResponeBase.successResult(mockUrl);
	
	}

	@Override
	public ResponeBase selectMockUrlByUrl(String url) {
		IUrlEntity mockUrl=iUrlDao.selectMockUrlByUrl(url);
		if(mockUrl == null) {
			log.info("【Mock-url】查询为空");
			return  ResponeBase.resultNull("【Mock-url】查询为空");
		}
		return ResponeBase.successResult(mockUrl);
	}

	@Override
	public boolean isUniqueUrl(String url) {
		int count=iUrlDao.isUniqueUrl(url);
		if(count>0) {
			log.info("【Mock-Url】已存在");
			return true;
		}else {
			return false;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase insertMockUrl(IUrlEntity mockUrl) {
		int count=-1;
//		String UrlName=mockUrl.getName();
		String Url=mockUrl.getUrl();
//		if(UrlName.equals("") || UrlName.isEmpty()) {
//			log.info("Mock-Name不能为空");
//			return ResponeBase.failResult("MockName不能为空", null);
//		}
//		if(Url.equals("") || Url.isEmpty()) {
//			log.info("Mock-Url不能为空");
//			return ResponeBase.failResult("Mock-Url不能为空", null);
//		}
		int isUnique=iUrlDao.isUniqueUrl(Url);
		if(isUnique>0) {
			return ResponeBase.failResult("【Mock-Url】不能重复", null);
		}
		try {			
			count=iUrlDao.insertMockUrl(mockUrl);
			if(count>0) {
				List<IUrlEntity> mockUrls=iUrlDao.selectMockUrlList(null);
				return ResponeBase.successResult(mockUrls);
			}else {	
				log.info("添加【Mock-Url】失败");
				return ResponeBase.failResult("添加【Mock-Url】失败", null);
			}
		}catch (Exception e) {
			log.info("服务异常，添加【Mock-Url】失败");
			return ResponeBase.failResult("服务异常，添加【Mock-Url】失败", null);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase updateMockUrl(IUrlEntity mockUrl) {
		int count=-1;
		count=iUrlDao.updateMockUrl(mockUrl);
		if(count>0) {
			return ResponeBase.resultNull("【Mock-Url】修改成功");
		}else {			
			log.info("【Mock-Url】修改失败");
			return ResponeBase.failResult("【Mock-Url】修改失败", null);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponeBase deleteMockUrlById(int urlId) {
		int count=iUrlDao.deleteMockUrlById(urlId);
		if(count>0) {
			return ResponeBase.resultNull("【Mock-Url】删除成功");
		}else {			
			log.info("Mock-Url失败");
			return ResponeBase.failResult("【Mock-Url】删除失败", null);
		}
	}

}
