package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class McodemDTO {

	/*
	 * TABLE : MCODEM
	 */
	private String rowkey;	//Grid RowKey
	private String compkey; //Company Key
	private String comcdky; //Common code Key
	private String comcdvl; //Common code value
	private String comkytx; //Common key Text
	private String comcdtx; //Common code Text
	private int comcdor; //Code 표시 순서
	private String comcdsy;	//Code 시스템 전용
	private String cdcate1; //Code 분류1
	private String cdcate2; //Code 분류2
	private String cdcate3; //Code 분류3
	private String cdattr1; //Code Attribute1
	private String cdattr2; //Code Attribute2
	private String cdattr3; //Code Attribute3
	private String codelyn;	//삭제YN(Y=삭제)
	private String credate; //
	private String cretime; //
	private String creuser; //
	private String lmodate; //
	private String lmotime; //
	private String lmouser; //
	private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk;	//Update check
	
	//oldColumnData (Composite Key Column)
	private String beforecomcdky; //VARCHAR(10) Common code Key
	private String beforecomcdvl; //VARCHAR(10)	Common code value
	
}
