package com.mock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mock.service.IUrlService;
import com.mock.tool.RecordIP;
import com.mock.entity.ResponeBase;
import com.mock.entity.IUrlEntity;

@Controller
@RequestMapping("/system/url")
public class IUrlController {
	private Logger log=LoggerFactory.getLogger(IUrlController.class);

	@Autowired
	private IUrlService iUrlService;
	
	@RequestMapping(value="/mockList")
	public String mockList(HttpServletRequest request) {
		String ip=RecordIP.getIpAddress(request);
		log.info("进入mockList:"+ip);
		return "/system/url/mockList";
	}

	@ResponseBody
	@RequestMapping("/list")
	public ResponeBase list(@RequestBody JSONObject msg,Model model) {
		IUrlEntity mockUrl=JSONObject.toJavaObject(msg, IUrlEntity.class);
		ResponeBase response= iUrlService.selectMockUrlList(mockUrl);
		return response;
	}
	
	
	@ResponseBody
	@RequestMapping("/addAction")
	public ResponeBase	addAction(HttpServletRequest request,@RequestBody JSONObject msg) {
		IUrlEntity mockUrl=JSONObject.toJavaObject(msg, IUrlEntity.class);
		//获取项目名称
//		String path=request.getContextPath();
		String Name=mockUrl.getName();
		String Url=mockUrl.getUrl();
		int categoryId=mockUrl.getCategoryId();
		if(categoryId==0) {
    		return ResponeBase.failResult("请求缺少categoryId字段", null);
    	}		
		if(Name==null) {
    		return ResponeBase.failResult("请求缺少name字段", null);
    	}else if(Name.equals("") || Name.isEmpty()) {
//			log.info("Mock-Name不能为空");
			return ResponeBase.failResult("name不能为空", null);
		}
		if(Url==null) {
			return ResponeBase.failResult("请求缺少url字段", null);
		}else if(Url.equals("") || Url.isEmpty()) {
//			log.info("Mock-Url不能为空");
			return ResponeBase.failResult("Url不能为空", null);
		}
//    	mockUrl.setUrl(path+Url);
    	ResponeBase response=iUrlService.insertMockUrl(mockUrl);
    	return response;
	}
	
	@ResponseBody
	@RequestMapping("/edit/{urlId}")
	public ResponeBase	edit(@PathVariable("urlId") int urlId) {
		ResponeBase response=iUrlService.selectMockUrlById(urlId);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/editAction")
	public ResponeBase	editAction(HttpServletRequest request,@RequestBody JSONObject msg) {
		IUrlEntity mockUrl=JSONObject.toJavaObject(msg, IUrlEntity.class);
//		String path=request.getContextPath();
		String Name=mockUrl.getName();
		String Url=mockUrl.getUrl();
		
		if(Name==null) {
    		return ResponeBase.failResult("请求缺少name字段", null);
    	}else if(Name.equals("") || Name.isEmpty()) {
			return ResponeBase.failResult("name不能为空", null);
		}
		if(Url==null) {
			return ResponeBase.failResult("请求缺少url字段", null);
		}else if(Url.equals("") || Url.isEmpty()) {
			return ResponeBase.failResult("Url不能为空", null);
		}
    	
//    	mockUrl.setUrl(path+Url);
		ResponeBase response=iUrlService.updateMockUrl(mockUrl);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/del/{urlId}")
	public ResponeBase	delAction(@PathVariable("urlId") int urlId) {
		ResponeBase response=iUrlService.deleteMockUrlById(urlId);
		return response;
	}
}
