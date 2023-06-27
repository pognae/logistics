package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MpakmaVO {
	
	private String rowkey;	//rowkey
	private String compkey; //Company Key 
	private String warekey; //창고
	private String packkey; //포장키
	private String truntyp; //이동용기타입
	private String pkdelyn; //삭제YN
	private Double packqty; //입수량
	private String puomkey; //단위
	private String credate; //생성일자
	private String cretime; //생성시간
	private String creuser; //생성사용자
	private String lmodate; //수정일자
	private String lmotime; //수정시간
	private String lmouser; //수정사용자
	private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk;	//Update check
	
	private String oldwarekey;
	private String oldpackkey;
	private String oldtruntyp;

}
