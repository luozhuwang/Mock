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
import com.mock.entity.IUserEntity;
import com.mock.entity.ResponeBase;
import com.mock.service.IUserService;
import com.mock.tool.Constant;
import com.mock.tool.RecordIP;

@Controller
@RequestMapping("/system/user")
public class IUserController {
	private Logger log=LoggerFactory.getLogger(IUserController.class);

	@Autowired
	private IUserService iUserService;
	
	
	@RequestMapping(value="/userList")
	public String userList(HttpServletRequest request) {
		String ip=RecordIP.getIpAddress(request);
		log.info("进入userList:"+ip);
		return "/system/user/userList";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public ResponeBase list(@RequestBody JSONObject msg,Model model) {
		IUserEntity iUser=JSONObject.toJavaObject(msg, IUserEntity.class);
		String beginTime=iUser.getBeginTime();
		String endTime=iUser.getEndTime();
		if(!beginTime.equals("") || beginTime!= "") {			
			iUser.setBeginTime(beginTime+" 00:00:00");
		}if(!endTime.equals("") || endTime!= "") {			
			iUser.setEndTime(endTime+" 23:59:59");
		}
		ResponeBase response= iUserService.selectUserList(iUser);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/resetPWD")
	public ResponeBase resetUserPwd(@RequestBody JSONObject msg) {
		IUserEntity iUser=JSONObject.toJavaObject(msg, IUserEntity.class);
		ResponeBase response= iUserService.resetUserPwd(iUser.getUpdateBy(),iUser.getUserId(), Constant.initPWD);
		return response;
	}
	@ResponseBody
	@RequestMapping("/editPWD")
	public ResponeBase editUserPwd(@RequestBody JSONObject msg) {
		IUserEntity iUser=JSONObject.toJavaObject(msg, IUserEntity.class);
		ResponeBase response= iUserService.editUserPwd(iUser.getUpdateBy(),iUser.getUserId(), iUser.getOldPassword(), iUser.getPassword());
		return response;
	}	
	
	@ResponseBody
	@RequestMapping("/edit/{userId}")
	public ResponeBase	edit(@PathVariable("userId") int userId) {
		ResponeBase response=iUserService.selectUserById(userId);
		return response;
	}
	@ResponseBody
	@RequestMapping("/switchOn")
	public ResponeBase	switchOn(@RequestBody JSONObject msg) {
		IUserEntity iUser=JSONObject.toJavaObject(msg, IUserEntity.class);
		ResponeBase response=iUserService.updateUser(iUser);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/editAction")
	public ResponeBase	editAction(@RequestBody JSONObject msg) {
		IUserEntity iUser=JSONObject.toJavaObject(msg, IUserEntity.class);
		String userName=iUser.getUserName();
    	if(userName==null) {
    		return ResponeBase.failResult("请求缺少userName字段", null);
    	}
    		
    	ResponeBase response=iUserService.updateUser(iUser);
    	return response;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public ResponeBase	delAction(@RequestBody JSONObject msg) {
		IUserEntity iUser=JSONObject.toJavaObject(msg, IUserEntity.class);
		ResponeBase response=iUserService.delUserById(iUser.getUpdateBy(),iUser.getUserId());
		return response;
	}
}
