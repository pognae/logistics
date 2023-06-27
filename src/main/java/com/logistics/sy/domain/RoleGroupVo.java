package com.logistics.sy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleGroupVo {
    
	private String rowkey;	//Row Key
	private String compkey; //VARCHAR(20)	Company Key
	private String rolgkey;	//VARCHAR(20)	권한그룹 키
	private String rolgnam;	//VARCHAR(60)	권한그룹 명
	private String rolekey;	//VARCHAR(20)	Role Key
	private String menukey;	//VARCHAR(20)	Menu Key
    private String credate; //VARCHAR(8)	Creation date
    private String cretime; //VARCHAR(6)	Creation time
    private String creuser; //VARCHAR(20)	Created by
    private String lmodate; //VARCHAR(8)	Last modified date
    private String lmotime; //VARCHAR(6)	Last modified time
    private String lmouser; //VARCHAR(20)	Last modified by
    private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk;	//Update check
    
}
