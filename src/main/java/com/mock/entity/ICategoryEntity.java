package com.mock.entity;

import lombok.Data;

@Data
public class ICategoryEntity extends BaseEntity<ICategoryEntity>{

	/**
	* 	主键
	*/
	private int Id;
	/**
	 * 模块名称
	 * */
	private String name;
}
