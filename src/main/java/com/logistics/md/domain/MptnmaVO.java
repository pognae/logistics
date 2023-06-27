package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MptnmaVO {
		
		private String rowkey;	//rowkey        
		private String compkey;	//VARCHAR(20)   
		private String ptnrkey; //VARCHAR(20)   
		private String ownerky; //VARCHAR(20)    
		private String ptdelyn; //VARCHAR(1)    
		private String ptnatcd; //VARCHAR(2)   
		private String ptstpky; //VARCHAR(20)   
		private String ptnrtyp; //VARCHAR(10)   
		private String ptnamlc; //VARCHAR(60)  
		private String ptnamko; //VARCHAR(60)  
		private String ptnamen; //VARCHAR(60)  
		private String ptaddr1; //VARCHAR(100)
		private String ptaddr2; //VARCHAR(100)
		private String ptaddr3; //VARCHAR(100)  
		private int ptnrlat;
		private int ptnrlon;
		private String ptciynm; //VARCHAR(100)     
		private String ptregnm; //VARCHAR(100)   
		private String ptteln1; //VARCHAR(20)   
		private String ptteln2; //VARCHAR(20)   
		private String ptteln3; //VARCHAR(20)   
		private String pttaxcd; //VARCHAR(20)   
		private String ptposbx; //VARCHAR(10)   
		private String ptposcd; //VARCHAR(10)   
		private String ptrepnm; //VARCHAR(60)   
		private String ptreptl; //VARCHAR(20)   
		private String ptrepem; //VARCHAR(60)   
		private String ptmannm; //VARCHAR(10)   
		private String ptmantl; //VARCHAR(20) 
		private String ptmanem; //VARCHAR(60) 
		private String ptngro1; //VARCHAR(10) 
		private String ptngro2; //VARCHAR(10) 
		private String ptngro3; //VARCHAR(10) 
		private String ptinmag; //VARCHAR(60) 
		private String ptblctc; //VARCHAR(20) 
		private String credate; //VARCHAR(8) 
		private String cretime; //VARCHAR(6) 
		private String creuser; //VARCHAR(60) 
		private String lmodate; //VARCHAR(8) 
		private String lmotime; //VARCHAR(6) 
		private String lmouser; //VARCHAR(60) 
		private String indibzl;	//biz logic indicator
		private String indiarc;	//Archive indicator
		private int updtchk; //Update check
		
		
		private String oldptnrkey;
		
		// MCUSMA 
		private String custkey;
		private String cudelyn;
		private String cunatcd;
		private String custpky;
		private String custtyp;
		private String cunamlc;
		private String cunamko;
		private String cunamen;
}                         
