package com.mock.filter;


import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.mock.dao.ILogDao;
import com.mock.dao.IUrlDao;
import com.mock.dao.IUrlRespDao;
import com.mock.entity.ILogEntity;
import com.mock.entity.IMockResponse;
import com.mock.entity.IUrlEntity;
import com.mock.tool.BizUtils;
import com.mock.tool.OutMsgUtils;
import com.mock.tool.RegexUtils;
import com.mock.tool.SpringContextUtils;
import com.mock.tool.characterTools;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;

public class MockFilterHandler extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4402594270862122025L;

	private Logger logger =LoggerFactory.getLogger(MockFilterHandler.class);
	
	private IUrlDao IUrldao;
	private IUrlRespDao iUrlRespDao;
	private ILogDao iLogDao;
	
	
	public MockFilterHandler() {
		IUrldao=  SpringContextUtils.getBean(IUrlDao.class);
		iUrlRespDao=  SpringContextUtils.getBean(IUrlRespDao.class);
		iLogDao=  SpringContextUtils.getBean(ILogDao.class);
	}
	/**
     *	 执行处理
     * @param request
     * @param response
	 * @throws IOException 
	 * @throws InterruptedException 
     */
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
    	IUrlEntity mockResp=new IUrlEntity();
    	String contextPath=request.getContextPath();
    	//去除项目名称查询数据库路径
    	String URL=request.getRequestURI().replace(contextPath, "");
    	URL = StrUtil.isBlank(URL) ? StrUtil.SLASH : URL;
    	IUrlEntity mockUrlEntity=IUrldao.selectMockUrlByUrl(URL);
		
    	String contentType=request.getContentType();
    	String method=request.getMethod();
    	    	
    	String requestDetail = BizUtils.requestToJson(request);
    	logger.info("requestDetail："+requestDetail);  
    		
    	if(mockUrlEntity == null) {    		
    		//Path传参情况，请用占位符{path}占位--待处理
    		String path =RegexUtils.resolve_Reg(URL, "/[1-9]\\d*");
    		if(path !=null ) {
    			logger.info("处理URL占位符："+path);
    			URL=URL.replace(path, "/{path}");
    		}
    		String path1 =RegexUtils.resolve_Reg(URL, "/[1-9]\\d*/");
    		if(path1 !=null ) {
    			logger.info("处理URL占位符："+path1);
    			URL=URL.replace(path1, "/{path}/");
    		}	
    		//URL解码
    		URL=characterTools.getURLDecoderString(URL);
    		mockUrlEntity=IUrldao.selectMockUrlByUrl(URL);
    	}
    	IMockResponse mockResponse =new IMockResponse();
		if(mockUrlEntity == null) {
			logger.info("未配置此接口");
			OutMsgUtils.outMsg("未配置此接口", response);
			mockResponse.setContent("未配置此接口");
			return ;
		}else {
			logger.info("请求URL："+URL);	
			//json字符串序列化后保持顺序不变
			LinkedHashMap<String, Object> json = JSON.parseObject(requestDetail,LinkedHashMap.class, Feature.OrderedField);
			JSONObject Object=new JSONObject(true);
			Object.putAll(json);
			
			int urlId=mockUrlEntity.getUrlId();
			String body=Object.getString("params");
			//判断请求参数是否一致
			//request请求
			int param_type=-1;
			if(contentType!=null) {				
				if(contentType.equals("application/x-www-form-urlencoded")|| contentType.contains("form-data")) {
					param_type=1;
				}else if(contentType.equals("application/json")) {
					param_type=2;
				}else if( contentType.equals("application/javascript")|| contentType.equals("application/xml")|| contentType.equals("text/html")|| contentType.equals("text/plain")) {
					param_type=3;
				}
			}
			//get请求只允许form-data/application/x-www-form-urlencoded 参数
			if(method.equals("GET") && !body.equals("{}")) {
				param_type=1;
			}
			
			if(body.equals("") || body.equals("{}")) {
				body=Object.getString("body");
			}
			
			mockResp.setUrlId(urlId);
			mockResp.setRequestMethod(method);
			mockResp.setParamType(param_type);
			mockResp.setRequestParam(body);
			
			IUrlEntity	respEntity=iUrlRespDao.selectMockRespById(mockResp);
			if(respEntity!=null) {				
				String Content=respEntity.getContent();
				mockResponse.setContent(Content);
				int delay=respEntity.getDelay();
				if(delay!=0) {
					Thread.sleep(delay*1000);
				}
				OutMsgUtils.outMsg(Content, response);	
			}else {
				OutMsgUtils.outMsg("未找到对应Mock配置，请检查请求", response);
				return ;
			}
//			
//			int paramType=mockUrlEntity.getParamType();
//			if(paramType != param_type) {
//				OutMsgUtils.outMsg(URL+"：请求contentType不一致，请查看Mock配置", response);
//				mockResponse.setContent("请求contentType不一致，请查看Mock配置");
//				logger.info("请求contentType不一致，请查看Mock配置");
//				return ;
//			}else {
//				
//				String sqlDetail=mockUrlEntity.getRequestParam();
//				if(param_type==1) {
//					JSONObject Object1 = JSON.parseObject(body);
//					JSONObject Object2 = JSON.parseObject(sqlDetail);
//					
//					boolean sameJson=JsonUtils.compareTwoDeviceInfo(Object1, Object2);
//					if(!sameJson) {
//						OutMsgUtils.outMsg(URL+"：请求x-www-form-urlencoded参数不一致，请查看Mock配置", response);
//						mockResponse.setContent("请求x-www-form-urlencoded参数不一致，请查看Mock配置");
//						logger.info("请求x-www-form-urlencoded参数不一致，请查看Mock配置");
//						return ;
//					}
//				}else if(param_type==2) {	
//					if(!body.equals(sqlDetail)) {
//						OutMsgUtils.outMsg(URL+"：请求raw参数不一致，请查看Mock配置", response);
//						mockResponse.setContent("请求raw参数不一致，请查看Mock配置");
//						logger.info("请求raw参数不一致，请查看Mock配置");
//						return ;
//					}
//					
//				}
//					String Content=mockUrlEntity.getContent();
//					String customHeader=mockUrlEntity.getResponseHeader();				
//					logger.info("响应结果："+Content);
//					mockResponse.setContent(Content);
//					mockResponse.setCustomHeader(customHeader);
//					OutMsgUtils.outMsg(Content, response);							
//			}
//			
			
		}
		if (mockResponse != null) {				 
			logResponse(requestDetail, mockResponse, request,URL,mockUrlEntity);
		}

    }
    /**
     * 记录response日志
     *
     * @param requestDetail 请求详细
     * @param hitUrl        命中url
     * @param mockResponse  mock的返回实体
     * @param request       httpServletRequest
     * @param hitUrl       命中URL
     * @param mockUrlEntity mock实体
     */
    private void logResponse(String requestDetail, IMockResponse mockResponse, HttpServletRequest request,String hitUrl,IUrlEntity mockUrlEntity) {
    	ILogEntity iLogEntity=new ILogEntity();
    			
    	String ip=ServletUtil.getClientIP(request);
    	String contextPath=request.getContextPath();
    	String URL=characterTools.getURLDecoderString(request.getRequestURI().replace(contextPath, ""));
    	String method=request.getMethod();
    	JSONObject jsonObject =JSON.parseObject(requestDetail);
		String headers=jsonObject.getString("headers");
		String params=jsonObject.getString("params");
		String body=jsonObject.getString("body");

//		logger.info("请求URL:"+URL);
		iLogEntity.setRequestIp(ip);
		iLogEntity.setRequestMethod(method);
		iLogEntity.setRequestUrl(URL);
		iLogEntity.setCategoryId(mockUrlEntity.getCategoryId());
		iLogEntity.setHitUrl(hitUrl);
		
		iLogEntity.setRequestHeader(headers);
		
		if(!params.equals("{}")) {			
			iLogEntity.setRequestDetail(params);
		}
		if(!body.equals("")){
			iLogEntity.setRequestDetail(body);
		}
		iLogEntity.setResponseDetail(mockResponse.getContent());
		iLogEntity.setResponseHeader(mockResponse.getCustomHeader());
		
		iLogDao.insertMockLog(iLogEntity);
		
    }   
    
    
    
}
