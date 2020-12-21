package com.mock.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

public class ResponeBase implements Serializable{

	private static final long serialVersionUID = -986166514147636258L;

	private int code;
	private Object data;
	private String msg;
	private String errMsg;

	/**
	 * 成功响应吗
	 */
	private final static int SUCCESS = 0;
	/**
	 * 默认逻辑错误响应吗,ResponeCodeException  默认错误也是这个，前端会对这个进行统一错误提示。
	 */
	private final static int ERROR = -1;
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public int getCode() {
		return code;
	}
	public ResponeBase setCode(int code) {
		this.code = code;
		return this;
	}
	public Object getData() {
		return data;
	}
	public ResponeBase setData(Object data) {
		this.data = data;
		return this;
	}
	public String getMsg() {
		return msg;
	}
	public ResponeBase setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public static ResponeBase successResult(Object data) {
		if (data instanceof PageInfo) {			
			return Page(data);
		}
		ResponeBase responeModel = new ResponeBase();
		responeModel.setData(data);
		responeModel.setMsg("success");
		responeModel.setCode(SUCCESS);
		return responeModel;
	}
	public static ResponeBase resultNull(String message) {
		ResponeBase responeModel = new ResponeBase();
		responeModel.setData(null);
		responeModel.setMsg(message);
		responeModel.setCode(SUCCESS);
		return responeModel;
	}
	  //rwm，自定义消息及数据
    public static ResponeBase failResult(String message, Object data) {
    	ResponeBase responeModel = new ResponeBase();
		responeModel.setData(data);
		responeModel.setCode(ERROR);
		responeModel.setMsg("failed");
		responeModel.setErrMsg(message);
		return responeModel;
    }
    public static ResponeBase failResult(Object data) {
    	ResponeBase responeModel = new ResponeBase();
		responeModel.setData(data);
		responeModel.setCode(ERROR);
		responeModel.setMsg("failed");
		return responeModel;
    }
	private static  ResponeBase Page(Object data) {
		PageInfo page = (PageInfo) data;
		ResponeBase responeModel = new ResponeBase();
		Pagination pagination = new Pagination();
		pagination.setTotal(new Integer(String.valueOf(page.getTotal())));
		pagination.setCurrent(page.getPageNum());		
		pagination.setPageSize(page.getPageSize());
		responeModel.setCode(SUCCESS);
		responeModel.setMsg("success");
		responeModel.setData(data);
		return responeModel;
	}
	
	@Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
