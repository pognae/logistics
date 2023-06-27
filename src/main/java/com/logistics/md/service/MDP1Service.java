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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDP1Service {

	@Autowired
	private PartnerMapper partnerMapper;
	
	
	public List<MowrmaDTO> getMowrmaSelect(Map<String, Object> param, UserVo userInfo){
		param.put("userData", userInfo);
		List<MowrmaDTO> dataList =  partnerMapper.selectMowrma(param);
		if(dataList == null) {
			return Collections.<MowrmaDTO>emptyList();
		}
		return dataList;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void saveMpd1(Map<String, Object> param , UserVo userInfo) {
		Map grid = (Map)param.get("data");
		List<MowrmaDTO> addList = convertMowrmaDTO((ArrayList)grid.get("addList"));
		List<MowrmaDTO> updateList = convertMowrmaDTO((ArrayList)grid.get("updateList"));
		List<MowrmaDTO> oldList = convertMowrmaDTO((ArrayList)grid.get("oldList"));
		
		if(!addList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = partnerMapper.insertMowrma(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
		}
		
		if(!updateList.isEmpty() && !oldList.isEmpty()) {
			for(int i =0; i< updateList.size(); i++) {
				updateList.get(i).setOldownerky(oldList.get(i).getOwnerky());
			}
			HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
    		map.put("list", updateList);
    		int updateCnt = partnerMapper.updateMowrma(map);
    		if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
		}	
	}
	
	private List<MowrmaDTO> convertMowrmaDTO(ArrayList arr) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(arr , mapper.getTypeFactory().constructCollectionType(List.class, MowrmaDTO.class));
	}
	
    
    public List<Map<String, Object>> getOwnerSelectBox(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return partnerMapper.selectOwnerBox(params);
	}

}
