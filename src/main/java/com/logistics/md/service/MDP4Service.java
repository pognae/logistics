package com.logistics.md.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McusmaVO;
import com.logistics.md.domain.McuswhVO;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDP4Service {
	
	@Autowired
	private PartnerMapper partnerMapper;

	
	public List<McusmaVO> getMdp4HeaderList(Map<String, Object> params, UserVo userInfo) {
		params.put("userData", userInfo);
		List<McusmaVO> dataList = partnerMapper.getMdp4McusmaList(params);
		if (dataList == null) {
			return Collections.<McusmaVO>emptyList();
		}
		return dataList;
	}

	
	public List<McuswhVO> getMdp4ItemList(Map<String, Object> param, UserVo userInfo) {
		List<McuswhVO> dataList = partnerMapper.getMdp4McuswhList(param);
		if(dataList == null) {
			return Collections.<McuswhVO>emptyList();
		}
		return dataList;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void setMcuswhSave(Map<String, Object> param , UserVo userInfo) {
		Map grid = (Map)param.get("data");
		List<McuswhVO> oldList = convertMcuswhVO((ArrayList)grid.get("oldList"));
		List<McuswhVO> addList = convertMcuswhVO((ArrayList) grid.get("addList"));
		List<McuswhVO> updateList = convertMcuswhVO((ArrayList) grid.get("updateList"));
		List<McuswhVO> deleteList = convertMcuswhVO((ArrayList) grid.get("deleteList"));
		
		if(!addList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
    		map.put("list", addList);
			int inserCnt = partnerMapper.saveMcuswhList(map);
			if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
		}
		
		if(!updateList.isEmpty() && !oldList.isEmpty()) {
			
			for(int i = 0; i < updateList.size() ; i++) {
				updateList.get(i).setOldMngwhky(oldList.get(i).getMngwhky());
			}
			HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
			int updateCnt = partnerMapper.updateMcuswhList(map);
			if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
		}
		
		if(!deleteList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("list", deleteList);
			int deleteCnt = partnerMapper.deleteMcuswhList(map);
			if(deleteCnt == 0) {
        		throw new DeleteCheckedException();
        	}
		}
	}

	private List<McuswhVO> convertMcuswhVO(ArrayList arr) {
		ObjectMapper om = new ObjectMapper();
		return om.convertValue(arr, om.getTypeFactory().constructCollectionType(List.class, McuswhVO.class));
	}
	
	
	public String getMcuswh(Map<String, Object> params , UserVo userInfo) {
		params.put("userData", userInfo);
		return partnerMapper.selectMcuswh(params);
	}
	
}
