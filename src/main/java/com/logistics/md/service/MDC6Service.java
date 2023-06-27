package com.logistics.md.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.DocumentMapper;
import com.logistics.sy.domain.UserVo;


@Service  
public class MDC6Service {

	/*
	 * 2022.06.02
	 * SWH
	 * [MDC6] 사유코드 서비스
	 */
	
	@Autowired
	private CodeMapper mcodemMapper;
	
	@Autowired
	private DocumentMapper mdocmaMapper;
	
	/**
	 * 문서 카테고리 그룹 리스트
	 **/
	
	public List<MdocmaDTO> getDoccateGroupListSelect(Map<String, Object> param, UserVo userInfo) {

    	List<MdocmaDTO> dataList = Collections.emptyList();
    	param.put("userData", userInfo);
    	dataList = mdocmaMapper.selectMdocmaDoccateGroupList(param);
         
        return dataList;
	}
	
	/**
	 * 문서 타입 리스트
	 **/
	
	public List<MdocmaDTO> getDoctypeListSelect(Map<String, Object> param, UserVo userInfo) {
		param.put("userData", userInfo);
		String doctype = (String) param.get("doctype");
    	if(doctype != null) {
    		List<String> doctypes = Arrays.asList(doctype.split(","));
    		param.put("doctypes", doctypes);
    	}
        return mdocmaMapper.selectMdocmaDoctypeList(param);
	}
	
	
	public List<MrscmaDTO> getMrscmaListSelect(Map<String, Object> param, UserVo userInfo) {
        
    	List<MrscmaDTO> dataList = Collections.emptyList();
    	dataList = mcodemMapper.selectMrscmaList(param);
         
        return dataList;
	}

	/**
	 * 2022.06.07
	 * MDC6 저장
	 **/
	
	@Transactional(rollbackFor = Exception.class)
	public boolean setMDC6Save(Map<String, Object> params, UserVo userInfo) {
		
		boolean result = true;
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    
	    Map<String, Object> dataMap = mapper.convertValue(params.get("list"), Map.class);
	    Map<String, Object> sqlMap = new HashMap<>();
	    
	    List<MrscmaDTO> insertList 	= Arrays.asList(mapper.convertValue(dataMap.get("addList"), MrscmaDTO[].class));
	    List<MrscmaDTO> updateList 	= Arrays.asList(mapper.convertValue(dataMap.get("updateList"), MrscmaDTO[].class));
	    List<MrscmaDTO> deleteList 	= Arrays.asList(mapper.convertValue(dataMap.get("deleteList"), MrscmaDTO[].class));
	    List<MrscmaDTO> oldList 	= Arrays.asList(mapper.convertValue(dataMap.get("oldList"), MrscmaDTO[].class));
	    
	    //UserSessionData param put
	    sqlMap.put("userData", userInfo);

	    /**
	     * 실행순서
	     * 1.Delete
	     * 2.Update
	     * 3.Insert
	     * 
	     * RSNCODE 값이 복합키에 속하는 컬럼이며 변경이 가능하여
	     * 위 순서로 쿼리 실행
	     **/
	    
	    //1. Delete 실행
	    if(!deleteList.isEmpty()) {
	    	sqlMap.put("deleteList", deleteList);
	    	mcodemMapper.deleteMrscma(sqlMap);
	    }
	    
	    //updateList 는 oldList 에서 키데이터 세팅 필요하여 DTO에 객체데이터 초기화 로직 구현
	    if(!updateList.isEmpty()) {
	    	String beforeRsncode;
	    	String beforeModcode;
	    	
	    	for(int i=0; i<updateList.size(); i++) {
	    		
	    		beforeRsncode = oldList.get(i).getRsncode() != null ? oldList.get(i).getRsncode() : updateList.get(i).getRsncode();
	    		beforeModcode = oldList.get(i).getWarekey() != null ? oldList.get(i).getWarekey() : updateList.get(i).getWarekey();

	    		updateList.get(i).setBeforersncode(beforeRsncode);
	    		updateList.get(i).setBeforewarekey(beforeModcode);	    		
	    	}
	    	sqlMap.put("updateList", updateList);
	    	mcodemMapper.updateMrscma(sqlMap);
	    }
	    
	    if(!insertList.isEmpty()) {
	    	sqlMap.put("insertList", insertList);
	    	mcodemMapper.insertMrscma(sqlMap);
	    }
	    
		return result;
	}
	
	
	
	public List<MrscmaDTO> mdc6SelectBox(Map<String, Object> param, UserVo userInfo) {
		param.put("userData", userInfo);
		return mdocmaMapper.selectMrscmaSelectBox(param);
	}
}
