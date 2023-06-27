package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MskuurVO {
	
	/**
	 * TABLE : MSKUUR
	 **/
	
	private String compkey;
	private String warekey;
	private String ownerky;
	private String skumkey;
	private String useract;
	private String usrat01;	//관심상품여부
	private String usrat02;	//세일상품여부
	private String usrat03;
	private String sudelyn;
	private String credate;
	private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;
	private String indiarc;
	private int updtchk;
	
	// 커스텀
	private String skudesc;
	private String skugr04;
	private int stotqty;
	private int selpric;
	
}
