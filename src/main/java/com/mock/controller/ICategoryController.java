package com.mock.controller;

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
import com.mock.entity.ICategoryEntity;
import com.mock.entity.ILogEntity;
import com.mock.entity.ResponeBase;
import com.mock.service.ICategoryService;
import com.mock.tool.RecordIP;

@Controller
@RequestMapping("/system/category")
public class ICategoryController {
	private Logger log=LoggerFactory.getLogger(ICategoryController.class);

	@Autowired
	private ICategoryService iCategoryService;
	
	@RequestMapping(value="/categoryList")
	public String mockLog(HttpServletRequest request) {
		String ip=RecordIP.getIpAddress(request);
		log.info("进入模块管理:"+ip);
		return "/system/category/categoryList";
	}
	@ResponseBody
	@RequestMapping("/list")
	public ResponeBase list(@RequestBody JSONObject msg,Model model) {
		ICategoryEntity iCategoryEntity=JSONObject.toJavaObject(msg, ICategoryEntity.class);	
//		String beginTime=iCategoryEntity.getBeginTime();
//		String endTime=iCategoryEntity.getEndTime();
//		if(!beginTime.equals("") || beginTime!= "") {			
//			iCategoryEntity.setBeginTime(beginTime+" 00:00:00");
//		}if(!endTime.equals("") || endTime!= "") {			
//			iCategoryEntity.setEndTime(endTime+" 23:59:59");
//		}
		ResponeBase response= iCategoryService.selectCategory(iCategoryEntity);		
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/addAction")
	public ResponeBase	addAction(HttpServletRequest request,@RequestBody JSONObject msg) {
		ICategoryEntity iCategoryEntity=JSONObject.toJavaObject(msg, ICategoryEntity.class);	
		String Name=iCategoryEntity.getName();
		if(Name==null) {
    		return ResponeBase.failResult("请求缺少【模块名称】字段", null);
    	}else if(Name.equals("") || Name.isEmpty()) {
			return ResponeBase.failResult("【模块名称】不能为空", null);
		}
		ResponeBase response= iCategoryService.insertCategory(iCategoryEntity);		
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/edit/{id}")
	public ResponeBase	edit(@PathVariable("id") int id) {
		ResponeBase response=iCategoryService.selectCategoryById(id);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/editAction")
	public ResponeBase	editAction(HttpServletRequest request,@RequestBody JSONObject msg) {
		ICategoryEntity iCategoryEntity=JSONObject.toJavaObject(msg, ICategoryEntity.class);	
		String Name=iCategoryEntity.getName();
		if(Name==null) {
    		return ResponeBase.failResult("请求缺少【模块名称】字段", null);
    	}else if(Name.equals("") || Name.isEmpty()) {
			return ResponeBase.failResult("【模块名称】不能为空", null);
		}
		ResponeBase response= iCategoryService.updateCategory(iCategoryEntity);		
		return response;
	}
}
