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
import com.mock.entity.ILogEntity;
import com.mock.entity.IUrlEntity;
import com.mock.entity.ResponeBase;
import com.mock.service.ILogService;
import com.mock.tool.RecordIP;

@Controller
@RequestMapping("/system/url")
public class ILogController {
	private Logger log=LoggerFactory.getLogger(ILogController.class);
	
	@Autowired
	private ILogService iLogService;

	@RequestMapping(value="/mockLog")
	public String mockLog(HttpServletRequest request) {
		String ip=RecordIP.getIpAddress(request);
		log.info("进入mockLog:"+ip);
		return "/system/url/mockLog";
	}
	
	@ResponseBody
	@RequestMapping("/mockLog/list")
	public ResponeBase list(@RequestBody JSONObject msg,Model model) {
		ILogEntity iLogEntity=JSONObject.toJavaObject(msg, ILogEntity.class);	
		String beginTime=iLogEntity.getBeginTime();
		String endTime=iLogEntity.getEndTime();
		if(!beginTime.equals("") || beginTime!= "") {			
			iLogEntity.setBeginTime(beginTime+" 00:00:00");
		}if(!endTime.equals("") || endTime!= "") {			
			iLogEntity.setEndTime(endTime+" 23:59:59");
		}
		ResponeBase response= iLogService.selectMockLogList(iLogEntity);		
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/mockLog/{logId}")
	public ResponeBase	edit(@PathVariable("logId") int logId) {
		ResponeBase response= iLogService.selectMockLogById(logId);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/mockLog/byDate")
	public ResponeBase byDate(@RequestBody JSONObject msg) {
		ILogEntity iLogEntity=JSONObject.toJavaObject(msg, ILogEntity.class);			
		ResponeBase response= iLogService.selectMockLogListByDate(iLogEntity);		
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/mockLog/byName")
	public ResponeBase byName(@RequestBody JSONObject msg) {
		ILogEntity iLogEntity=JSONObject.toJavaObject(msg, ILogEntity.class);			
		ResponeBase response= iLogService.selectMockLogListByName(iLogEntity);		
		return response;
	}
}
