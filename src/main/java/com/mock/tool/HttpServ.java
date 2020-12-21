package com.mock.tool;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.http.ContentType;
import lombok.Cleanup;

public class HttpServ extends HttpServlet{
//	<servlet>
//		<description>This is the description of my J2EE component </description>
//		<display-name>This is the display name of my J2EE component </display-name>
//		<servlet-name>vMock</servlet-name>
//		<servlet-class>com.vmock.tool.HttpServ</servlet-class>
//	</servlet>
//
//	<servlet-mapping>
//		<servlet-name>vMock</servlet-name>
//		<url-pattern>/http</url-pattern>
//	</servlet-mapping>

	/**
	 * 
	 * 请求地址：http://127.0.0.1:8080/vMock/http
	 * */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String URI=request.getRequestURI();
		System.out.println("URI:"+URI);
		System.out.println("发送get请求。。。。。。。。。。。");
		StringBuffer requestURL = request.getRequestURL(); 
		System.out.println("requestURL:"+requestURL);
		String queryString = request.getQueryString();  
		System.out.println("queryString:"+queryString);
		String fromURL = request.getHeader("Referer");  
		System.out.println("fromURL:"+fromURL);
		response.setCharacterEncoding("utf-8");//告诉服务器用UTF-8进行编码解析
		response.setContentType("text/html; charset=utf-8");//这行代码干了两件事，同时告诉服务器和客户端使用UTF-8编码
		if(URI.equals("/vMock/http/testUrl")) {
			OutMsgUtils.outMsg("{\"code\":0,\"data\":[\"添加产品：平安意外险1-6类（惠享版）\"],\"msg\":\"success\",\"errMsg\":null}", response);
		}else if(URI.equals("/vMock/http/example")){
			OutMsgUtils.outMsg("<h1>示例接口请求成功~</h1>", response);
		}else {
//			OutMsgUtils.outMsg("收到get请求", response);
			response.sendRedirect(URI);
		}
//		@Cleanup  PrintWriter writer = response.getWriter();
//        writer.print("收到get请求");
//        writer.flush();
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType(ContentType.JSON.toString(UTF_8));
		System.out.println("发送post方法。。。。。。。。。。");
		@Cleanup  PrintWriter writer = resp.getWriter();
        writer.print("收到post请求");
        writer.flush();
	}
}
