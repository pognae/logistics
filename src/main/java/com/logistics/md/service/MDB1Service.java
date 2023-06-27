package com.logistics.md.service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MboardVO;
import com.logistics.md.domain.MbodrfVO;
import com.logistics.md.mapper.BoardMapper;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MDB1Service {
	
	private final BoardMapper boardMapper;
	
	 
	public Page<MboardVO> getBoardList(Map<String, Object> param , UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		int pageNum = 0;
		if(param.containsKey("pageNum")) {
			pageNum = Integer.parseInt((String)param.get("pageNum"));
		}else {
			MboardVO mboardVo = new MboardVO();
			pageNum = mboardVo.getPageNum();
		}
		
		PageHelper.startPage(pageNum, 15);
		return boardMapper.getBoardList(param);
	}

	
	@Transactional(rollbackFor = Exception.class)
	public boolean saveBoard(Map<String, Object> param , UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		param.put("creuser", userInfo.getUseract());
		boolean result = false;
		
		if(param.size() > 0) {
			int insertCnt = boardMapper.saveBoard(param);

			if(insertCnt == 0) {
				new InsertCheckedException();
			}
			result = true;
		}
		return result;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> getBoardDetail(Map<String, Object> param , UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		
		Map<String, Object> map = new HashMap<>();
		MboardVO boardData = new MboardVO();
		List<MbodrfVO> refData = new ArrayList<>();
		
		if(param.get("boidseq")== null) {
			boardData = (MboardVO)Collections.emptyList();
			refData = Collections.emptyList();
		}
		
		boardData = boardMapper.getBoardDetail(param);
		refData = boardMapper.getBoardRefDetail(param);
		
		int viewcntData = boardMapper.updateBoardViewCnt(boardData);
		
		if(viewcntData == 0) {
			throw new UpdateCheckedException();
		}
		
		map.put("boardData", boardData);
		map.put("refData", refData);
		
		return map;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public boolean insertMbodrf(Map<String, Object> param , UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		param.put("creuser", userInfo.getUseract());

		boolean result = false;
		
		if(param.size() > 0) {
			int refData = boardMapper.insertBoardRef(param);
			
			if(refData == 0) {
				new InsertCheckedException();
			}
			result = true;
		}
		return result;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public boolean saveBoardDetail(Map<String, Object> param , UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		param.put("lmouser", userInfo.getUseract());
		int boardCnt = 0;
		boolean result = false;
		
		if(param.get("noticyn").equals(true)) {
			param.put("noticyn", "Y");
		}else {
			param.put("noticyn", "N");
		}
		
		if(param.size() > 0) {
			MboardVO dataVO = boardMapper.getBoardDetail(param);
			
			if(dataVO.getLmodate().trim().isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("(MM/DD 수정)");
				Calendar cal = Calendar.getInstance();
				
				String updateDate = sdf.format(cal.getTime());
				String botitle = (String)param.get("botitle") + " " + updateDate;
				param.put("botitle", botitle);
			}
				
			boardCnt = boardMapper.saveBoardDetail(param);
		
			if(boardCnt == 0) {
				new UpdateCheckedException();
			}
			result = true;
		}
		return result;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteBoardDetail(Map<String, Object> param , UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		param.put("useract", userInfo.getUseract());
		boolean result = false;
		
		if(!param.get("boidseq").equals("")) {
			int boardData = boardMapper.deleteBoardDetail(param);
			
			if(boardData == 0) {
				new UpdateCheckedException();
			}
			
			boardMapper.deleteBoardRef(param);
			result = true;
		}
		
		return result;
	}

	
	
	@Transactional(rollbackFor = Exception.class)
	public void deleteBodrfcont(Map<String, Object> param , UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		param.put("deluser", userInfo.getUseract());
		int refData = 0;
		
		if(!param.get("boidseq").equals("")) {
			refData = boardMapper.deleteBoardRef(param);
		}
		
		if(refData == 0) {
			new InsertCheckedException();
		}
		
	}
	

	public static <K, V> K getKey(Map<K, V> map, V value) {
        for (K key : map.keySet()) {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
    }

}
