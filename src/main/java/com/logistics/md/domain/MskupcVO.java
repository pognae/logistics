package com.logistics.md.domain;

import lombok.Data;

@Data
public class MskupcVO {
	//MD SKU시방서-공정
	private String compkey;		//varchar(20)		Company Key
	private String ownerky;		//varchar(20)       화주
	private String skuskey;		//varchar(50)       상품시방서 SKEY
	private String skutkey;		//varchar(50)       상품시방서 TKEY
	private String itegnum;     //varchar(6)        원단 순번(원단그룹)
	private String itemnum;     //varchar(6)        원단 순번
	private String itemdiv;     //varchar(30)     	원단 구분(주원단 부원단)
	private String sprocno;		//varchar(6)        공정 순번
	private String sprotyp;		//varchar(20)       공정 구분 (나염/자수...)
	private String spronam;		//varchar(60)       공정 명
	private int	   sproprc;		//int(11)			공정 공임비
	private String sprofac;		//varchar(20)       작업공장
	private int    sproday;		//int(11)			소요일
	private String sproetc;		//varchar(100)      기타
	private String sprdosu;		//varchar(20)       도수
	private String sprosiz;		//varchar(100)      작업사이즈 정보 가로세로
	private String sproreq;		//varchar(1000)     요청사항
	private String spxytxt;		//varchar(100)      위치 코멘트
	private String spxyimg;		//varchar(100)      위치 이미지
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
