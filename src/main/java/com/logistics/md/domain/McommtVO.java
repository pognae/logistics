package com.logistics.md.domain;


import com.logistics.configuration.util.PaginationVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class McommtVO extends PaginationVO{
	private String compkey;
	private int commseq;
	private String comcate;
	private String comtype;
	private String copenyn;
	private String qutitle;
	private String quconte;
	private int viewcnt;
	private String credate;
	private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;
	private String indiarc;
	private int updtchk;
	private String refcnt;
	
	//paging
	private String rnum;
	private int newCnt;
	private String pageMaker;
	
	private String writer;
	private String orderby;

}
