package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MzonmaVO {
	
	private String rowkey;	//rowkey
	private String compkey; //Company Key
	private String warekey; //Warehouse Key
	private String whnamlc;	//Warehouse Name<커스텀>
	private String areakey; //Area key
	private String areanam;	//Area Name<커스텀>
	private String zonekey; //Zone Key
	private String zodelyn;
	private String zonenam;
	private String zonetyp; //VARCHAR(10)
	
	private int floorty;
	private String putawyn; //VARCHAR(1) 
	private String allocyn; //VARCHAR(1) 
	private String miqtyyn;
	private String syonlyn; //VARCHAR(1) 
	private String moveoyn; //VARCHAR(1)
	private String moveiyn; //VARCHAR(1)
	private String credate;
	private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk;	//Update check
	
	private String oldwarekey;
	private String oldareakey;
	private String oldzonekey;
	
}
