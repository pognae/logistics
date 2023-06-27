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
import com.logistics.md.domain.MtutmaVO;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDU4Service {
 
	@Autowired
	private UnitsMapper unitsMapper ;

	
	public List<MtutmaVO> getMtutlist(Map<String, Object> params, UserVo userInfo) {
		params.put("userData", userInfo);
		List<MtutmaVO> dataList = unitsMapper.selectMtutma(params);
		if (dataList == null) {
			return Collections.<MtutmaVO>emptyList();
		}
		return dataList;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void setMDU4Save(Map<String, Object> params, UserVo userInfo) {
		Map grid = (Map) params.get("data");
		List<MtutmaVO> updateList = convertMtutmaVO((ArrayList)grid.get("updateList"));
		List<MtutmaVO> addList = convertMtutmaVO((ArrayList)grid.get("addList"));
		List<MtutmaVO> oldList = convertMtutmaVO((ArrayList)grid.get("oldList"));
        
		if(!addList.isEmpty()) {
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = unitsMapper.insertMtutma(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
    	}
		
		if(!updateList.isEmpty() && !oldList.isEmpty()) {
        	
        	for(int i =0; i < updateList.size(); i++) {
        		updateList.get(i).setOldtruntyp(oldList.get(i).getTruntyp());
    		}
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
        	int updateCnt = unitsMapper.updateMtutma(map);
    		if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
    	}
	}
	
	
	public List<Map<String, Object>> getMtutSelectBox(Map<String, Object> params, @AuthenticationPrincipal UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return unitsMapper.selectMtutBox(params);
	}	
	
    private List<MtutmaVO> convertMtutmaVO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, MtutmaVO.class));
    }
}

