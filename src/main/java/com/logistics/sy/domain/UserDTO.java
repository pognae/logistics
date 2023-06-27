package com.logistics.sy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    
	private String rowkey;	//Row Key
	private String compkey; //Company Key
    private String useract; //계정ID
    private String passwrd; //비밀번호
    private String usernam; //사용자이름 
    private String address; //*Address
    private String citycod; //*City
    private String rsonkey; //*Region
    private String postcod; //*Postal code
    private String natn2ky; //*국기코드
    private String telphnm; //휴대전화번호
    private String emailad; //이메일
    private String departm; //부서명
    private String emploid; //*회사 내부 사번
    private String acapdoc; //*결재문서번호
    
    private String usertyp; //사용자 타입
    private String ownerky; //Owner key
	private String ptnrkey; //Partner Key
	private String custkey; //Customer Key
	 
    private String usergr1; //User group1
    private String usergr2; //User group2
    private String usergr3; //User group3
    private String langkey; //*Language key
    private String datefmt; //*Date format
    private String timefmt; //*Time format
    private String decifmt; //*Decimal format
    private int utimzon; //*Time zone by user
    
    private String idexpdt; //계정 만료일
    private String pwexpdt; //비밀번호 만료일자
    private int pwercnt; //비번 오류횟수
    private String alocked; //계정잠금여부
    private String alockmo; //계정 Locked 사유
    
    private String rolgkey;	//권한그룹 키
    
    private String polpryn;	//개인정보동의
    private String polsvyn;	//이용약관동의여부
    private String polsmyn;	//문자수신동의
    private String polemyn;	//이메일수신동의
    private String poldate;	//서비스동의날자
    private String poltime;	//서비스동의시간
    private String approyn;	//사용자승인여부
    
    private String sotapyn;	//탈퇴요청(Y=요청)
    private String sotdate;	//탈퇴요청일자
    private String sottime;	//탈퇴요청시간
    private String ownapyn;	//화주사용자 승인여부
    private String ownapdt;	//화주사용자 승인일자
    private String ownaptm;	//화주사용자 승인시간
    private String ownapid;	//화주사용자 승인자
    
    private String themety; //메인테마타입
    private String favoryn; //VARCHAR(1)	즐겨찾기 자동Open YN
    
    private String credate; //생성일자
    private String cretime; //생성시간
    private String creuser; //생성사용자
    private String lmodate; //수정일자
    private String lmotime; //수정시간
    private String lmouser; //수정사용자
    private String indibzl;	//biz logic indicator
	private String indiarc;	//Archive indicator
	private int updtchk; //Update check
    
	// 커스텀 변수
	private String ownamlc;	//Owner Name<커스텀>
	private String ptnamlc;	//Partner Name<커스텀>
	private String cunamlc;	//Customer Name<커스텀>
	
	
}

 
 