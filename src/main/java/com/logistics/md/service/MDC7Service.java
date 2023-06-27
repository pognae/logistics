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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.sy.domain.UserVo;

@Service  
public class MDC7Service {

	@Autowired
	private CodeMapper mcodemMapper; 
	
	
	public List<McodemDTO> getMcodemListSelect(Map<String, Object> params, UserVo userInfo) {
		params.put("userData", userInfo);
        List<McodemDTO> dataList = mcodemMapper.selectMcodemList(params);
        if(dataList == null){
        	return Collections.<McodemDTO>emptyList();
        }
		return dataList;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void setMDC7Save(Map<String, Object> params, UserVo userInfo) {
		Map grid = (Map) params.get("data");
		List<McodemDTO> updateList 	= convertMcodemDTO((ArrayList)grid.get("updateList"));
	    List<McodemDTO> addList 	= convertMcodemDTO((ArrayList)grid.get("addList"));
	    List<McodemDTO> oldList 	= convertMcodemDTO((ArrayList)grid.get("oldList"));
	    
	    if(!addList.isEmpty()) {
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = mcodemMapper.insertMcodem(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
	    }
	    
	    if(!updateList.isEmpty() && !oldList.isEmpty()) {
	    	for(int i =0; i < updateList.size(); i++) {
        		updateList.get(i).setBeforecomcdky(oldList.get(i).getComcdky());
        		updateList.get(i).setBeforecomcdvl(oldList.get(i).getComcdvl());
    		}
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
        	int updateCnt = mcodemMapper.updateMcodem(map);
        	if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
	    }
	}
	
	/*
     * List<McodemDTO> convert
     * */
	private List<McodemDTO> convertMcodemDTO(ArrayList arr){
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, McodemDTO.class));
	}
	
	
	public List<Map<String, Object>> getMcodemSelect(Map<String, Object> params, UserVo userInfo) {
		params.put("userData", userInfo);
		String comcdvl = (String) params.get("comcdvl");
		if(comcdvl != null) {
			List<String> comcdvls = Arrays.asList(comcdvl.split(","));
			params.put("comcdvls", comcdvls);
		}
		return mcodemMapper.selectMcodem(params);
	}
}
