package com.logistics.md.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MvhcmaVO {
	
	private String rowkey;	//rowkey
	private String compkey; //Company Key
	private String vehicky; //차량코드
	private String vhdelyn; //삭제YN
	private String vhcfnam; //차량번호
	private String dlvtype; //차량운행타입
	private String carrier; //운송사코드
	private String useract; //기사 계정ID
	private String vhcopty; //차량운영형태
	private String vhctype; //차량종류
	private String vhctncd; //차량톤수
	private int maxwegt; //최대중량
	private int maxcapa; //최대적재 CBM
	private String credate; //생성일자
	private String cretime; //생성시간
	private String creuser; //생성사용자
	private String lmodate; //수정일자
	private String lmotime; //수정시간
	private String lmouser; //수정사용자
	private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk;	//Update check
	
	private String oldvehicky;
		
	private String combovl;
	private String combonm;
}
