package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MskuwcDTO {

	/**
	 * TABLE : MSKUWC
	 **/

	private String rowkey;		//			 		Grid RowKey
	private String compkey; 	//VARCHAR(20) 		Company Key
	private String warekey; 	//VARCHAR(20)       창고키
	private String ownerky; 	//VARCHAR(20)       화주
	private String skumkey; 	//VARCHAR(50)       상품코드
	private String skudesc; 	//VARCHAR(100)      상품명
	private String skutkey; 	//VARCHAR(50)       상품작업지시 TKEY 
	private String skuskey; 	//VARCHAR(50)       상품작업지시 SKEY
	private String skutdsc;		//VARCHAR(100)      대표상품명
	private String designe; 	//VARCHAR(50)       디자이너
	private String skutype; 	//VARCHAR(10)       *상품유형 
	private String vendkey; 	//VARCHAR(20)       공장 
	private int vndfday; 	//DECIMAL(10,3)     생산소요일,
	private String truntyp;		//VARCHAR(10)		이동용기타입
	private String packkey; 	//VARCHAR(10)       포장 키
	private String eancode; 	//VARCHAR(20)       *EAN 코드
	private String upccode; 	//VARCHAR(20)       *UPC 코드
	private String brandcd; 	//VARCHAR(20)       브랜드
	private String skugr01; 	//VARCHAR(20)       SKU Group 1 
	private String skugr02; 	//VARCHAR(20)       SKU Group 2 Season 
	private String skugr03; 	//VARCHAR(20)       SKU Group 3 Size 
	private String skugr04; 	//VARCHAR(20)       SKU Group 4 Color
	private String skugr05; 	//VARCHAR(20)       SKU Group 5 원단종류 
	private String skualt1; 	//VARCHAR(50)       SKU Alternative1 
	private String skualt2; 	//VARCHAR(50)       SKU Alternative2 
	private String skualt3; 	//VARCHAR(50)       SKU Alternative3 
	private String skuat01; 	//VARCHAR(10)       SKU Attribute1 디자이너 
	private String skuat02; 	//VARCHAR(10)       SKU Attribute2 
	private String skuat03; 	//VARCHAR(10)       SKU Attribute3 
	private String skuat04; 	//VARCHAR(10)       SKU Attribute4 
	private String skuat05; 	//VARCHAR(10)       SKU Attribute5 
	private Double sgrweig; 	//DECIMAL(10,3)     Gross weight,
	private Double sneweig; 	//DECIMAL(10,3)     Net weight,
	private Double skuleng; 	//DECIMAL(10,3)     Length,
	private Double skuwidh; 	//DECIMAL(10,3)     Width,
	private Double skuheig; 	//DECIMAL(10,3)     Height,
	private Double skucubi; 	//DECIMAL(10,3)     Cubic meter,
	private String suomkey; 	//VARCHAR(10)       Default unit of measure 
	private String skuwabc; 	//VARCHAR(10)       WH SKU ABC analyzed value 
	private String skustat; 	//VARCHAR(20)       *SKU상태 
	private String sputzon; 	//VARCHAR(10)       *기본 적치 Zone 
	private String sputloc; 	//VARCHAR(10)       *기본 적치 Location
	private String skuimg1;		//VARCHAR(100)		상품이미지1
	private String skuimg2;		//VARCHAR(100)		상품이미지2
	private String skuimg3;		//VARCHAR(100)		상품이미지3
	private String skuimg4;		//VARCHAR(100)		상품이미지4
	private String skuimg5;		//VARCHAR(100)		상품이미지5
	private int itmcost;		//INT(11)			단가
	private int selpric;		//INT(11)			판매단가
	
	private int sproprc;		//INT(11)			Main공장 공임비
	private String skunote;		//VARCHAR(100)		Main공장 비고
	private String retexky;		//VARCHAR(20)		제작의뢰고객	
	private String skudcex;		//VARCHAR(1)		DC제외 체크			
	private int cuspric;		//INT(11)			도매특가
	private int rtspric;		//INT(11)			소매특가
	
	
	private String skudlyn;		//VARCHAR(1)		상품 삭제 여부
	private String credate; 	//VARCHAR(8)        생성일자 
	private String cretime; 	//VARCHAR(6)        생성시간 
	private String creuser; 	//VARCHAR(60)       생성사용자 
	
	private int roinqty;		//1절수량
	private int miniqty;		//최소발주수량
	private String mixrato;		//VARCHAR(100)		혼용율
	private String spronte;		//VARCHAR(100)		Main공장 비고
	private String itemnt1;		//VARCHAR(100)		상품비고1
	private String itemnt2;		//VARCHAR(100)		상품비고2
	
	private String tollvd1;		//VARCHAR(20)       임가공 공장1
	private String tollvd2;		//VARCHAR(20)       임가공 공장2
	private String tollvd3;		//VARCHAR(20)       임가공 공장3
	private String tollvd4;		//VARCHAR(20)       임가공 공장4

	private int    tollfe1;		//INT(11)			임가공 공임비1
	private int    tollfe2;		//INT(11)			임가공 공임비2
	private int    tollfe3;		//INT(11)			임가공 공임비3
	private int    tollfe4;		//INT(11)			임가공 공임비4

	private String tollnt1;		//VARCHAR(100)      임가공 비고1
	private String tollnt2;		//VARCHAR(100)      임가공 비고2
	private String tollnt3;		//VARCHAR(100)      임가공 비고3
	private String tollnt4;		//VARCHAR(100)      임가공 비고4
	
	private String lmodate; 	//VARCHAR(8)        수정일자 
	private String lmotime; 	//VARCHAR(6)        수정시간 
	private String lmouser; 	//VARCHAR(60)       수정사용자
	private String indibzl;		//biz logic indicator
	private String indiarc;		//Archive indicator
	private int updtchk;		//Update check
	
	private String comcdnm;		// 다른 페이지에서 WAREKEY(value) , SKUAT01(text)으로 이루어진
	private String comcdvl;		// select box 가져오기 위해 추가 
	
	//================
	private String skuimg1del;	//VARCHAR(100)		상품이미지1 Delete 여부
	private String skuimg2del;	//VARCHAR(100)		상품이미지2 Delete 여부
	private String skuimg3del;	//VARCHAR(100)		상품이미지3 Delete 여부
	private String skuimg4del;	//VARCHAR(100)		상품이미지4 Delete 여부
	private String skuimg5del;	//VARCHAR(100)		상품이미지5 Delete 여부
	
	private String stkdate;
	private String stktime;
	
	
	//MSKUUR
	private String usrat01;		//VARCHAR(20)
	private String usrat02;		//VARCHAR(20)
	private String usrat03;		//VARCHAR(20)
}