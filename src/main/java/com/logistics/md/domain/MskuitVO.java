package com.logistics.md.domain;

import lombok.Data;

@Data
public class MskuitVO {
	//MD SKU시방서-원단정보
	private String compkey;		//varchar(20)		Company Key
	private String ownerky;     //varchar(20)       화주
	private String skuskey;     //varchar(50)       상품시방서 KEY
	private String skutkey;     //varchar(50)       상품코드앞자리 사이즈색상제외키
	private String skugr04;     //varchar(20)       SKU Group 4 Color
	private String itegnum;     //varchar(6)        원단 순번(원단그룹)
	private String itemnum;     //varchar(6)        원단 순번
	private String itemdiv;     //varchar(30)     	원단 구분(주원단 부원단)
	private String itemnam;     //varchar(1000)     원단 명
	private String itemfac;     //varchar(100)      원단처
	private String mixrato;     //varchar(100)      혼용률
	private String pttitle;     //varchar(100)      패턴 제목
	private String fabcolo;     //varchar(10)       원단 공정색코드
	private String skucolo;     //varchar(10)       원단 상품Color
	private int	   fabpric;     //int(11)    		원단 가격
	private int	   itmcost;     //int(11)     		원단 가격
	private String fabimph;     //varchar(100)      원단 이미지경로
	private String fabimnm;     //varchar(100)      원단 이미지파일명
	private String credate;     //varchar(8)        생성일자
	private String cretime;     //varchar(6)        생성시간
	private String creuser;     //varchar(60)       생성사용자
	private String lmodate;     //varchar(8)        수정일자
	private String lmotime;     //varchar(6)        수정시간
	private String lmouser;     //varchar(60)       수정사용자

	private String indibzl;		//varchar(1)			Business logic indicator
	private String indiarc;		//varchar(1)			Archive indicator
	private int	   updtchk;		//int(11)				Update check
	
}
