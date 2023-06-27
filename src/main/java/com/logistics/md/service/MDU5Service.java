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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MpakmaVO;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDU5Service {
 
	@Autowired
	private UnitsMapper unitsMapper ;

	
	public List<MpakmaVO> getMpaklist(Map<String, Object> params, UserVo userInfo) {
		params.put("userData", userInfo);
		List<MpakmaVO> dataList = unitsMapper.selectMpakma(params);
		if (dataList == null) {
			return Collections.<MpakmaVO>emptyList();
		}
		return dataList;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setMDU5Save(Map<String, Object> params, UserVo userInfo) {
		Map grid = (Map) params.get("data");
		List<MpakmaVO> updateList = convertMpakmaVO((ArrayList)grid.get("updateList"));
		List<MpakmaVO> addList = convertMpakmaVO((ArrayList)grid.get("addList"));
		List<MpakmaVO> oldList = convertMpakmaVO((ArrayList)grid.get("oldList"));
        
		if(!addList.isEmpty()) {
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = unitsMapper.insertMpakma(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
    	}
		
    	if(!updateList.isEmpty() && !oldList.isEmpty()) {
        	for(int i =0; i < updateList.size(); i++) {
        		updateList.get(i).setOldwarekey(oldList.get(i).getWarekey());
        		updateList.get(i).setOldpackkey(oldList.get(i).getPackkey());
        		updateList.get(i).setOldtruntyp(oldList.get(i).getTruntyp());
    		}
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
        	int updateCnt = unitsMapper.updateMpakma(map);
        	if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
    	}
	}
	
    private List<MpakmaVO> convertMpakmaVO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, MpakmaVO.class));
    }
    
    
	public List<Map<String, Object>> getMpakmaSelectBox(Map<String, Object> params, @AuthenticationPrincipal UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return unitsMapper.selectMpakmaBox(params);
	}
    
    
   	public List<Map<String, Object>> getMpakmaSelectPackBox(Map<String, Object> params, @AuthenticationPrincipal UserVo userInfo) {
   		params.put("compkey", userInfo.getCompkey());
   		return unitsMapper.selectMpakmaPackBox(params);
   	}
}

