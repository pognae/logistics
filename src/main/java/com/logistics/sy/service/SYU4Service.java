package com.logistics.sy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.sy.domain.RoleGroupVo;
import com.logistics.sy.domain.UserVo;
import com.logistics.sy.mapper.UserMapper;

@Service  
public class SYU4Service {
	
	@Autowired
    private UserMapper userMapper;
	
    
    public List<RoleGroupVo> getGroupSelect(Map<String, Object> params, UserVo userInfo)  {
    	params.put("userData", userInfo);
        List<RoleGroupVo> dataList = userMapper.selectGroup(params);
        if(dataList == null) {
        	return Collections.<RoleGroupVo>emptyList();
        }
        return dataList;
    }
    
    @Transactional(rollbackFor = Exception.class)
	
    public void setSYU4Save(Map<String, Object> params, UserVo userInfo) {
    	Map grid = (Map) params.get("data");
    	List<RoleGroupVo> updateList = convertMaremaDTO((ArrayList)grid.get("updateList"));
    	List<RoleGroupVo> addList = convertMaremaDTO((ArrayList)grid.get("addList"));
    	List<RoleGroupVo> oldList = convertMaremaDTO((ArrayList)grid.get("oldList"));
    	
    	if(!addList.isEmpty()) {
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = userMapper.insertGroup(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
    	}
    	
    	if(!updateList.isEmpty() && !oldList.isEmpty()) {
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
        	int updateCnt = userMapper.updateGroup(map);
        	if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
    	}
    }
    
    
	@Transactional(rollbackFor = Exception.class)
	public void setSYU4GroupDelete(Map<String, Object> params) {
		List<RoleGroupVo> list = convertMaremaDTO((ArrayList)params.get("data"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		int deleteCnt = userMapper.deleteGroup(map);
		if(deleteCnt == 0) {
    		throw new DeleteCheckedException();
    	}
	}
    
    /*
     * List<RoleGroupVo> convert
     * */
    private List<RoleGroupVo> convertMaremaDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, RoleGroupVo.class));
    }
	
	
	public List<Map<String, Object>> getGroupSelectBox(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return userMapper.selectGroupBox(params);
	}
	
}
