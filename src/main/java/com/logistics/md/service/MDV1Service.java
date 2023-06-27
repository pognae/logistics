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
import com.logistics.md.domain.MvhcmaVO;
import com.logistics.md.mapper.VehicleMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDV1Service {
 
	@Autowired 
	private VehicleMapper vehicleMapper;

	
	public List<MvhcmaVO> getMvhalist(Map<String, Object> params, UserVo userInfo) {
		params.put("userData", userInfo);
		List<MvhcmaVO> dataList = vehicleMapper.selectMvhcma(params);
		if (dataList == null) { 
			return Collections.<MvhcmaVO>emptyList();
		}
		return dataList;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setMDV1Save(Map<String, Object> params, UserVo userInfo) { 
		Map grid = (Map) params.get("data");
		List<MvhcmaVO> updateList = convertMvhcmaVO((ArrayList)grid.get("updateList"));
		List<MvhcmaVO> addList = convertMvhcmaVO((ArrayList)grid.get("addList"));
		List<MvhcmaVO> oldList = convertMvhcmaVO((ArrayList)grid.get("oldList"));
        
		if(!addList.isEmpty()) {
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = vehicleMapper.insertMvhcma(map); 
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
    	}
		
    	if(!updateList.isEmpty() && !oldList.isEmpty()) {
        	for(int i =0; i < updateList.size(); i++) {
        		updateList.get(i).setOldvehicky(oldList.get(i).getVehicky());
    		}
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
    		int updateCnt = vehicleMapper.updateMvhcma(map); 
    		if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
    	}
	}
	
    private List<MvhcmaVO> convertMvhcmaVO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, MvhcmaVO.class)); //변환하고자 하는 class 
    }
}

