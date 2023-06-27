package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {

	private String boidseq;
	private String botitle;
	private String boconte;
	private int viewcnt;
	private String noticyn;
	private String deletyn;
	private String credate;
	private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String deldate;
	private String deltime;
	private String deluser;

	
	/** 번호 (PK) */
	private Long idx;

	/** 제목 */
	private String title;

	/** 내용 */
	private String content;

	/** 작성자 */
	private String writer;

	/** 조회 수 */
	private int viewCnt;

	/** 공지 여부 */
	private String noticeYn;

	/** 비밀 여부 */
	private String secretYn;

	/** 삭제 여부 */
	private String deleteYn;

	/** 등록일 */
	private String insertTime;

	/** 수정일 */
	private String updateTime;

	/** 삭제일 */
	private String deleteTime;

}