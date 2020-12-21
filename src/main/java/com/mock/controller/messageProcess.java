package com.mock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/system/url")
public class messageProcess {
	
	@ResponseBody
	@RequestMapping(value="/lima/order",method = RequestMethod.POST)
	public String orderMess(@RequestBody JSONObject msg) {
		System.out.println(msg.toJSONString());
		return "get order Message";
	}
	
	@ResponseBody
	@RequestMapping(value="/lima/policy",method = RequestMethod.POST)
	public String policyMess(@RequestBody JSONObject msg) {
		System.out.println(msg.toJSONString());
		return "get policy Message";
	}
	@ResponseBody
	@RequestMapping(value="/lima/policyFile",method = RequestMethod.POST)
	public String policyFileMess(@RequestBody JSONObject msg) {	
		System.out.println(msg.toJSONString());
		return "get policyFile Message";
	}
}
