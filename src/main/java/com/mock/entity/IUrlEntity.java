package com.mock.entity;


import lombok.Data;


@Data
public class IUrlEntity extends BaseEntity<IUrlEntity>{

	private static final long serialVersionUID = -874642557177830153L;
	/**
	  *responseId	主键
	*/
	private int Id;
	/**
    * URL id	主键
     */
    private int urlId;
    /**
     * 模块ID
     * */
    private int categoryId;
    /**
     * url路径，path传参位置用{path}占位
     */
    private String url;

    /**
     * url名称
     */
    private String name;
    /**
     * 请求方式:GET/POST/PUT/DEL
     */
    private String requestMethod;
    /**
     * 参数类型：0:None(默认)、1:x-www-form-urlencoded、2:json、3:raw
     * */
    private int paramType;
    
    /**
     * 请求参数
     * */
    private String requestParam;

    /**
     * 请求头
     * */
    private String requestHeader;
    /**
     * 返回体
     */
    private String content;
    /**
     * 延迟
     * */
    private Integer delay;

    /**
     * 返回http状态码
     */
    private Integer statusCode;
    /**
     * 响应头
     * */
    private String responseHeader;
    /**
     * 描述
     */
    private String description;

 
}
