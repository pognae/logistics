package com.logistics.md.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McommtVO;
import com.logistics.md.domain.McomrfVO;
import com.logistics.md.mapper.BoardMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDB2Service {

	@Autowired
	private BoardMapper boardMapper;

	
	public Page<McommtVO> getMdb2List(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		param.put("useract", userInfo.getUseract());
		int pageNum = 0;
		if(param.containsKey("pageNum")) {
			pageNum = Integer.parseInt((String)param.get("pageNum"));
		}else {
			McommtVO mcommtVO = new McommtVO();
			pageNum = mcommtVO.getPageNum();
		}
		
		PageHelper.startPage(pageNum, 6);
		return boardMapper.getMdb2List(param);
	}

	
	public List<McommtVO> getMdb2QnASortList(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		List<McommtVO> list ;
		int pageNum = 0;
		
		if(param.containsKey("pageNum")) {
			pageNum = Integer.parseInt((String)param.get("pageNum"));
		}else {
			McommtVO mcommtVO = new McommtVO();
			pageNum = mcommtVO.getPageNum();
		}
		
		PageHelper.startPage(pageNum, 6);
		if(param.containsKey("NEW")) {
			list = boardMapper.QnAListSortByNew(param);
		}else{
			list = boardMapper.QnAListSortByViewCnt(param);
		}
		
		return list;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void getMdb2WriteSave(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		param.put("creuser", userInfo.getUseract());
		
		int insertCnt = 0 ;
		
		if(param.get("comcate").equals("FAQ")) {
			param.put("copenyn", "N");
		}else{
			if(param.get("copenyn").equals(true)) {
				param.put("copenyn", "Y");
			}else {
				param.put("copenyn", "N");
			}
		}
		insertCnt = boardMapper.insertMCommt(param);
		
		if(insertCnt == 0) {
			new InsertCheckedException();
		}
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void getMdb2WriteUpdate(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		param.put("lmouser", userInfo.getUseract());
		
		int updateCnt = 0;
		int updateCopenyn = 0;

		
		if(param.containsKey("copenyn")) {
			if(param.get("copenyn").equals(true)) {
				param.put("copenyn", "Y");
				
			}else {
				param.put("copenyn", "N");
			}
		}

		if(param.get("comcate").equals("FAQ")) {
			updateCnt = boardMapper.updateMCommtByFAQ(param);
			if(updateCnt == 0) {
				throw new UpdateCheckedException();
			}
		}else {
			McommtVO detailVO = boardMapper.getMdb2QnADetailList(param);
			// 게시글 공개여부 COPENYN만 수정할 경우 ( 수정날짜 update X )
			if(detailVO.getQuconte().equals(param.get("quconte"))) {
				updateCopenyn= boardMapper.updateCopenynChk(param);
				
				if(updateCopenyn == 0) {
					throw new UpdateCheckedException();
				}
			}else {
				updateCnt = boardMapper.updateMCommtByQnA(param);
				
				if(updateCnt == 0) {
					throw new UpdateCheckedException();
				}
				
			}
			
		}
		
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void getMdb2WriteDelete(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		param.put("deluser", userInfo.getUseract());
		
		int deleteCnt = boardMapper.deleteMcommt(param);

		if(deleteCnt == 0) {
			throw new DeleteCheckedException(); 
		}
		
		if(param.get("comcate").equals("Q&A")) {
			int deleteRefCnt = boardMapper.deleteMcomrf(param);
			
			if(deleteRefCnt == 0) {
				throw new DeleteCheckedException(); 
			}

		}
		
	}
	
	
	public void getMdb2RefContSave(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		param.put("creuser", userInfo.getUseract());
		
		int insertCnt = boardMapper.insertMComrf(param);

		if(insertCnt == 0) {
			throw new InsertCheckedException();
		}
		
	}

	
	public List<McomrfVO> getMdb2Answer(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		
		List<McomrfVO> list = boardMapper.getMdb2AnswerList(param);
		
		if(list == null) {
			list = Collections.emptyList();
			
		}
		return list;
	}

	
	public Map<String, Object> getMdb2DetailList(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		param.put("useract", userInfo.getUseract());
		
		Map<String, Object> map = new HashMap<>();
		McommtVO data = new McommtVO() ;
		List<McomrfVO> ansData = new ArrayList<>();
		
		if(param.get("comcate").equals("FAQ")) {
			
			data = boardMapper.getFAQdetail(param);
			
			boardMapper.updateQnAViewCnt(data);
			map.put("faqData", data);
			
		}else {
			data = boardMapper.getQnAdetail(param);
			
			if(data == null) {
				data = (McommtVO)Collections.emptyList();
			}
			
			ansData = boardMapper.getMdb2AnswerList(param);
			
			boardMapper.updateQnAViewCnt(data);
			map.put("qnaData", data);
			map.put("ansData", ansData);
		}
		
		return map;
	}
}
