package com.logistics.md.domain;

import lombok.Data;

@Data
public class MskmchVO {
	//MD SKU시방서-원단 조합
	private String compkey;		//varchar(20)		Company Key
	private String ownerky;		//varchar(20)       화주
	private String skuskey;		//varchar(50)       상품시방서 SKEY
	private String skutkey;		//varchar(50)       상품시방서 TKEY
	private String skugr04;		//varchar(20)		SKUGR04
	private String itegnum;     //varchar(6)        원단 순번(원단그룹)
	private String mainnum;     //varchar(6)        주원단
	private String subinum;     //varchar(6)        부원단
	
	private String credate;		//varchar(8)        생성일자
	private String cretime;		//varchar(6)        생성시간
	private String creuser;		//varchar(60)       생성사용자
	private String lmodate;		//varchar(8)        수정일자
	private String lmotime;		//varchar(6)        수정시간
	private String lmouser;		//varchar(60)       수정사용자

	private String indibzl;		//varchar(1)			Business logic indicator
	private String indiarc;		//varchar(1)			Archive indicator
	private int	   updtchk;		//int(11)				Update check
	
}
