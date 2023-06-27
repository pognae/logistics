package com.logistics.md.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MaremaDTO;
import com.logistics.md.domain.MuommaVO;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.sy.domain.UserVo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.md.domain.MaremaDTO;
import com.logistics.md.domain.MuommaVO;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.UnitsMapper;

@Service
public class MDU3Service {
 
	@Autowired
	private UnitsMapper unitsMapper;

	
	public List<MuommaVO> getUnitList(Map<String, Object> params, UserVo userInfo) {
		params.put("userData", userInfo);
		List<MuommaVO> dataList = unitsMapper.selectMuomma(params);
		if (dataList == null) {
			return Collections.<MuommaVO>emptyList();
		}
		return dataList;
	}	
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setMDU3Save(Map<String, Object> params, UserVo userInfo) {
		Map grid = (Map) params.get("data");
		List<MuommaVO> updateList = convertMuommaVO((ArrayList)grid.get("updateList"));
		List<MuommaVO> addList = convertMuommaVO((ArrayList)grid.get("addList"));
		List<MuommaVO> oldList = convertMuommaVO((ArrayList)grid.get("oldList"));
        
		if(!addList.isEmpty()) {
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = unitsMapper.insertMuomma(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
    	}
		
    	if(!updateList.isEmpty() && !oldList.isEmpty()) {
        	
        	for(int i =0; i < updateList.size(); i++) {
        		updateList.get(i).setOlduomekey(oldList.get(i).getUomekey());
    		}
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
        	int updateCnt = unitsMapper.updateMuomma(map);
        	if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
    	}
	}
	
	
	public List<Map<String, Object>> getUnitSelectBox(Map<String, Object> params, @AuthenticationPrincipal UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return unitsMapper.selectUnitBox(params);
	}
	
    private List<MuommaVO> convertMuommaVO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, MuommaVO.class));
    }
}

