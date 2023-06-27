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
import com.logistics.md.domain.McommaVO;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDO1Service {

	@Autowired
	private OrganizationMapper organizationmapper;
	
	
	public List<McommaVO> getMcommaListSelect(Map<String, Object> params, UserVo userInfo) {
		List<McommaVO> dataList = organizationmapper.selectMcommaList(params);
		if (dataList == null) {
			return Collections.<McommaVO>emptyList();
		}
		return dataList;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setMDO1Save(Map<String, Object> params, UserVo userInfo) {
		List<McommaVO> list = convertMcommaVO((ArrayList) params.get("data"));
		List<McommaVO> addList = new ArrayList<>();
		List<McommaVO> updateList = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getRowkey() == null) {
				addList.add(list.get(i));
			}else {
				updateList.add(list.get(i));
			}
		}
		
		if(!addList.isEmpty()) {
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("mcommavolist", addList);
    		int inserCnt = organizationmapper.insertMcomma(map);
    		if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
    	}
        
    	if(!updateList.isEmpty()) {
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("mcommavolist", updateList);
        	int updateCnt = organizationmapper.updateMcomma(map);
        	if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
    	}

    	// 테스트용
//    	Map<String, Object> call = new HashMap<>();
//    	call.put("compkey", userInfo.getCompkey());
//    	call.put("loguser", userInfo.getUseract());
//    	
//    	organizationmapper.sp_mcomma2_test(call);
//    	if(!call.get("O_ORESULT").equals(0)) {
//    		throw new procedureCheckedException((String) call.get("O_OMSGKEY"));
//    	}
	}
	
    /*
     * List<MaremaDTO> convert
     * */
    private List<McommaVO> convertMcommaVO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, McommaVO.class));
    }

}
