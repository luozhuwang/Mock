package com.mock.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mock.tool.SpringContextUtils;

public class MockFilter implements Filter{	
	private MockFilterHandler mockFilterHandler;
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		mockFilterHandler=SpringContextUtils.getBean(MockFilterHandler.class);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		  // 转换类型
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        setCorsHeader(httpServletRequest, httpServletResponse);
        String URL=httpServletRequest.getRequestURI();        
        //项目名称
        String path=httpServletRequest.getContextPath()+"/system";
		if(URL.startsWith(path)) {			
				chain.doFilter(servletRequest,servletResponse);				
		}else {			
			// 执行主要逻辑处理
			try {
				mockFilterHandler.execute(httpServletRequest, httpServletResponse);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void destroy() {
		
	}

	 /**
     * 设置filter跨域header
     *
     * @param request  请求
     * @param response 响应
     */
    private static void setCorsHeader(HttpServletRequest request, HttpServletResponse response) {
        // 域
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        // credentials
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // method
        response.setHeader("Access-Control-Allow-Methods", "*");
        // age
        response.setHeader("Access-Control-Max-Age", "3600");
        // header
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setCharacterEncoding("utf-8");//告诉服务器用UTF-8进行编码解析
//      response.setContentType("text/html;charset=UTF-8");//这行代码干了两件事，同时告诉服务器和客户端使用UTF-8编码
    }

	
}
