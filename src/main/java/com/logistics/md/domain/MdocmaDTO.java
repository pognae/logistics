package com.logistics.md.domain;

 
import lombok.Data;

@Data
public class MdocmaDTO {
	
	/*
	 * TABLE : MDOCMA
	 */
	private String compkey; //VARCHAR(20)   Company Key
	private String doccate; //VARCHAR(10)   Document category
	private String doctype; //VARCHAR(10)   Document type
	private String docctnm; //VARCHAR(60)   Document category  name
	private String doctynm; //VARCHAR(60)	Document type Name
	private String mvtgloc; //VARCHAR(10)   기본 목적지 로케이션
	private String receloc; //VARCHAR(10)   기본 입하로케이션
	private String doctlky; //DECIMAL(20) 	다국어키
	private String doctico; //VARCHAR(100) 	문서타입 아이콘
	private String doctobj; //VARCHAR(20)   Number range object
	private String credate; //VARCHAR(8)	생성일자
	private String cretime; //VARCHAR(6)	생성시간
	private String creuser; //VARCHAR(20)	생성사용자
	private String lmodate; //VARCHAR(8) 	수정일자
	private String lmotime; //VARCHAR(6) 	수정시간
	private String lmouser; //VARCHAR(20)	수정사용자
	
	private String combovl;
	private String combonm;
	
}
