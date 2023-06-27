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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.procedureCheckedException;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MskuurVO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@Service  
@RequiredArgsConstructor
public class MDU2Service {

	/*
	 * 2022.06.02
	 * SWH
	 * [MDU2] 재고관리 서비스
	 */
	
	@Autowired
	private UnitsMapper unitsMapper;
	
	@Autowired
	private OrganizationMapper organizationMapper;
	/**
	 * 2022.06.07
	 * MDU2 Search
	 **/
	
	public List<MskuwcDTO> getMdu2ListSelect(Map<String, Object> params, UserVo userInfo){
		params.put("userData", userInfo);
		List<MskuwcDTO> result = unitsMapper.selectMskuwcList(params);
		if(result == null) {
			return Collections.<MskuwcDTO>emptyList();
		}
		return result;
	}
	
	
	public List<MskuurVO> getWzGrid01(Map<String, Object> params, UserVo userInfo){
		params.put("userData", userInfo);
		List<MskuurVO> result = unitsMapper.selectWzGrid01(params);
		if(result == null) {
			return Collections.<MskuurVO>emptyList();
		}
		return result;
	}
	
	/**
	 * 2022.06.07
	 * MDU2 저장
	 **/
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> setMDU2SkuCreateSave(Map<String, Object> params, UserVo userInfo) {
		
		Map<String, Object> result = new HashMap<>();
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    
	    Map<String, Object> dataMap = mapper.convertValue(params.get("param"), Map.class);
	    Map<String, Object> sqlMap = new HashMap<>();
	    
	    List<MskuwcDTO> mskuwcList = new ArrayList<>();
	    List<MskuwcDTO> mskuwcSpList = new ArrayList<>();
	    
	    //UserSessionData param put
	    sqlMap.put("userData", userInfo);
	    sqlMap.put("compkey", userInfo.getCompkey());
	    List<Map<String, Object>> warehouseList = organizationMapper.selectWarehouseBox(sqlMap);
	    
	    String skutkey 			= "";
	    
	    ArrayList sksizes 		= (ArrayList) dataMap.get("sksizes");
	    ArrayList skugr04 		= (ArrayList) dataMap.get("skugr04");
	    
	    for(int i=0; i<skugr04.size(); i++) {
	    	for(int j=0; j<sksizes.size(); j++) {
	    		for(var k=0; k<warehouseList.size(); k++) {
	    			
	    			if("".equals(skutkey)) {
	    				sqlMap.put("item", dataMap);
	    				skutkey = unitsMapper.selectMskuspSkutKey(sqlMap);
	    			}	    			
	    			
		    		 MskuwcDTO mskuwcDTO = new MskuwcDTO();
		    		 
		    		 mskuwcDTO.setCompkey(userInfo.getCompkey());
		    		 mskuwcDTO.setWarekey((String) warehouseList.get(k).get("combovl"));
		    		 mskuwcDTO.setSkutkey((String) dataMap.get("skutkey")+"."+skutkey);
		    		 mskuwcDTO.setSkuskey((String) dataMap.get("skutkey")+"."+skutkey+"."+skugr04.get(i)+"."+sksizes.get(j));
		    		 mskuwcDTO.setSkumkey((String) dataMap.get("skutkey")+"."+skutkey+"."+skugr04.get(i)+"."+sksizes.get(j));
		    		// mskuwcDTO.setSkudesc((String) dataMap.get("skudesc")+"."+skutkey+"."+skugr04.get(i)+"."+sksizes.get(j));
		    		 mskuwcDTO.setSkudesc((String) dataMap.get("skudesc")+"."+skugr04.get(i)+"."+sksizes.get(j));
		    		 mskuwcDTO.setSkutdsc((String) dataMap.get("skudesc"));
		    		 mskuwcDTO.setSkunote((String) dataMap.get("skunote"));
		    		 mskuwcDTO.setSkugr01((String) dataMap.get("skugr01"));
		    		 mskuwcDTO.setSkugr02((String) dataMap.get("seasona"));
		    		 mskuwcDTO.setSkugr03((String) sksizes.get(j));
		    		 mskuwcDTO.setSkugr04((String) skugr04.get(i));
		    		 mskuwcDTO.setSkualt1((String) dataMap.get("skualt1"));
		    		 mskuwcDTO.setSkualt2((String) dataMap.get("stryear"));
		    		 mskuwcDTO.setSkuat01((String) dataMap.get("designe"));
		    		 mskuwcDTO.setOwnerky((String) dataMap.get("ownerky"));
		    		 mskuwcDTO.setBrandcd((String) dataMap.get("brandnm"));
		    		 mskuwcDTO.setMixrato((String) dataMap.get("mixrato"));
		    		 mskuwcDTO.setRetexky((String) dataMap.get("retexky"));
		    		 mskuwcDTO.setItemnt1((String) dataMap.get("itemnt1"));
		    		 mskuwcDTO.setItemnt2((String) dataMap.get("itemnt2"));
		    		 
		    		 mskuwcDTO.setItmcost(Integer.parseInt(dataMap.get("itmcost").toString()));
		    		 mskuwcDTO.setSelpric(Integer.parseInt(dataMap.get("selpric").toString()));
		    		 
		    		 mskuwcDTO.setVendkey((String) dataMap.get("vendkey"));
		    		 mskuwcDTO.setSproprc(Integer.parseInt(dataMap.get("sproprc").toString()));
		    		 mskuwcDTO.setSpronte((String) dataMap.get("spronte"));

		    		 mskuwcDTO.setTollvd1((String) dataMap.get("tollvd1"));
		    		 mskuwcDTO.setTollfe1(Integer.parseInt(dataMap.get("tollfe1").toString()));
		    		 mskuwcDTO.setTollnt1((String) dataMap.get("tollnt1"));
		    		 
		    		 mskuwcDTO.setTollvd2((String) dataMap.get("tollvd2"));
		    		 mskuwcDTO.setTollfe2(Integer.parseInt(dataMap.get("tollfe2").toString()));
		    		 mskuwcDTO.setTollnt2((String) dataMap.get("tollnt2"));
		    		 
		    		 mskuwcDTO.setTollvd3((String) dataMap.get("tollvd3"));
		    		 mskuwcDTO.setTollfe3(Integer.parseInt(dataMap.get("tollfe3").toString()));
		    		 mskuwcDTO.setTollnt3((String) dataMap.get("tollnt3"));
		    		 
		    		 mskuwcDTO.setTollvd4((String) dataMap.get("tollvd4"));
		    		 mskuwcDTO.setTollfe4(Integer.parseInt(dataMap.get("tollfe4").toString()));
		    		 mskuwcDTO.setTollnt4((String) dataMap.get("tollnt4"));
		    		 
		    		 
		    		 //mskuwcDTO.setSkunote((String) dataMap.get("skunote"));
		    		 //mskuwcDTO.setSkudcex((String) dataMap.get("skudcex"));
		    		 //mskuwcDTO.setCuspric(Integer.parseInt(dataMap.get("cuspric").toString()));
		    		 //mskuwcDTO.setRtspric(Integer.parseInt(dataMap.get("rtspric").toString()));
		    		 
		    		 mskuwcList.add(mskuwcDTO);
		    		 
		    		 if(k==0) {
		    			 mskuwcSpList.add(mskuwcDTO);
		    		 }
	    		}
	    	}
	    }
	    
	    
	    sqlMap.put("insertList", mskuwcList);
	    
	    Map<String, Object> checkData = unitsMapper.selectMskuwcCheck(sqlMap);
	    
	    int count = Integer.parseInt(checkData.get("CNT").toString());
	    
	    if(count > 0) {
	    	result.put("status", "DUPLICATION");
	    }
	    else {
	    	if(!mskuwcList.isEmpty()) {
		    	unitsMapper.insertMskuwc(sqlMap);
		    	result.put("status", "OK");
		    	
		    	
		    	for(int i=0; i<mskuwcSpList.size(); i++) {
		    		sqlMap.put("item", mskuwcSpList.get(i));
		    		unitsMapper.callSpMdSkuexky(sqlMap);
		    	}
		    }
	    }
	    
	    return result;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public boolean setMDU2Save(Map<String, Object> params, UserVo userInfo) {
		
		boolean result = true;
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    
	    Map<String, Object> dataMap = mapper.convertValue(params.get("list"), Map.class);
	    Map<String, Object> sqlMap = new HashMap<>();
	    
	    List<MskuwcDTO> updateList 	= Arrays.asList(mapper.convertValue(dataMap.get("updateList"), MskuwcDTO[].class));
	    List<MskuwcDTO> oldList 	= Arrays.asList(mapper.convertValue(dataMap.get("oldList"), MskuwcDTO[].class));
	    
	    //UserSessionData param put
	    sqlMap.put("userData", userInfo);

	    /**
	     * 실행순서
	     * 1.Update
	     **/
	    
	    //updateList 는 oldList 에서 키데이터 세팅 필요하여 DTO에 객체데이터 초기화 로직 구현
	    if(!updateList.isEmpty()) {
	    	sqlMap.put("updateList", updateList);
	    	unitsMapper.updateMskuwc(sqlMap);
	    	
	    	for(int i=0; i<updateList.size(); i++) {
	    		sqlMap.put("item", updateList.get(i));
	    		unitsMapper.callSpMdSkuexky(sqlMap);
	    	}
	    	
	    }
	    
		return result;
	}
	
	/**
	 * 2022.06.177
	 * MDU2 Image 저장 (모달팝업)
	 **/
	
	@Transactional(rollbackFor = Exception.class)
	public boolean setMDU2SaveImage(Map<String, Object> params, UserVo userInfo) {
		
		boolean result = true;
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    
	    Map<String, Object> dataMap = mapper.convertValue(params.get("list"), Map.class);
	    Map<String, Object> sqlMap = new HashMap<>();
	    
	    List<MskuwcDTO> updateList 	= Arrays.asList(mapper.convertValue(dataMap.get("updateList"), MskuwcDTO[].class));
	    
	    sqlMap.put("userData", userInfo);

	    /**
	     * 실행순서
	     * 1.Update
	     **/
	    
	    if(!updateList.isEmpty()) {
	    	sqlMap.put("updateList", updateList);
	    	unitsMapper.updateMskuwcImage(sqlMap);
	    }
	    
		return result;
	}
	
	
	public List<Map<String, Object>> getSkuwcSelectBoxQty(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		if(params.containsKey("skumkey")) {
			String skumkey = (String) params.get("skumkey");
	    	if(skumkey != null) {
	    		List<String> skumkeys = Arrays.asList(skumkey.split(","));
	    		params.put("skumkeys", skumkeys);
	    	}
		}
		return unitsMapper.selectSkuwcBoxQty(params);
	}
	
	
	public List<Map<String, Object>> getSkuwcSelectBox(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		if(params.containsKey("skumkey")) {
			String skumkey = (String) params.get("skumkey");
	    	if(skumkey != null) {
	    		List<String> skumkeys = Arrays.asList(skumkey.split(","));
	    		params.put("skumkeys", skumkeys);
	    	}
		}
		return unitsMapper.selectSkuwcBox(params);
	}
	
	
	public List<Map<String, Object>> getSkuwcOms2SB(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		if(params.containsKey("skumkey")) {
			String skumkey = (String) params.get("skumkey");
	    	if(skumkey != null) {
	    		List<String> skumkeys = Arrays.asList(skumkey.split(","));
	    		params.put("skumkeys", skumkeys);
	    	}
		}
		return unitsMapper.selectSkuwcOms2SB(params);
	}
	
	
	public List<Map<String, Object>> getSkuwcSelectBoxOmw1(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		if(params.containsKey("skumkey")) {
			String skumkey = (String) params.get("skumkey");
	    	if(skumkey != null) {
	    		List<String> skumkeys = Arrays.asList(skumkey.split(","));
	    		params.put("skumkeys", skumkeys);
	    	}
		}
		return unitsMapper.selectSkuwcBoxOmw1(params);
	}

	
	public List<Map<String, Object>> getSkucSelectBoxByWarekey(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		return unitsMapper.selectSkwucBoxByWarekey(param);
	}

	
	public Map<String, Object> getSkucSelectBoxtByWarekey(Map<String, Object> param, UserVo userInfo) {
		param.put("compkey", userInfo.getCompkey());
		return unitsMapper.selectSkwucBoxtByWarekey(param);
	}
	
	
	
	
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> setMDU7Save(Map<String, Object> params, UserVo userInfo) {
		
		Map<String, Object> result = new HashMap<>();
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    
	    Map<String, Object> dataMap = mapper.convertValue(params.get("list"), Map.class);
	    Map<String, Object> sqlMap = new HashMap<>();
	    
	    List<MskuwcDTO> insertList 	= Arrays.asList(mapper.convertValue(dataMap.get("addList"), MskuwcDTO[].class));
	    
	    String twarekey = (String) dataMap.get("targetWtk");
	    String tskumkey = (String) dataMap.get("targetSku");
	    
	    List<MskuwcDTO> mskuwcList = new ArrayList<>();
	    List<MskuwcDTO> mskuwcSpList = new ArrayList<>();
	    
	    //UserSessionData param put
	    sqlMap.put("userData", userInfo);
	    
	    sqlMap.put("insertList", insertList);
	    Map<String, Object> checkData = unitsMapper.selectMskuwcCheck(sqlMap);
	    
	    int count = Integer.parseInt(checkData.get("CNT").toString());
	    
	    if(count > 0) {
	    	result.put("status", "DUPLICATION");
	    }
	    else
	    {
	    	List<Map<String, Object>> warehouseList = organizationMapper.selectWarehouseBox(sqlMap);
	    	
	    	sqlMap.put("warekey", twarekey);
	    	sqlMap.put("skumkey", tskumkey);
	    	
	    	for(int i=0; i<insertList.size(); i++) {
		    	for(int j=0; j<warehouseList.size(); j++) {
		    		MskuwcDTO mskuwcDTO = unitsMapper.selectMskuwcData(sqlMap);
		    		mskuwcDTO.setWarekey((String) warehouseList.get(j).get("combovl"));
		    		mskuwcDTO.setSkumkey(insertList.get(i).getSkumkey());
		    		mskuwcDTO.setSkudesc(insertList.get(i).getSkudesc());
		    		mskuwcDTO.setSkuskey(insertList.get(i).getSkuskey());  		
		    		
		    		mskuwcList.add(mskuwcDTO);
		    		
		    		if(j==0) {
		    			mskuwcSpList.add(mskuwcDTO);
		    		}
				}
		    }
	    	
	    	sqlMap.put("insertList", mskuwcList);
	    	
	    	if(!mskuwcList.isEmpty()) {
		    	int resultCount = unitsMapper.insertMskuwc(sqlMap);
		    	
		    	if(resultCount == 0) {
					throw new InsertCheckedException();
				}
		    	
		    	result.put("status", "OK");
	    	}
	    	
	    	for(int i=0; i<mskuwcSpList.size(); i++) {
	    		sqlMap.put("item", mskuwcSpList.get(i));
	    		unitsMapper.callSpMdSkuexky(sqlMap);
	    		
	    		if(!sqlMap.get("O_ORESULT").equals(0)) {
	        		throw new procedureCheckedException((String) sqlMap.get("O_OMSGKEY"));
	        	}
	    	}
	    }	 
	    
	    return result;
	}
}
