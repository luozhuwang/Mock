package com.mock.entity;

import lombok.Data;

@Data
public class ILogEntity extends BaseEntity<ILogEntity>{

	private static final long serialVersionUID = 216622460157536782L;

	private Long logId;
	
	/**
	 *  请求url
     */
    private String requestUrl;
    /**
	 *  模块ID
     */
    private int categoryId;
    /**
	 *  命中url
     */
    private String hitUrl;
    /**
     * * 请求IP
     */
    private String requestIp;
    /**
     * 	请求方法
     */
    private String requestMethod;
    /**
     * 	请求头
     */
    private String requestHeader;
    /**
     * * 请求详细
     */
    private String requestDetail;

    /**
     * * 响应详细
     */
    private String responseDetail;
    /**
     * 	响应头
     */
    private String responseHeader;


}
