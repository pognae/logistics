package com.logistics.md.service; 

import java.util.ArrayList;
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
import com.logistics.md.domain.MaremaDTO;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDO3Service {

	@Autowired
	private OrganizationMapper organizationmapper;

	
	public List<MaremaDTO> getAreaList(Map<String, Object> params, UserVo userInfo) {
		params.put("userData", userInfo);
		List<MaremaDTO> dataList = organizationmapper.selectAreaList(params);
		if (dataList == null) {
			return Collections.<MaremaDTO>emptyList();
		}
		return dataList;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setMDO3Save(Map<String, Object> params, UserVo userInfo) {
		Map grid = (Map) params.get("data");
		List<MaremaDTO> updateList = convertMaremaDTO((ArrayList)grid.get("updateList"));
		List<MaremaDTO> addList = convertMaremaDTO((ArrayList)grid.get("addList"));
		List<MaremaDTO> oldList = convertMaremaDTO((ArrayList)grid.get("oldList"));
        
		if(!addList.isEmpty()) {
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = organizationmapper.insertArea(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
    	}
		
		if(!updateList.isEmpty() && !oldList.isEmpty()) {
        	
        	for(int i =0; i < updateList.size(); i++) {
        		updateList.get(i).setOldwarekey(oldList.get(i).getWarekey());
        		updateList.get(i).setOldareakey(oldList.get(i).getAreakey());
    		}
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
    		int updateCnt = organizationmapper.updateArea(map);
    		if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
    	}
	}
	
	
	public List<Map<String, Object>> getAreaSelectBox(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return organizationmapper.selectAreaBox(params);
	}
	
    /*
     * List<MaremaDTO> convert
     * */
    private List<MaremaDTO> convertMaremaDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, MaremaDTO.class));
    }

}
