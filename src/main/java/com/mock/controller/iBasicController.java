package com.mock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mock.entity.BasicDataEntity;
import com.mock.entity.IUserEntity;
import com.mock.entity.ResponeBase;
import com.mock.service.IBasicService;
import com.mock.service.IUserService;

@Controller
@RequestMapping("/system/basic")
public class iBasicController {
	private Logger logger=LoggerFactory.getLogger(iBasicController.class);

	@Autowired
	private IBasicService iBasicService;
	
	@ResponseBody
	@RequestMapping("/method")
	public ResponeBase list(@RequestBody JSONObject msg,Model model) {
		BasicDataEntity basicDataEntity=JSONObject.toJavaObject(msg, BasicDataEntity.class);		
		ResponeBase response= iBasicService.selectBasicData(basicDataEntity);
		return response;
	}
}
