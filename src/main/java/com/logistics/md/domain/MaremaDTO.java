package com.logistics.md.domain;

 

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaremaDTO {
	
	private String rowkey;	//Row Key
	private String compkey;	//Company Key
	private String warekey;	//Warehouse Key
	private String areakey;	//Area key
	private String ardelyn;	//Area Deletion YN
	private String areanam;	//Area Name
	private String areatyp;	//Area type
	private String syonlyn;	//시스템 기본 Area 여부
	private String credate;	//Creation date
	private String cretime;	//Creation time
	private String creuser;	//Created by
	private String lmodate;	//Last modified date
	private String lmotime;	//Last modified time
	private String lmouser;	//Last modified by
	private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk;	//Update check
	
	private String combovl;  // 다른 페이지에서 areakey(value) , areanam(text)으로 이루어진
	private String combonm;  // select box 가져오기 위해 추가
	
	private String oldwarekey;	//
	private String oldareakey;	//
	
	  
}
