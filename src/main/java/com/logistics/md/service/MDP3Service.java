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
import com.logistics.configuration.error.procedureCheckedException;
import com.logistics.md.domain.McusmaVO;
import com.logistics.md.domain.MptnmaVO;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.sy.domain.UserVo;

@Service
public class MDP3Service {
	
	@Autowired
	private PartnerMapper partnerMapper;

	
	public List<McusmaVO> getMcusmaList(Map<String, Object> param ,UserVo userInfo){
		param.put("userData", userInfo);
		List<McusmaVO> dataList= partnerMapper.getMcusmaList(param);
		if(dataList == null) {
			return Collections.<McusmaVO>emptyList();
		}
		return dataList;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void saveMdp3(Map<String, Object> param , UserVo userInfo) {
		Map grid = (Map)param.get("data");
		List<McusmaVO> addList = convertMcusmaVO((ArrayList)grid.get("addList"));
		List<McusmaVO> updateList = convertMcusmaVO((ArrayList)grid.get("updateList"));
		List<McusmaVO> oldList = convertMcusmaVO((ArrayList)grid.get("oldList"));
		
		List<MptnmaVO> mptAddList = new ArrayList<>();
		List<MptnmaVO> mptUpdateList = new ArrayList<>();
		
		if(!addList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
    		for(int i = 0 ; i < addList.size() ; i++) {
    			addList.get(i).setCreuser(userInfo.getUseract());
    			addList.get(i).setLmouser(userInfo.getUseract());
    			
    			if(addList.get(i).getCustkey() == null) {
    				String fnCustkey = partnerMapper.selectFnCustkey(param);
    				addList.get(i).setCustkey(fnCustkey);
    			}
    			int inserCnt = partnerMapper.insertMcusma(addList.get(i));
	    		if(inserCnt == 0) {
	    			throw new InsertCheckedException();
	    		}
	    		//도매 선택 후 insert 시, MPTNMA(협력업체) 테이블에도 INSERT
	    		if(addList.get(i).getCusttyp().equals("WHOLESALE")) {
		    		Map<String , Object> call = new HashMap<String , Object>();
					call.put("userData", userInfo);
					call.put("addList", addList.get(i)); // 아래 프로시저 호출로 인하여 다중->단건 처리로 변경
					
					partnerMapper.updateMcusmaList(call); //WHOLESALE을 선택함에 따라 LOCATION을 생성하는 프로시저 호출
					
					if(call.get("O_ORESULT").equals(-1)) {
						throw new procedureCheckedException((String) call.get("O_OMSGKEY"));
					}
					
					MptnmaVO mptnma = new MptnmaVO();
					mptnma.setCompkey(addList.get(i).getCompkey());
					mptnma.setPtnrkey(addList.get(i).getCustkey());
					mptnma.setOwnerky(addList.get(i).getOwnerky());
					mptnma.setPtdelyn(addList.get(i).getCudelyn());
					mptnma.setPtnrtyp(addList.get(i).getCusttyp());
					mptnma.setPtnamlc(addList.get(i).getCunamlc());
					mptAddList.add(mptnma);
					
					HashMap<String, Object> ptnMap = new HashMap<>();
					ptnMap.put("list", mptAddList);
					ptnMap.put("userData", userInfo);
					int ptrInsertCnt = partnerMapper.insertMptnma(ptnMap);
					
					if(ptrInsertCnt == 0) {
		    			throw new InsertCheckedException();
		    		}
	    		}
    		} 
		}
		
		if(!updateList.isEmpty() && !oldList.isEmpty()) {
			for(int i = 0 ; i < updateList.size(); i++) {				
				updateList.get(i).setOldcustkey(oldList.get(i).getCustkey());
				
				//CUSTTYP 도매 DATA update 시, MPTNMA(협력업체) 테이블에도 update
				if(oldList.get(i).getCusttyp() != null && updateList.get(i).getCusttyp().equals("WHOLESALE")) {
					MptnmaVO mptnma = new MptnmaVO();
					mptnma.setCompkey(updateList.get(i).getCompkey());
					mptnma.setPtnrkey(updateList.get(i).getCustkey());
					mptnma.setOldptnrkey(updateList.get(i).getCustkey());
					mptnma.setOwnerky(updateList.get(i).getOwnerky());
					mptnma.setPtdelyn(updateList.get(i).getCudelyn());
					mptnma.setPtnrtyp(updateList.get(i).getCusttyp());
					mptnma.setPtnamlc(updateList.get(i).getCunamlc());
					mptUpdateList.add(mptnma);

					HashMap<String, Object> map = new HashMap<>();
					map.put("userData", userInfo);
					map.put("list", mptUpdateList);
					int ptrUpdateCnt = partnerMapper.updateMptnma(map);
					
					if(ptrUpdateCnt == 0) {
						throw new UpdateCheckedException();
					}
				}
				
			}
			
			HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", updateList);
    		int updateCnt = partnerMapper.updateMcusma(map);
    		
    		if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
		}
	}
	
	private List<McusmaVO> convertMcusmaVO(ArrayList arr){
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, McusmaVO.class));
	}
	
	
    public List<Map<String, Object>> getCustomerSelectBox(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return partnerMapper.selectCustomerBox(params);
	}
	
	
    public List<Map<String, Object>> getCustWareSelectBox(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return partnerMapper.selectCustWareBox(params);
	}
	
	
    public List<Map<String, Object>> getCustWareSelectBoxGroup(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return partnerMapper.selectCustWareBoxGroup(params);
	}
	
	
    public List<Map<String, Object>> getRouteSelectBox(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return partnerMapper.selectRouteBox(params);
	}
	
	
    public McusmaVO getSalrqtm(Map<String, Object> params, UserVo userInfo)  {
    	params.put("userData", userInfo);
        return partnerMapper.selectSalrqtm(params);
    }

}
