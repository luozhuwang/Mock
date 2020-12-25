package com.mock.entity;

import lombok.Data;

@Data
public class BasicDataEntity extends BaseEntity<BasicDataEntity>{
	private int id;
	private int associateId;
	private String value;

}
