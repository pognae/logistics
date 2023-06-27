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
import com.logistics.sy.domain.SloghiVO;
import com.logistics.sy.domain.SprogmVO;
import com.logistics.sy.domain.SprthiVO;
import com.logistics.sy.domain.SupahiVO;
import com.logistics.sy.domain.UserVo;
import com.logistics.sy.mapper.WorkbenchMapper;

@Service
public class WorkbenchService{
	
	@Autowired
	private WorkbenchMapper workbenchmapper;
	
	
	public List<SprogmVO> getSyw1List(Map<String, Object> params, UserVo userInfo)  {
		params.put("userData", userInfo);
		List<SprogmVO> dataList = workbenchmapper.selectSyw1List(params);
		if(dataList == null) {
			return Collections.<SprogmVO>emptyList();
		}
		return dataList;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setSyw1List(Map<String, Object> params, UserVo userInfo)  {
		Map grid = (Map) params.get("data");
		List<SprogmVO> updateList = convertSprogmVODTO((ArrayList)grid.get("updateList"));
		List<SprogmVO> addList = convertSprogmVODTO((ArrayList)grid.get("addList"));
		List<SprogmVO> oldList = convertSprogmVODTO((ArrayList)grid.get("oldList"));
		List<SprogmVO> deleteList = convertSprogmVODTO((ArrayList)grid.get("deleteList"));
		
		if(!addList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", addList);
			int inserCnt = workbenchmapper.insertSprogm(map);
			if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
		}
		
		if(!updateList.isEmpty() && !oldList.isEmpty() ) {
			for(int i=0; i<updateList.size(); i++) {
				updateList.get(i).setOldprogrid(oldList.get(i).getProgrid());
			}
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", updateList);
			int updateCnt = workbenchmapper.updateSprogm(map);
			if(updateCnt == 0) {
				throw new UpdateCheckedException();
			}
		}
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setSYW1Delete(Map<String, Object> params, UserVo userInfo) {
		List<SprogmVO> list = convertSprogmVODTO((ArrayList)params.get("data"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		int deleteCnt = workbenchmapper.deleteSprogm(map);
		if(deleteCnt == 0) {
    		throw new DeleteCheckedException();
    	}
	}
	
	private List<SprogmVO> convertSprogmVODTO(ArrayList arr){
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, SprogmVO.class));
	}
	
	
	public List<SupahiVO> getSyw2List(Map<String, Object> params, UserVo userInfo)  {
		params.put("userData", userInfo);
		List<SupahiVO> dataList = workbenchmapper.selectSyw2List(params);
		if(dataList == null) {
			return Collections.<SupahiVO>emptyList();
		}
		return dataList;
	}
	
	
	public List<SloghiVO> getSyw3List(Map<String, Object> params, UserVo userInfo)  {
		params.put("userData", userInfo);
		List<SloghiVO> dataList = workbenchmapper.selectSyw3List(params);
		if(dataList == null) {
			return Collections.<SloghiVO>emptyList();
		}
		return dataList;
	}
	
	
	public List<SprthiVO> getSyw4List(Map<String, Object> params, UserVo userInfo)  {
		params.put("userData", userInfo);
		List<SprthiVO> dataList = workbenchmapper.selectSyw4List(params);
		if(dataList == null) {
			return Collections.<SprthiVO>emptyList();
		}
		return dataList;
	}
	
}
