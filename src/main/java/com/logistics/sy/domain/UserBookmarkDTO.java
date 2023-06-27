package com.logistics.sy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBookmarkDTO {
	private String rowkey;	//Row Key
	private String compkey; //Company Key
	private String useract; //계정ID
	private String progrid; //VARCHAR(20)	프로그램ID
	private String menugbn; //VARCHAR(10)	메뉴구분 FLD PGM
	private String menulky; //VARCHAR(20)	메뉴명 라벨키
	private int menulvl; //NUMERIC(10,3)	메뉴레벨
	private String menunam; //VARCHAR(30)	메뉴명칭_참고용
	private int menuord; //NUMERIC(10,3)	메뉴순서번호
	private String menuico; //VARCHAR(100)	메뉴아이콘
	private String opentyp; //VARCHAR(20)	페이지 Open방법 (DEFAULT, SINGLEPAGE, POPUPPAGE, TABPAGE)
	private String credate; //
	private String cretime; //
	private String creuser; //
	private String lmodate; //
	private String lmotime; //
	private String lmouser; //
	private String indibzl; //
	private String indiarc; //
	private int updtchk; //	 
}
