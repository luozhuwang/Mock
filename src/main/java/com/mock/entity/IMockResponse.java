package com.mock.entity;

import lombok.Data;

@Data
public class IMockResponse {

	private int statusCode;
	
	private String customHeader;		
	
	private String Content;
}
