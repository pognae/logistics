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
import com.logistics.md.domain.MzonmaVO;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class MDO4Service {
	
	private final OrganizationMapper organizationMapper;  
	
	
	
	public List<MzonmaVO> getMzonmaSelect(Map<String, Object> params , UserVo userInfo){
		params.put("userData", userInfo);
    	List<MzonmaVO> dataList = organizationMapper.selectMzonmaList(params);
    	if(dataList == null) {
    		return Collections.<MzonmaVO>emptyList();
    	}
        return dataList;	 
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void saveMdo4(Map<String, Object> param , UserVo userInfo) {
		Map grid = (Map) param.get("data");
		List<MzonmaVO> addList = convertMzonmaVO((ArrayList) grid.get("addList"));
		List<MzonmaVO> updateList = convertMzonmaVO((ArrayList) grid.get("updateList"));
		List<MzonmaVO> oldList = convertMzonmaVO((ArrayList)grid.get("oldList"));
        
		if(!addList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = organizationMapper.insertMzonma(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
		}
			
		if(!updateList.isEmpty() && !oldList.isEmpty()) {
			for(int i = 0 ; i < updateList.size() ; i++ ) {
				updateList.get(i).setOldwarekey(oldList.get(i).getWarekey());
				updateList.get(i).setOldareakey(oldList.get(i).getAreakey());
				updateList.get(i).setOldzonekey(oldList.get(i).getZonekey());
			}
			HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
    		map.put("list", updateList);
    		int updateCnt = organizationMapper.updateMzonma(map);
    		if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
		}
	}
	
	
	public List<Map<String, Object>> getZoneSelectBox(Map<String, Object> params, @AuthenticationPrincipal UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return organizationMapper.selectZoneBox(params);
	}

	private List<MzonmaVO> convertMzonmaVO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, MzonmaVO.class));
	}
}
