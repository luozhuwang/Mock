package com.mock.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.metadata.MethodType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.mock.entity.IUrlEntity;
import com.mock.entity.IUserEntity;
import com.mock.entity.ResponeBase;
import com.mock.service.IUserService;
import com.mock.tool.DateFormat;
import com.mock.tool.RecordIP;

@Controller
@RequestMapping("/system")
public class homeController {
	private Logger log=LoggerFactory.getLogger(homeController.class);
			
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private IUserService iUserService;
	
	@RequestMapping(value="/index")
	public String home(HttpServletRequest request) {
		String ip=RecordIP.getIpAddress(request);
		log.info("进入首页:"+ip);
		return "/index";
	}
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request) {
		String ip=RecordIP.getIpAddress(request);
		log.info("登陆页面:"+ip);
		return "/system/user/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/loginAction",method = RequestMethod.POST)
	public ResponeBase loginAction( @RequestBody JSONObject msg,HttpSession session) {
		IUserEntity iUser=JSONObject.toJavaObject(msg, IUserEntity.class);
		ResponeBase response= iUserService.userLogin(iUser);
		IUserEntity iUser2=(IUserEntity) response.getData();
		String message=response.getMsg();
		if(message.equals("success")){				
			session.setAttribute("userName", iUser2.getUserName());
			session.setAttribute("userType", iUser2.getUserType());
			session.setAttribute("userId", iUser2.getUserId());
			iUser2.setLoginIp(RecordIP.getIpAddress(request));
			iUser2.setLoginDate(DateFormat.DateToString(new Date()));
			iUserService.updateUser(iUser2);
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/registAction")
	public ResponeBase	addAction(HttpServletRequest request,@RequestBody JSONObject msg) {
		IUserEntity iUser=JSONObject.toJavaObject(msg, IUserEntity.class);		
    	ResponeBase response=iUserService.register(iUser);
    	return response;
	}

	
	@RequestMapping("/outLogin")
	public String Longinout(HttpSession session){
		session.invalidate();
		return "redirect:/system/login";
	}
	
}
