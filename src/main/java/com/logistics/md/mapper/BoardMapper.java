package com.logistics.md.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.logistics.md.domain.BoardDTO;
import com.logistics.md.domain.MboardVO;
import com.logistics.md.domain.MbodrfVO;
import com.logistics.md.domain.McommtVO;
import com.logistics.md.domain.McomrfVO;

@Mapper
public interface BoardMapper {
	
	//게시판
	public int insertBoard(BoardDTO params);

	public BoardDTO selectBoardDetail(Long idx);

	public int updateBoard(BoardDTO params);

	public int deleteBoard(Long idx);

	public List<BoardDTO> selectBoardList();

	public int selectBoardTotalCount();

	public Page<MboardVO> getBoardList(Map<String, Object> param);

	
	public int saveBoard(Map<String, Object> param);

	
	public MboardVO getBoardDetail(Map<String, Object> param);
	public int getBoardListCount(Map<String, Object> param);
	public List<MbodrfVO> getBoardRefDetail(Map<String, Object> param);

	public int updateBoardViewCnt(MboardVO param);
	public int saveBoardDetail(Map<String, Object>  param);
	public MbodrfVO saveBoardRefDetail(Map<String, Object>  param);

	public int insertBoardRef(Map<String, Object> param);
	public int deleteBoardRef(Map<String, Object> param);
	public int deleteBoardDetail(Map<String, Object> param);
	
	//게시판 Q&A , FAQ
	public Page<McommtVO> getMdb2List(Map<String, Object> param);
	public int getListCount(Map<String, Object> param);

	public McommtVO getQnAdetail(Map<String, Object> param); // 상세페이지
	public McommtVO getFAQdetail(Map<String, Object> param);
	
	public int updateQnAViewCnt(McommtVO param); // 조회수

	public List<McommtVO> QnAListSortByNew(Map<String, Object> param); // QnA 정렬 최신
	public List<McommtVO> QnAListSortByViewCnt(Map<String, Object> param); //  QnA 정렬 조회수

	public int insertMCommt(Map<String, Object> param); //글쓰기
	public int updateMCommtByFAQ(Map<String, Object> param);
	public int updateMCommtByQnA(Map<String, Object> param);

	public int insertMComrf(Map<String, Object> param); //댓글 등록
	public int deleteMcommt(Map<String, Object> param); // 게시글 삭제
	public int deleteMcomrf(Map<String, Object> param);

	public List<McomrfVO> getMdb2AnswerList(Map<String, Object> param);

	public McommtVO getMdb2QnADetailList(Map<String, Object> param);
	public int updateCopenynChk(Map<String, Object> param);

	public List<Map<String, Object>> getBoardMainList(Map<String, Object> param);












}