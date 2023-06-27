package com.logistics.md.domain;

import lombok.Data;

@Data
public class MskcstVO {
	//MD SKU시방서-상세정보(원가계산)
	private String compkey;		//varchar(20)		Company Key
	private String ownerky;     //varchar(20)       화주
	private String skuskey;     //varchar(50)       상품시방서 KEY
	private String skutkey;     //varchar(50)       상품코드앞자리 사이즈색상제외키
	private String skumkey;     //varchar(50)       상품코드앞자리 사이즈색상제외키
	private String skugr04;     //varchar(20)       SKU Group 4 Color
	private String itegnum;     //varchar(6)        원단 순번(원단그룹)
	private String itemnum;     //varchar(6)        원단 순번
	private String itemdiv;     //varchar(30)     	원단 구분(주원단 부원단)
	private String sizecod;     //varchar(6)        사이즈
	private String skucolo;     //varchar(20)       원단 상품Color
	private String fabcolo;     //varchar(20)       원단 공정색코드
	private int	   roinqty;     //int(11)		    1절장수
	private String consnum;     //varchar(10)       요척(yd)
	private int	   conspri;     //int(11)		    요척 단가
	private int	   submtps;     //int(11)		    부자재합계
	private int	   coslabs;     //int(11)		    공임비합계
	private int	   itmcost;     //int(11)		    원가합계
	private int	   selpric;     //int(11)		    판매가
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
