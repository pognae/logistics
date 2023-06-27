package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class McuswhVO {
	
	private String rowkey;	//rowkey
	private String compkey; 
	private String custkey; //VARCHAR(20) customer key
	private String mngwhky; //VARCHAR(20) 관리창고
	private String defwhyn; //VARCHAR(1) 기본창고 여부
	private String credate;
	private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;
	private String indiarc;
	private int updtchk;
	
	private String oldCustkey;
	private String oldMngwhky;
}
