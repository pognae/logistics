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
import com.logistics.md.domain.McusmaVO;
import com.logistics.md.domain.MptnmaVO;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDP2Service {
	
	@Autowired
	private PartnerMapper partnerMapper;
	
	
	public List<MptnmaVO> getMptnmaList(Map<String, Object> params, UserVo userInfo) {
		params.put("userData", userInfo);
		List<MptnmaVO> dataList = partnerMapper.selectMptnmaList(params);
		if (dataList == null) {
			return Collections.<MptnmaVO>emptyList();
		}
		return dataList;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void setMdp2Save(Map<String, Object> params, UserVo userInfo) {
		Map grid = (Map) params.get("data");
		List<MptnmaVO> updateList = convertMptnmaVO((ArrayList)grid.get("updateList"));
		List<MptnmaVO> addList = convertMptnmaVO((ArrayList)grid.get("addList"));
		List<MptnmaVO> oldList = convertMptnmaVO((ArrayList)grid.get("oldList"));
        
		List<McusmaVO> mcuAddList = new ArrayList<>();
		List<McusmaVO> mcuUpdateList = new ArrayList<>();
		
    	if(!updateList.isEmpty() && !oldList.isEmpty()) {
        	for(int i =0; i < updateList.size(); i++) {
        		updateList.get(i).setOldptnrkey(oldList.get(i).getPtnrkey());
        		if("VENDER".equals(updateList.get(i).getPtnrtyp())) {
        			McusmaVO mcusma = new McusmaVO();
        			mcusma.setCompkey(updateList.get(i).getCompkey());
        			mcusma.setCustkey(updateList.get(i).getPtnrkey());
        			mcusma.setOldcustkey(oldList.get(i).getPtnrkey());
        			mcusma.setOwnerky(updateList.get(i).getOwnerky());
        			mcusma.setCudelyn(updateList.get(i).getPtdelyn());
        			mcusma.setCusttyp(updateList.get(i).getPtnrtyp());
        			mcusma.setCunamlc(updateList.get(i).getPtnamlc());
        			mcusma.setCusgro1(updateList.get(i).getPtngro1());
        			mcuUpdateList.add(mcusma);
        		}
    		}
        	
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
    		int updateCnt = partnerMapper.updateMptnma(map);
    		if(updateCnt == 0) {
    			throw new UpdateCheckedException();
    		}
    		
    		if(!mcuUpdateList.isEmpty()) {
    			HashMap<String, Object> map2 = new HashMap<>();
    			map2.put("userData", userInfo);
    			map2.put("list", mcuUpdateList);
    			partnerMapper.updateMcusma(map2);
    		}
    	}
    	
    	if(!addList.isEmpty()) {
    		for(int i =0; i < addList.size(); i++) {
    			if("VENDER".equals(addList.get(i).getPtnrtyp())) {
    				McusmaVO mcusma = new McusmaVO();
    				mcusma.setCompkey(addList.get(i).getCompkey());
    				mcusma.setCustkey(addList.get(i).getPtnrkey());
    				mcusma.setOwnerky(addList.get(i).getOwnerky());
    				mcusma.setCusttyp(addList.get(i).getPtnrtyp());
    				mcusma.setCunamlc(addList.get(i).getPtnamlc());
        			mcusma.setCusgro1(addList.get(i).getPtngro1());
    				mcusma.setCreuser(userInfo.getUseract());
    				mcusma.setLmouser(userInfo.getUseract());
    				mcuAddList.add(mcusma);
    			}
    		}
    		
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		int inserCnt = partnerMapper.insertMptnma(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
    		
    		if(!mcuAddList.isEmpty()) {
    			for(int i =0; i < mcuAddList.size(); i++) {
    				partnerMapper.insertMcusma(mcuAddList.get(i));
    			}
    		}
    	}
	}
		
    /*
     * List<MptnmaVO> convert
     * */
    private List<MptnmaVO> convertMptnmaVO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, MptnmaVO.class));
    }

	
	public List<Map<String, Object>> getMp2SelectBox(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		return partnerMapper.getMdp2SelectBox(param);
	}

	
	public List<Map<String, Object>> getMp2SelectBoxByAll(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		return partnerMapper.getMdp2SelectBoxByAll(param);
	}

	
	public String mdp2Ptnrkey(Map<String, Object> param) {
		return partnerMapper.getPtnrkey();
	}

}
