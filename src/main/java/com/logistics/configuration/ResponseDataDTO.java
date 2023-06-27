package com.logistics.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/*
 * json 응답을 위한 ResponseDataDTO.java 생성
 * {
 * code : string,
 * status : string,
 * message : string, 
 * item : object
 * }
 * 형태로 json을 응답해준다.
 * */
public class ResponseDataDTO {
	
	private String code = "200";
	private String status="SUCCESS";
	private String message="SUCCESS";
	private String messagekey; //다국어 메세지 키
	private Object item; //data  dto list
	private Object item2; //data 2 dto list
	private Object item3; //data 3 dto list
	private Object item4; //data 4 dto list
	private Object item5; //data 5 dto list
	
}