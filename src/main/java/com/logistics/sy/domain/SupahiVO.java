package com.logistics.sy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupahiVO {
	
	private String compkey; //Company Key
	private String usrpgsq; //프로그램사용이력일련번호
	private String useract; //User Account ID
	private String progrid; //프로그램ID
	private String menunam; //메뉴명
	private String themety; //Main테마타입
	private String acclgdt; //접속로그생성일자
	private String acclgtm; //접속로그생성시간
	private String credate; //
	private String cretime; //
	private String creuser; //
	private String lmodate; //
	private String lmotime; //
	private String lmouser; //
	private String indibzl; //
	private String indiarc; //
	private int updtchk; //
}
