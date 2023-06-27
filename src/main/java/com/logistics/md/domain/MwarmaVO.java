package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MwarmaVO {
	
	private String rowkey;
	private String compkey;
	private String warekey;	   // VARCHAR(20) 창고키
	private String whdelyn;    // VARCHAR(1) 창고삭제여부
	private String whnamlc;	   // VARCHAR(60) 창고명칭 (LOCAL)
	private String whnamko;    // VARCHAR(60) 창고명칭 (KO)
	private String whnamen;	   // VARCHAR(60) 창고명칭 (EN)
	private String whaddr1;    // VARCHAR(100) 주소01 EX) 서울시
	private String whaddr2;    // VARCHAR(100) 주소02 EX) 중구 동대문로
	private String whaddr3;    // VARCHAR(100) 주소03 EX) ~층 ~호
	private String wacitnm;    // VARCHAR(100) City	
	private String waregnm;    // VARCHAR(100) Region
	private String wateln1;    // VARCHAR(20) 전화번호01
	private String wateln2;    // VARCHAR(20) 전화번호02
	private String wateln3;    // VARCHAR(20) 전화번호03
	private int warelat;	   // DECIMAL(15,10) 위도	
	private int warelon;    // DECIMAL(15,10) 경도
	private String wataxcd;    // VARCHAR(20) 창고삭제여부
	private String waposbx;    // VARCHAR(10) PO BOX
	private String waposcd;    // VARCHAR(10) POSTAL CODE
	private String warepnm;    // VARCHAR(60) 대표자명
	private String wareptl;    // VARCHAR(20) 대표자 전화번호
	private String warepem;    // VARCHAR(60) 대표자 EMAIL
	private String wamannm;    // VARCHAR(60) 담당자 이름
	private String wamantl;    // VARCHAR(20) 담당자 연락처
	private String wamanem;    // VARCHAR(20) 담당자 EMAIL
	private String overall;    // VARCHAR(1) Over Allocation 사용여부
	private String overloc;    // VARCHAR(10) Over Allocation Location
	private String shpcmul;    // VARCHAR(1) 출고완료 작업확인
	private String inbokey;    // VARCHAR(10) 입고오더 우선순위 기본키
	private String oubokey;    // VARCHAR(10) 출고오더 우선순위 기본키
	private int watarea;	   // INT(11) 총면적 m3	
	private int wascapa;	   // INT(11) 최대보관 CAPA
	private int watmzon;	   // INT(11) Time zone by warehouse
	private String credate;   
	private String cretime;   
	private String creuser;   
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;		// VARCHAR(1) biz logic indicator
	private String indiarc;		// VARCHAR(1) Archive indicator
	private int updtchk;		// INT(11) Update check
	
	// ComboBox
	private String combovl;
	private String combonm;
	
	private String useract;
	private String oldwarekey;
}
