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
import com.logistics.md.domain.MlocmaVO;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDO5Service {

	@Autowired
	private OrganizationMapper organizationmapper;
	
	
	public List<MlocmaVO> selectMlocmaList(Map<String, Object> params, UserVo userInfo){
		params.put("userData", userInfo);
        List<MlocmaVO> dataList = organizationmapper.selectMlocmaList(params);
    	if(dataList == null){
            return Collections.<MlocmaVO>emptyList();
        }
        return dataList; 
	}
	
	
	@Transactional(rollbackFor = Exception.class) 
	public void setMDO5Save(Map<String, Object> params, UserVo userInfo)  {
		Map grid = (Map) params.get("data");		
		List<MlocmaVO> addList = convertMlocmaVO((ArrayList)grid.get("addList"));
	    List<MlocmaVO> updateList = convertMlocmaVO((ArrayList)grid.get("updateList"));
	    List<MlocmaVO> oldList = convertMlocmaVO((ArrayList)grid.get("oldList"));
	    
	    if(!addList.isEmpty()) {
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = organizationmapper.insertMlocma(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
    	}
	    
    	if(!updateList.isEmpty() && !oldList.isEmpty()) {
        	
        	for(int i =0; i < updateList.size(); i++) {
        		updateList.get(i).setOldwarekey(oldList.get(i).getWarekey());
        		updateList.get(i).setOldareakey(oldList.get(i).getAreakey());
        		updateList.get(i).setOldzonekey(oldList.get(i).getZonekey());
        		updateList.get(i).setOldlocakey(oldList.get(i).getLocakey());
    		}
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
    		map.put("list", updateList);
    		int updateCnt = organizationmapper.updateMlocma(map);
    		if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
    	}
	}
	
	
	public List<Map<String, Object>> getLocationSelectBox(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		if(params.containsKey("notloctype")) {
			String notloctype = (String) params.get("notloctype");
	    	if(notloctype != null && !"".equals(notloctype)) {
	    		List<String> notloctypes = Arrays.asList(notloctype.split(","));
	    		params.put("notloctypes", notloctypes);
	    	}
		}
		return organizationmapper.selectLocationBox(params);
	}
	

    /*
     * List<MaremaDTO> convert
     * */
    private List<MlocmaVO> convertMlocmaVO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, MlocmaVO.class));
    }
    
    
	public List<MlocmaVO> selectMlocma(Map<String, Object> params, UserVo userInfo){
		List<MlocmaVO> dataList = organizationmapper.selectMlocma(params);
    	if(dataList == null){
            return Collections.<MlocmaVO>emptyList();
        }
        return dataList; 
	}

}
