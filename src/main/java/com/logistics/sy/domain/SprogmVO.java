package com.logistics.sy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SprogmVO {
	
	private String rowkey;	//Row Key
	private String compkey; //Company Key
	private String progrid; //프로그램ID
	private String progrnm; //프로그램명
	private String proglky; //프로그램명 라벨키
	private String progcmd; //프로그램 실행 경로
	private String whrolyn; //창고별 권한관리 여부
	private String credate; //
	private String cretime; //
	private String creuser; //
	private String lmodate; //
	private String lmotime; //
	private String lmouser; //
	private String indibzl; //
	private String indiarc; //
	private int updtchk; //
	
	private String oldprogrid;
}
