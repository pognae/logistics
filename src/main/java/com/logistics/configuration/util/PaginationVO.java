package com.logistics.configuration.util;

import lombok.Data;

@Data
public class PaginationVO {
	
	private String compkey;
	private String searchType;				//서치 타입
	private String searchKeyword;			//서치 키워드 값
	
	private int startRowNumber;				//로우넘 시작 번호
	private int endRowNumber;				//로우넘 끝 번호
	
	private int pageNum;
	private int pageSize;
	private int startRow;
	private int endRow;
	private int total;
	private int pages;
	private int reasonable;
	private int pageSizeZero;
	
	 public PaginationVO(){
 		this.pageNum = 1;
 		this.pageSize = 15;
     }
}
