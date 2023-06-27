package com.logistics.md.domain;

import lombok.Data;

@Data
public class MskuclVO {
	//MD SKU시방서-컬러
	private String compkey;		//varchar(20)	Company Key	
	private String ownerky;		//varchar(20)   화주
	private String skuskey;		//varchar(50)   상품시방서 KEY
	private String skutkey;		//varchar(50)   상품코드앞자리 사이즈색상제외키
	private String skugr04;		//varchar(20)   SKU Group 4 Color
	private String txtgr04;		//varchar(100)  SKU Group 4 Color 코멘트
	private String credate;		//varchar(8)    생성일자
	private String cretime;		//varchar(6)    생성시간
	private String creuser;		//varchar(60)   생성사용자
	private String lmodate;		//varchar(8)    수정일자
	private String lmotime;		//varchar(6)    수정시간
	private String lmouser;		//varchar(60)   수정사용자
	
	private String indibzl;		//varchar(1)			Business logic indicator
	private String indiarc;		//varchar(1)			Archive indicator
	private int	   updtchk;		//int(11)				Update check
	
	private String beforeskugr04;//varchar(20)   SKU Group 4 Color
	
}
