package com.logistics.tplt.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.tplt.domain.TpltVO;
import com.logistics.tplt.domain.TpsbVO;
import com.logistics.tplt.mapper.TpltMapper;
import com.logistics.sy.domain.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class Tplt1Service {
	
	/* 
	 * ******************************************** 
	  - Service Name   : Tplt1Service
	  - procedure Name : 템플릿 서비스
	  - Description    : 템플릿 서비스 내용
	  - Made By        : Shin Wonhyuk
	  - Creation Date  : 2023.05.30
	  ------------------------------------------
	 
	* ********************************************  
	*/ 
	
	@Autowired
	private TpltMapper tpltMapper;

	public List<TpsbVO> getTpltInitData(Map<String, Object> param, UserVo userVo){
		/************************
		 *** get Service
		 ************************/

		return tpltMapper.selectTpltSelectBoxList(param);
	}

	public List<TpltVO> getTplt1List(Map<String, Object> param, UserVo userVo){
		/************************
		 *** get Service
		 ************************/

		return tpltMapper.selectTplt1List(param);
	}

	public List<TpltVO> validationTplt1List(Map<String, Object> param, UserVo userVo){
		/************************
		 *** validation Service
		 ************************/

		List<TpltVO> returnList = null;

		return returnList;
	}

	public String toString(UserVo userVo){
		/************************
		 *** to Service
		 ************************/

		String returnData = "returData";

		return returnData;
	}
	
	public int saveTplt1Data(Map<String, Object> param, UserVo userVo){

		int resultCount = 0;

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);		// 알 수 없는 프로퍼티가 있을경우 예외가 발생할 수 있음 비활성화 처리

		Map<String, Object> sqlMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = mapper.convertValue(param.get("list"), Map.class);

		List<TpltVO> insertList 	= dataMap.get("addList") != null ? new ArrayList<TpltVO>(Arrays.asList(mapper.convertValue(dataMap.get("addList"), TpltVO[].class))) : null;
		List<TpltVO> updateList 	= dataMap.get("updateList") != null ? new ArrayList<TpltVO>(Arrays.asList(mapper.convertValue(dataMap.get("updateList"), TpltVO[].class))) : null;
		List<TpltVO> deleteList 	= dataMap.get("deleteList") != null ? new ArrayList<TpltVO>(Arrays.asList(mapper.convertValue(dataMap.get("deleteList"), TpltVO[].class))) : null;

		sqlMap.put("userData", userVo);

		/**
		 * insertList
		 */
		if(insertList != null && insertList.size() > 0) {
			sqlMap.put("insertList", insertList);
			resultCount = tpltMapper.insertTplt1Data(sqlMap);

			if(resultCount == 0) {
				throw new InsertCheckedException();
			}
		}

		/**
		 * updateList
		 */
		if(updateList != null && updateList.size() > 0) {
			sqlMap.put("updateList", updateList);
			resultCount = tpltMapper.updateTplt1Data(sqlMap);

			if(resultCount == 0) {
				throw new UpdateCheckedException();
			}
		}


		return resultCount;
	}

	public int updateTplt1Date(Map<String, Object> param, UserVo userVo){

		int resultCount = 0;

		/************************
		 *** update Service
		 ************************/

		return resultCount;
	}

	public int modifyTplt1Date(Map<String, Object> param, UserVo userVo){

		int resultCount = 0;

		/************************
		 *** modify Service
		 ************************/

		return resultCount;
	}

	public int deleteTplt1Date(Map<String, Object> param, UserVo userVo){

		int resultCount = 0;

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);		// 알 수 없는 프로퍼티가 있을경우 예외가 발생할 수 있음 비활성화 처리

		Map<String, Object> sqlMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = mapper.convertValue(param.get("list"), Map.class);

		List<TpltVO> deleteList 	= dataMap.get("deleteList") != null ? new ArrayList<TpltVO>(Arrays.asList(mapper.convertValue(dataMap.get("deleteList"), TpltVO[].class))) : null;

		sqlMap.put("userData", userVo);

		/**
		 * deleteList
		 */
		if(deleteList != null && deleteList.size() > 0) {
			sqlMap.put("deleteList", deleteList);
			resultCount = tpltMapper.deleteTplt1Data(sqlMap);

			if(resultCount == 0) {
				throw new DeleteCheckedException();
			}
		}

		return resultCount;
	}
}
