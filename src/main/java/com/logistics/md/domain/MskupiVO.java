package com.logistics.md.domain;

import lombok.Data;

@Data
public class MskupiVO {
	//MD SKU시방서-공정
	private String compkey;		//varchar(20)		Company Key
	private String ownerky;		//varchar(20)       화주
	private String skuskey;		//varchar(50)       상품시방서 SKEY
	private String skutkey;		//varchar(50)       상품시방서 TKEY
	private String itegnum;		//varchar(6)        원단 순번
	private String itemnum;		//varchar(6)        원단 순번
	private String itemdiv;		//varchar(30)		구분(	주원단 부원단)
	private String sprocno;		//varchar(6)        공정 순번
	private String spricol;		//varchar(100)      공정 주컬러
	private String sprfcol;		//varchar(100)      공정 원단코드
	private String spritxt;		//varchar(100)      작업 이미지 타이틀
	private String spriimg;		//varchar(100)      작업 이미지 경로
	private String sprides;		//varchar(1000)     작업 설명
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
