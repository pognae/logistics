package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MtutmaVO {
	
	private String rowkey;	//rowkey
	private String compkey; //Company Key 
	private String truntyp; //이동용기타입 
	private String trunnam; //이동용기명칭 
	private String trdelyn; //삭제YN 
	private int tutleng; //길이 
	private int tutwidh; //폭
	private int tutheig; //높이
	private int tutcubi; //입방미터
	private int tutweig; //중량
	private String uomekey; //중량단위
	private String credate; //생성일자
	private String cretime; //생성시간
	private String creuser; //생성사용자
	private String lmodate; //수정일자
	private String lmotime; //수정시간
	private String lmouser; //수정사용자
	private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk;	//Update check
	
	private String oldtruntyp;

}
