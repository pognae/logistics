package com.logistics.md.domain;

import lombok.Data;

@Data
public class McountVO {
	//Mcount
	private String rowkey;	//rowkey
	private String compkey;		//varchar(20) 		Company Key
	private String natnkey;		//varchar(2) 		국가코드
	private String natn2ky;		//varchar(3) 		국가코드ISO2
	private String natnlnm;		//varchar(60) 		Nationr Name Local
	private String natnknm;		//varchar(60) 		Nation Name KO
	private String natnenm;		//varchar(60) 		Nation Name EN
	private String natngr1;		//varchar(60) 		국가그룹1
	private String natngr2;		//varchar(60) 		국가그룹2
	private String natngr3;		//varchar(60) 		국가그룹3
	private String credate;		//varchar(8) 		생성일자
	private String cretime;		//varchar(6) 		생성시간
	private String creuser;		//varchar(60) 		생성사용자
	private String lmodate;		//varchar(8) 		수정일자
	private String lmotime;		//varchar(6) 		수정시간
	private String lmouser;		//varchar(60) 		수정사용자
	private String indibzl;		//varchar(1) 		biz logic indicator
	private String indiarc;		//varchar(1) 		Archive indicator
	private String updtchk;		//int(11) 			Update check
}
