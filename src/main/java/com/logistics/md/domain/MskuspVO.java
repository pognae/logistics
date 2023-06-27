package com.logistics.md.domain;

import lombok.Data;

@Data
public class MskuspVO {
	//MD SKU 시방서
	private String rowkey;			//ROWKEY
	private String compkey; 		//varchar(20) 			Company Key
	private String ownerky; 		//varchar(20) 			화주
	private String skuskey; 		//varchar(50) 			상품시방서 KEY
	private String spdelyn; 		//varchar(1) 			Deletion YN
	private String fworddt; 		//varchar(8) 			작성날자
	private String brandnm; 		//varchar(100) 			브랜드
	private String skudesc; 		//varchar(100) 			품명
	private String stryear; 		//varchar(4) 			출시년도
	private String seasona; 		//varchar(10) 			시즌
	private String designe; 		//varchar(60) 			디자이너
	private String skutkey; 		//varchar(50) 			상품코드앞자리 사이즈색상제외키
	private String skutdes; 		//varchar(100) 			상품명(임시)
	private String vendkey; 		//varchar(20) 			메인공장
	private int	   poreddt; 		//int(11) 				작업소요일
	private String skuifrk; 		//varchar(1000) 		주요사항 내용
	private String modifrk; 		//varchar(1000) 		수정사항 내용
	private String foundrk; 		//varchar(1000) 		재단 주의사항
	private String sksizes; 		//varchar(100) 			사이즈 S/M/L/F/SS 등 
	private String skuupdn; 		//varchar(2) 			상의UP하의DN UP/DN
	private String prtimgv; 		//varchar(100) 			인쇄 메인이미지
	private String spcimg1; 		//varchar(300) 			작업 메인이미지 경로1
	private String spcimg2; 		//varchar(300) 			작업 메인이미지 경로2
	private String spcimg3; 		//varchar(300) 			작업 메인이미지 경로3
	private String spcimg4; 		//varchar(300) 			작업 메인이미지 경로4
	private String spcimg5; 		//varchar(300) 			작업 메인이미지 경로5
	private String spcimt1; 		//varchar(100) 			작업 메인이미지 Title 1
	private String spcimt2; 		//varchar(100) 			작업 메인이미지 Title 2
	private String spcimt3; 		//varchar(100) 			작업 메인이미지 Title 3
	private String spcimt4; 		//varchar(100) 			작업 메인이미지 Title 4
	private String spcimt5; 		//varchar(100) 			작업 메인이미지 Title 5
	private String sitimgv; 		//varchar(100) 			원단 메인이미지
	private String sitimgm; 		//varchar(300) 			원단 메인이미지 경로1
	private String sitimtm; 		//varchar(100) 			원단 메인이미지 Title 1
	private int    itmcost; 		//int(11) 				원가 합계
	private int	   selpric; 		//int(11) 				판매가
	private String skuborn;			//varchar(6)			SKU마스터 생성여부
	private String credate; 		//varchar(8) 			생성일자
	private String cretime; 		//varchar(6) 			생성시간
	private String creuser; 		//varchar(60) 			생성사용자
	private String lmodate; 		//varchar(8) 			수정일자
	private String lmotime; 		//varchar(6) 			수정시간
	private String lmouser; 		//varchar(60) 			수정사용자
	
	private String indibzl;			//varchar(1)			Business logic indicator
	private String indiarc;			//varchar(1)			Archive indicator
	private int	   updtchk;			//int(11)				Update check
	
	//MSKUIT
	private String itemnam;     	//varchar(1000)     원단명
	private String itemfac;     	//varchar(100)      원단처
	private String mixrato;     	//varchar(100)      혼용률
	
	
	//MPTNMA
	private String ptnamlc;			//varchar(60)		작업공장명
	
	//MSKUPC
	private String sprofac;			//varchar(20)       작업공장
	private int	   sproprc;			//int(11)	       공임
	private	String sprocno;
}
