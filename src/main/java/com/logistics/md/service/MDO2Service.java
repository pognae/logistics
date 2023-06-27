package com.logistics.md.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MwarmaVO;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MDO2Service {

	private final OrganizationMapper morganiMapper;
	
	
	public List<MwarmaVO> getMwarmaListSelect(Map<String , Object> param, UserVo userInfo){
		param.put("userData", userInfo);
		List<MwarmaVO> dataList = morganiMapper.selectMwarmaList(param);
		if(dataList == null) {
			dataList = Collections.emptyList();
		}
		return dataList;
	}
	
	public List<Map<String, Object>> getWarehouseSelectBox(Map<String, Object> params, @AuthenticationPrincipal UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());// compkey : 000
		return morganiMapper.selectWarehouseBox(params);
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void saveMdo2(Map<String, Object> params , UserVo userInfo) {
		Map grid = (Map) params.get("data");
		List<MwarmaVO> updateList = convertMwarmaVO((ArrayList)grid.get("updateList"));
		List<MwarmaVO> addList = convertMwarmaVO((ArrayList)grid.get("addList"));
		List<MwarmaVO> oldList = convertMwarmaVO((ArrayList)grid.get("oldList"));
		
		if(!addList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = morganiMapper.insertMwarma(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
        }
		
		if(!updateList.isEmpty() && !oldList.isEmpty()) {
    		for(int i=0; i < updateList.size(); i++) {
    			updateList.get(i).setOldwarekey(oldList.get(i).getWarekey());
    		}
    		HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
    		map.put("list", updateList);
    		int updateCnt = morganiMapper.updateMwarma(map);
    		if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
        }
	}
	
	 private List<MwarmaVO> convertMwarmaVO(ArrayList arr){
	    	ObjectMapper mapper = new ObjectMapper();
	    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, MwarmaVO.class));
	 }
	 
	
	public List<Map<String, Object>> getWarehouseUserSelectBox(Map<String, Object> params, @AuthenticationPrincipal UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		params.put("useract", userInfo.getUseract());
		return morganiMapper.selectWarehouseUserBox(params);
	}
}
