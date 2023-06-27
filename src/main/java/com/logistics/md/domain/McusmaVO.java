package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class McusmaVO {
		
		private String rowkey;	//rowkey
		private String compkey;
		private String custkey;              // customer key
		private String ownerky;              // owner key
		private String cudelyn;              // customer key
		private String cunatcd;              // customer 국가 코드
		private String custpky;              // Sold to Party
		private String custtyp;              // customer type
		private String cunamlc;              // customer Name LOCAL
		private String cunamko;              // customer Name KO
		private String cunamen;              // customer Name ENG
		private String cuaddr1;              // customer 주소
		private String cuaddr2;              // customer 주소
		private String cuaddr3;               // customer * 경도
		private int custlat;
		private int custlon;
		private String cuciynm;              // customer * 도시
		private String curegnm;              // customer * 지역
		private String cuteln1;              // customer TEL
		private String cuteln2;              // customer TEL
		private String cuteln3;              // customer TEL
		private String cutaxcd;              // customer * TAX CODE
		private String cuposbx;              // customer * PO BOX
		private String cuposcd;              // customer * POSTAL CODE
		private String curepnm;              // customer 대표자 name
		private String cureptl;              // customer 대표자 tel
		private String curepem;              // customer 대표자 email
		private String cumannm;              // customer 담당자 name
		private String cumantl;              // customer 담당자 tel
		private String cumanem;              // customer key
		private String cusgro1;              // customer 그룹1
		private String cusgro2;              // customer 그룹2
		private String cusgro3;              // customer 그룹2
		private String poststm;				//영업일자변경시간
		private String sundavl;              // 요일 배송 가능 ( * 일 )
		private String mondavl;              // 요일 배송 가능 ( * 월 )
		private String tuedavl;              // 요일 배송 가능 ( * 화 )
		private String weddavl;              // 요일 배송 가능 ( * 수 )
		private String thudavl;              // 요일 배송 가능 ( * 목 )
		private String fridavl;              // 요일 배송 가능 ( * 금 )
		private String satdavl;              // 요일 배송 가능 ( * 토 )
		private String nathavl;              // 요일 배송 가능 ( * 국경일 )
		private String dlvrqtm;              // * 납품요청시간
		private String routeky;              // * 배송권역 route
		private String unlodmo;              // * 하역특이사항
		private String cuspecd;              // 고객 특이 사항 코드
		private String cusperm;              // 고객 특이 사항 내용
		private String cublctc;              // * BL customer cost center
		private String cuqrcod;              // 매장 QR코드
		private String depbank;              // 입금처 은행
		private String depacnm;              // 입금처 계좌번호
		private String mixraty;				 // 장끼 혼용률 표시방법
		private String invfoot;              // 장끼 하단 공지
		private String invhead;              // 장끼 상단 공지
		private String vipreyn;              // 미수금 관리대상 소매여부
		private String credate;
		private String cretime;
		private String creuser;
		private String lmodate;
		private String lmotime;
		private String lmouser;
		private String indibzl;	            // biz logic indicator
		private String indiarc;	            // Archive indicator
		private int updtchk;	        // Update check
		
		private String oldcustkey;
		private String ownamlc;
		
}                         
