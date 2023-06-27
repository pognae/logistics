package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MrtldtDTO {
	
	//MD 도매-소매 속성 - MRTLDT
	private String compkey;		//Company Key
	private String ownerky;		//화주
	private String custkey;		//고객키
	private String retakey;		//소매
	private int lastbud;		//현잔
	private String lstcdat;		//최근현금결재일
	private String lstbdat;		//최근통장결재일
	private String lstsdat;		//최근판매일
	private int lstcamt;		//최근현금결재액
	private int lstbamt;		//최근통장결재액
	private int lstsamt;		//최근판매액
	private String credate;
	private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;
	private String indiarc;
	private int updtchk;
	
	//커스텀 컬럼
	private String retnmlc;		//소매명
	private String ltcbdat;		//최근판매일
	private String paycyty;		//입금주기타입
	private String paydate;		//입금일자
	private String weekday;		//입금요일
	private int paymday;		//입금지정일
}
