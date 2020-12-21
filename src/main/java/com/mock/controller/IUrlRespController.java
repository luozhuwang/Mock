package com.mock.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mock.entity.IUrlEntity;
import com.mock.entity.ResponeBase;
import com.mock.service.IUrlRespService;
import com.mock.tool.RecordIP;

@Controller
@RequestMapping("/system/mockresp")
public class IUrlRespController {

	@Autowired
	private IUrlRespService iUrlRespService;
	
	@RequestMapping(value="/index")
	public String home(HttpServletRequest request) {		
		return "/system/url/mockResp";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public ResponeBase	resp(HttpServletRequest request,@RequestBody JSONObject msg) {
		IUrlEntity mockResp=JSONObject.toJavaObject(msg, IUrlEntity.class);
		ResponeBase response=iUrlRespService.selectMockResp(mockResp);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public ResponeBase	editById(HttpServletRequest request,@RequestBody JSONObject msg) {
		IUrlEntity mockResp=JSONObject.toJavaObject(msg, IUrlEntity.class);
		ResponeBase response=iUrlRespService.selectMockRespById(mockResp);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/addAction")
	public ResponeBase	addAction(HttpServletRequest request,@RequestBody JSONObject msg) {
		IUrlEntity mockUrlResp=JSONObject.toJavaObject(msg, IUrlEntity.class);
		String name=mockUrlResp.getName();
		String method=mockUrlResp.getRequestMethod();
		String content=mockUrlResp.getContent();
		if(name==null) {
    		return ResponeBase.failResult("【响应】请求缺少name字段", null);
    	}else if(name.equals("") || name.isEmpty()) {
			return ResponeBase.failResult("【响应】请求name不能为空", null);
		}
		if(method==null) {
    		return ResponeBase.failResult("【响应】请求缺少method字段", null);
    	}else if(method.equals("") || method.isEmpty()) {
			return ResponeBase.failResult("【响应】请求method不能为空", null);
		}
		if(content==null) {
    		return ResponeBase.failResult("【响应】请求缺少content字段", null);
    	}else if(content.equals("") || content.isEmpty()) {
			return ResponeBase.failResult("【响应】请求content不能为空", null);
		}
    	ResponeBase response=iUrlRespService.insertMockResp(mockUrlResp);
    	return response;
	}
	@ResponseBody
	@RequestMapping("/editAction")
	public ResponeBase	editAction(HttpServletRequest request,@RequestBody JSONObject msg) {
		IUrlEntity mockUrlResp=JSONObject.toJavaObject(msg, IUrlEntity.class);
		String name=mockUrlResp.getName();
		String method=mockUrlResp.getRequestMethod();
		String content=mockUrlResp.getContent();
				
		if(name==null) {
    		return ResponeBase.failResult("【响应】请求缺少name字段", null);
    	}else if(name.equals("") || name.isEmpty()) {
			return ResponeBase.failResult("【响应】请求name不能为空", null);
		}
		if(method==null) {
    		return ResponeBase.failResult("【响应】请求缺少method字段", null);
    	}else if(method.equals("") || method.isEmpty()) {
			return ResponeBase.failResult("【响应】请求method不能为空", null);
		}
		if(content==null) {
    		return ResponeBase.failResult("【响应】请求缺少content字段", null);
    	}else if(content.equals("") || content.isEmpty()) {
			return ResponeBase.failResult("【响应】请求content不能为空", null);
		}
		ResponeBase response=iUrlRespService.updateMockResp(mockUrlResp);
		return response;
	}
	@ResponseBody
	@RequestMapping("/del/{id}")
	public ResponeBase	delAction(@PathVariable("id") int id) {
		ResponeBase response=iUrlRespService.deleteMockRespById(id);
		return response;
	}
}
