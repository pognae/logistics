package com.logistics.sy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SprthiVO {
	
	private String compkey;  	//Company Key
	private String printsq;  	//인쇄이력번호
	private String warekey;  	//Warehouse Key
	private String progrid;  	//프로그램ID
	private String eoasnky;  	//ASN No
	private int eoasnit; 		//ASN item number
	private String rcvdcky;  	//입고문서 번호
	private int rcvdcit; 		//입고문서 아이템
	private String taskoky;  	//Task Key
	private int taskoit; 		//Task Item Key
	private String adjsoky;  	//조정문서 번호
	private int adjsoit; 		//조정문서 Item Key
	private String physoky;  	//실사문서 번호
	private int physoit; 		//실사문서 Item Key
	private String shpdcky;  	//출고문서 번호
	private int shpdcit; 		//출고문서 아이템
	private String allgrky;		//할당그룹키
	private String copodky;  	//ref ERP PO
	private String copodit; 	//ref ERP PO Item
	private String refsodc;  	//주문장끼번호
	private String refsoit; 	//주문아이템번호
	private String refdndc;  	//ref ERP DN
	private String refdnit; 	//ref ERP DN Item
	private String refsddc;  	//ref ERP SD
	private String refsdit; 	//ref ERP SD Item
	private String invokey;		//거래명세서 템플릿 키
	private String prtdate;  	//인쇄일자
	private String prttime;  	//인쇄시간
	private String prtuser;  	//인쇄사용자
	private String credate; //
	private String cretime; //
	private String creuser; //
	private String lmodate; //
	private String lmotime; //
	private String lmouser; //
	private String indibzl;  //biz logic indicator
	private String indiarc;  //Archive indicator
	private int updtchk; //Update check
}
