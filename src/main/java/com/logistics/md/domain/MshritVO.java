package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MshritVO {
	
	private String rowkey;	//rowkey
	private String compkey; //Company Key
	private String shtrtky; //순회노선키
	private int shtrtit; //셔틀순서번호
	private String custkey; //Customer Key
	private String shtrpty; //보고유형
	private String credate; //생성일자
	private String cretime; //생성시간
	private String creuser; //생성사용자
	private String lmodate; //수정일자
	private String lmotime; //수정시간
	private String lmouser; //수정사용자
	private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk;	//Update check
	
	//셔틀배차계획
	private String doccate;
	private String doctype;
	private String repitdt;
	private String repittm;
	private String rsncode;
	private String rsnremk;
	
	private String vhplnky;
	private String vhplnit;
	private String vhcfnam; 
}
