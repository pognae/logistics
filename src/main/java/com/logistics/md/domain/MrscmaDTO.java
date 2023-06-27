package com.logistics.md.domain;

 
import lombok.Data;

@Data
public class MrscmaDTO {
	
	/**
	 * TABLE : MRSCMA, MCODEM
	 **/
	
	//MRSCMA
	private String rowkey; //Grid RowKey
	private String compkey; //VARCHAR(20)   Company Key
	private String warekey; //VARCHAR(10)   MODULE -> WAREKEY 로 변경됨 2022.07.06
	private String doccate; //VARCHAR(10)   문서유형
	private String doctype; //VARCHAR(10)   문서타입
	private String rsncode; //VARCHAR(10)	사유코드
	private String rsncdnm; //VARCHAR(10)   사유코드명
	private String rsnmlky; //VARCHAR(10)   사유코드명 다국어키
	private Double rsncdod; //DECIMAL(10,3) 사유코드 표시 순서 
	private String rscate1; //VARCHAR(10) 	사유분류1
	private String rscate2; //VARCHAR(10)   사유분류2
	private String rscate3; //VARCHAR(10) 	사유분류3
	private String rsattr1; //VARCHAR(10) 	사유속성1
	private String rsattr2; //VARCHAR(10) 	사유속성2
	private String rsattr3; //VARCHAR(10)	사유속성3
	private String credate; //VARCHAR(8)
	private String cretime; //VARCHAR(6)
	private String creuser; //VARCHAR(20)
	private String lmodate; //VARCHAR(8) 
	private String lmotime; //VARCHAR(6) 
	private String lmouser; //VARCHAR(20)
	
	//MCODEM
	private String comcdtx; //VARCHAR(100)	Common code value Text
	private String docctnm; //VARCHAR(60) 	Document category  name
	private String doctynm; //VARCHAR(60)	Document type Name
	private String docctcd;
	private String doctycd; 
	
	//oldColumnData (Composite Key Column)
	private String beforewarekey; //VARCHAR(10)   	MODULE
	private String beforersncode; //VARCHAR(10)	사유코드
	
	private String combovl;
	private String combonm;
	
}
