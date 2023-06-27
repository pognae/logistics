package com.logistics.sy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SloghiVO {
	
	private String compkey; //Company Key
	private String loginsq; //접속일련번호
	private String useract; //User Account ID
	private String usernam; //사용자이름<커스텀>
	private String warekey; //Warehouse Key
	private String logindt; //접속일자
	private String logintm; //접속시간
	private String loginip; //접속IP  
	private String secchua; //브라우져
	private String secchmb; //모바일
	private String navplat; //접솝브라우져 및 OS 정보
	private String secchpf; //platform OS
	private String haccept; //http header accept
	private String navvers; //접속브라우져 정보 
	private String loginsf; //접속성공실패여부
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
