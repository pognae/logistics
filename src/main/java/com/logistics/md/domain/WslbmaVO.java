package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WslbmaVO {
	private String compkey;		//VARCHAR(20)	
	private String paklbky;		//VARCHAR(250) 포장 바코드키
	private String warekey;		//VARCHAR(20)
	private String paklbnm;		//VARCHAR(1000) 라벨 타이틀
	private String custkey;		//VARCHAR(20)
	private String cunamlc;		//VARCHAR(60)
	private String retakey;		//VARCHAR(20)
	private String retainm;		//VARCHAR(60)
	private String credate;		//VARCHAR(8)
	private String cretime;		//VARCHAR(6)
	private String creuser;		//VARCHAR(60)
	private String lmodate;		//VARCHAR(8)
	private String lmotime;		//VARCHAR(6)
	private String lmouser;		//VARCHAR(60)
	private String indibzl;		//VARCHAR(1)
	private String indiarc;		//VARCHAR(1)
	private int updtchk;		//INT(11)
	
	private int level;		//라벨 개수
}
