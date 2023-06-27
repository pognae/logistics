package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MshrhdVO {
	
	private String rowkey;	//rowkey
	private String compkey; //Company Key
	private String shtrtky; //순회노선키
	private String shtrtnm; //순회노선명
	private int shtcytm; //셔틀운행주기
	private String credate; //생성일자
	private String cretime; //생성시간
	private String creuser; //생성사용자
	private String lmodate; //수정일자
	private String lmotime; //수정시간
	private String lmouser; //수정사용자
	private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk;	//Update check

	private String combonm;
	private String combovl;
}
