package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MuommaVO {

	private String rowkey;
	private String compkey;	//
	private String uomekey; //VARCHAR(20) 
	private String uomenam; //VARCHAR(20) 
	private String uodelyn; //VARCHAR(10) 
	private String credate; //VARCHAR(10) 
	private String cretime; //VARCHAR(1) 
	private String creuser; //VARCHAR(1) 
	private String lmodate; //VARCHAR(1) 
	private String lmotime; //VARCHAR(1)
	private String lmouser; //VARCHAR(1)
	private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk;	//Update check
	
	private String olduomekey;
}

