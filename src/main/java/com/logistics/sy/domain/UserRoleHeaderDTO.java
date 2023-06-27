package com.logistics.sy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRoleHeaderDTO {
	
	private String rowkey;	//Row Key
	private String compkey;	//VARCHAR2(20)	Not Null	' '  	Company Key
	private String rolekey;	//VARCHAR2(20)	Not Null	' '  	Role Key
	private String rolenam; //VARCHAR2(60)	Not Null	' '  	Role Name 
	private String credate; //VARCHAR(8)	Creation date
    private String cretime; //VARCHAR(6)	Creation time
    private String creuser; //VARCHAR(20)	Created by
    private String lmodate; //VARCHAR(8)	Last modified date
    private String lmotime; //VARCHAR(6)	Last modified time
    private String lmouser; //VARCHAR(20)	Last modified by
    private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk; //Update check
	
}
