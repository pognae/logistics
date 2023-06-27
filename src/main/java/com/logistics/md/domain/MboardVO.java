package com.logistics.md.domain;

import com.logistics.configuration.util.PaginationVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MboardVO extends PaginationVO{
	
	private String rowkey;		//ROW KEY
	private String compkey; 	//VARCHAR(20)    
	private int boidseq; 	//INT(11)
	private String botitle; 	//VARCHAR(100)
	private String boconte; 	//VARCHAR(2000)
	private int viewcnt;	//INT(11)
	private String noticyn; 	//VARCHAR(1) 
	private String deletyn; 	//VARCHAR(1)
	private String attfnm1;     //VARCHAR(100)
	private String attfnm2;     //VARCHAR(100)
	private String attfnm3;     //VARCHAR(100)
	private String credate;     //VARCHAR(8)
	private String cretime;     //VARCHAR(6)
	private String creuser;     //VARCHAR(60)
	private String lmodate;     //VARCHAR(8)
	private String lmotime;     //VARCHAR(6)
	private String lmouser;     //VARCHAR(60)
	private String deldate;     //VARCHAR(8)
	private String deltime;     //VARCHAR(6)
	private String deluser;     //VARCHAR(60)
	private String indibzl;     //biz logic indicator  
	private String indiarc;     //Archive indicator    
	private int updtchk;     //Update check
	private String refcnt;		//댓글 개수
	private String rfcont;		//게시판 댓글 내용
	private String rfuser;		//게시판 댓글 등록자
	private String rfdate;		//게시판 댓글 등록일
	private String rftime;		//게시판 댓글 등록시간
	
	private String newCnt;
	
	private String writer;   
	
	private String orgfnm1;		//원본 파일명1
	private String orgfnm2;		//원본 파일명2
	private String orgfnm3;		//원본 파일명3

}
