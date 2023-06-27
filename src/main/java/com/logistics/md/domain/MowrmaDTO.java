package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MowrmaDTO {
	
	private String rowkey;	//rowkey       
	private String compkey; //VARCHAR(20)  
	private String ownerky; //VARCHAR(20)  
	private String owdelyn; //VARCHAR(1)  
	private String ownatcd; //VARCHAR(2)  
	private String ownamlc; //VARCHAR(60)  
	private String ownamko; //VARCHAR(60)   
	private String ownamen; //VARCHAR(60)   
	private String owaddr1; //VARCHAR(100)   
	private String owaddr2; //VARCHAR(100)   
	private String owaddr3; //VARCHAR(100)    
	private String owciynm; //VARCHAR(100)   
	private String owregnm; //VARCHAR(100)   
	private String owteln1; //VARCHAR(20)   
	private String owteln2; //VARCHAR(20) 
	private String owteln3; //VARCHAR(20) 
	private String owtaxcd; //VARCHAR(20) 
	private String owposbx; //VARCHAR(10) 
	private String owposcd; //VARCHAR(10) 
	private String owrepnm; //VARCHAR(60) 
	private String owreptl; //VARCHAR(20) 
	private String owrepem; //VARCHAR(60) 
	private String owmannm; //VARCHAR(60) 
	private String owmantl; //VARCHAR(20) 
	private String owmanem; //VARCHAR(60) 
	private String owblctc; //VARCHAR(20) 
	private String credate;
    private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;	 //biz logic indicator
	private String indiarc;	 //Archive indicator
	private int updtchk; //Update check
	private int ownelat;	   // DECIMAL(15,10) 위도	
	private int ownelon;    // DECIMAL(15,10) 경도
	private String oldownerky;
	
}