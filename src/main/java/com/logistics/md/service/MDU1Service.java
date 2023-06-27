package com.logistics.md.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.configuration.error.procedureCheckedException;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MskcstVO;
import com.logistics.md.domain.MskmchVO;
import com.logistics.md.domain.MskuclVO;
import com.logistics.md.domain.MskuitVO;
import com.logistics.md.domain.MskupcVO;
import com.logistics.md.domain.MskupiVO;
import com.logistics.md.domain.MskusbVO;
import com.logistics.md.domain.MskuspVO;
import com.logistics.md.domain.MskuszVO;
import com.logistics.md.domain.MskusiVO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.DocumentMapper;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.sy.domain.UserVo;


@Service  
public class MDU1Service {

	/*
	 * 2022.06.02
	 * SWH
	 * [MDU2] 재고관리 서비스
	 */
	
	@Autowired
	private UnitsMapper unitsMapper;
	
	
	/**
	 * 2022.06.28
	 * MDU1 Search
	 **/
	
	
	public List<MskuspVO> getMskuspListSelect(Map<String, Object> param, UserVo userInfo) {
        
    	List<MskuspVO> dataList = Collections.emptyList();
    	param.put("userData", userInfo);
    	dataList = unitsMapper.selectMskuspList(param);
         
        return dataList;
	}
	
	public Map<String, Object> getMdu1InitDataSelect(Map<String, Object> param, UserVo userInfo){
		
		Map<String, Object> data 	= new HashMap<>();
		Map<String, Object> sqlMap 	= new HashMap<>();
		
		param.put("userData", userInfo);
		param.put("item", param);
		
		List<MskuspVO> mskuspList	= unitsMapper.selectMskuspList(param);
		param.put("skuskey", mskuspList.get(0).getSkuskey());
		
		param.put("item", param);
		List<MskuclVO> mskuclList 	= unitsMapper.selectMskuclList(param);
		List<MskuitVO> mskuitList 	= unitsMapper.selectMskuitList(param);
		List<MskuszVO> mskuszList 	= unitsMapper.selectMskuszList(param);
		List<MskusbVO> mskusbList 	= unitsMapper.selectMskusbList(param);
		List<MskupcVO> mskupcList 	= unitsMapper.selectMskupcList(param);
		List<MskmchVO> mskmchList 	= unitsMapper.selectMskmchList(param);
		List<MskcstVO> mskcstList 	= unitsMapper.selectMskcstList(param);
		List<MskusiVO> mskusiList 	= unitsMapper.selectMskusiList(param);
		List<MskupiVO> mskupiList 	= unitsMapper.selectMskupiList(param);
		
		data.put("skuskey", mskuspList.get(0).getSkuskey());
		data.put("skutkey", param.get("skutkey"));
		data.put("mskuspList", mskuspList);
		data.put("mskuclList", mskuclList);
		data.put("mskuitList", mskuitList);
		data.put("mskuszList", mskuszList);
		data.put("mskusbList", mskusbList);
		data.put("mskupcList", mskupcList);
		data.put("mskmchList", mskmchList);
		data.put("mskcstList", mskcstList);
		data.put("mskusiList", mskusiList);
		data.put("mskupiList", mskupiList);
		
		return data;
	}
	/**
	 * 2022.06.07
	 * MDU1 저장
	 **/
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> setMDU1Save(Map<String, Object> params, UserVo userInfo) {
		Map<String, Object> result = new HashMap<>();
		int resultType = 1;
		
		String mskuspSkuskey;
		String mskuspSkutkey;
		String skutkey = (String) params.get("skutkey");
		String skuskey = (String) params.get("skuskey");
		String savetyp = (String) params.get("savetyp");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    
	    Map<String, Object> dataMap = mapper.convertValue(params.get("list"), Map.class);
	    Map<String, Object> sqlMap 	= new HashMap<>();
	    
	    //====================================================
	    // MSKUSP | MSKUCL | MSKUIT | MSKUSB | MSKUPC | MSKUSZ
	    //====================================================
	    MskuspVO mskuspMap		 = dataMap.get("mskusp") != null ? mapper.convertValue(dataMap.get("mskusp"), MskuspVO.class) : null;
	    
	    List<MskuclVO> mskuclMapList = dataMap.get("mskucl") != null ? Arrays.asList(mapper.convertValue(dataMap.get("mskucl"), MskuclVO[].class)) : null;
	    List<MskuitVO> mskuitMapList = dataMap.get("mskuit") != null ? Arrays.asList(mapper.convertValue(dataMap.get("mskuit"), MskuitVO[].class)) : null;
	    List<MskuszVO> mskuszMapList = dataMap.get("mskusz") != null ? Arrays.asList(mapper.convertValue(dataMap.get("mskusz"), MskuszVO[].class)) : null;
	    List<MskusbVO> mskusbMapList = dataMap.get("mskusb") != null ? Arrays.asList(mapper.convertValue(dataMap.get("mskusb"), MskusbVO[].class)) : null;
	    List<MskupcVO> mskupcMapList = dataMap.get("mskupc") != null ? Arrays.asList(mapper.convertValue(dataMap.get("mskupc"), MskupcVO[].class)) : null;

	    List<MskmchVO> mskmchMapList = dataMap.get("mskmch") != null ? Arrays.asList(mapper.convertValue(dataMap.get("mskmch"), MskmchVO[].class)) : null;
	    List<MskusiVO> mskusiMapList = dataMap.get("mskusi") != null ? Arrays.asList(mapper.convertValue(dataMap.get("mskusi"), MskusiVO[].class)) : null;
	    List<MskupiVO> mskupiMapList = dataMap.get("mskupi") != null ? Arrays.asList(mapper.convertValue(dataMap.get("mskupi"), MskupiVO[].class)) : null;	    
	    //====================================================
	    // MSKCST - 원가 계산
	    //====================================================
	    List<MskcstVO> mskcstMapList = dataMap.get("mskcst") != null ? Arrays.asList(mapper.convertValue(dataMap.get("mskcst"), MskcstVO[].class)) : null;

	    //====================================================
	    //채번 sqlMap 세팅 
	    //====================================================
	    sqlMap.put("userData", userInfo);
	    sqlMap.put("skutkey", skutkey);
	    sqlMap.put("skuskey", skuskey);
	    sqlMap.put("item", mskuspMap);
	    
	    if("".equals(skutkey)) {
		    //SKUSKEY 시퀀스채번 + 키 세팅
		    String mskuspSkutKeySEQ	 = unitsMapper.selectMskuspSkutKey(sqlMap);
		    mskuspSkutkey = mskuspMap.getBrandnm()
		    		+ "." + mskuspMap.getStryear()
		    		+ "." + mskuspMap.getSeasona()
		    		+ "." + mskuspSkutKeySEQ;
		    
		    mskuspMap.setSkutkey(mskuspSkutkey);
		    
		    sqlMap.put("item", mskuspMap);
		    
		    String mskuspSkusKeySEQ	 = unitsMapper.selectMskuspSkusKey(sqlMap);
		    mskuspSkuskey = mskuspMap.getBrandnm()
		    		+ "." + mskuspMap.getStryear()
		    		+ "." + mskuspMap.getSeasona()
		    		+ "." + mskuspSkutKeySEQ
		    		+ "." + mskuspSkusKeySEQ;
	    }
	    else {

	    	MskuspVO checkData = unitsMapper.selectMskuspCheckList(sqlMap);

		    if(!"Y".equals(checkData.getSkuborn())) {
		    	unitsMapper.deleteUnitSkusKey(sqlMap);
		    }

		    mskuspMap.setSkutkey(skutkey);
		    sqlMap.put("item", mskuspMap);
	    	String mskuspSkusKeySEQ	 = unitsMapper.selectMskuspSkusKey(sqlMap);
	    	mskuspSkutkey = skutkey;
	    	mskuspSkuskey = skutkey+"."+mskuspSkusKeySEQ;
	    }
	    
	    
	    
	    
	    //mskuspMap
	    mskuspMap.setSkutkey(mskuspSkutkey);
	    mskuspMap.setSpdelyn("Y");
	    sqlMap.put("item", mskuspMap);
	    unitsMapper.updateMskusp(sqlMap);
	    
	    mskuspMap.setSkuskey(mskuspSkuskey);
	    mskuspMap.setSpdelyn("N");
	    sqlMap.put("item", mskuspMap);

	    //mskuclMapList
	    if(mskuclMapList != null) {
	    	for(int i=0; i<mskuclMapList.size(); i++) {
		    	mskuclMapList.get(i).setSkuskey(mskuspSkuskey);
		    	mskuclMapList.get(i).setSkutkey(mskuspSkutkey);
		    }
		    sqlMap.put("mskuclitem", mskuclMapList);
	    }
	    
	    
	    //mskuitMapList
	    if(mskuitMapList != null) {
		    for(int i=0; i<mskuitMapList.size(); i++) {
		    	mskuitMapList.get(i).setSkuskey(mskuspSkuskey);
		    	mskuitMapList.get(i).setSkutkey(mskuspSkutkey);
		    }

		    sqlMap.put("mskuititem", mskuitMapList);
	    }
	    
	    //mskuszMapList
	    if(mskuszMapList != null) {
		    for(int i=0; i<mskuszMapList.size(); i++) {
		    	mskuszMapList.get(i).setSkuskey(mskuspSkuskey);
		    	mskuszMapList.get(i).setSkutkey(mskuspSkutkey);
		    }
		    sqlMap.put("mskuszitem", mskuszMapList);
	    }
	    
	    //mskusbMapList
	    if(mskusbMapList != null) {
		    for(int i=0; i<mskusbMapList.size(); i++) {
		    	mskusbMapList.get(i).setSkuskey(mskuspSkuskey);
		    	mskusbMapList.get(i).setSkutkey(mskuspSkutkey);
		    }
		    sqlMap.put("mskusbitem", mskusbMapList);
	    }
	    //mskupcMapList
	    if(mskupcMapList != null) {
		    for(int i=0; i<mskupcMapList.size(); i++) {
		    	mskupcMapList.get(i).setSkuskey(mskuspSkuskey);
		    	mskupcMapList.get(i).setSkutkey(mskuspSkutkey);
		    }
		    sqlMap.put("mskupcitem", mskupcMapList);
	    }
	    
	    //mskmchMapList
	    if(mskmchMapList != null) {
		    for(int i=0; i<mskmchMapList.size(); i++) {
		    	mskmchMapList.get(i).setSkuskey(mskuspSkuskey);
		    	mskmchMapList.get(i).setSkutkey(mskuspSkutkey);
		    }
		    
		    sqlMap.put("mskmchitem", mskmchMapList);
	    }
	    
	    //mskcstMapList
	    if(mskcstMapList != null) {
		    for(int i=0; i<mskcstMapList.size(); i++) {
		    	mskcstMapList.get(i).setSkuskey(mskuspSkuskey);
		    	mskcstMapList.get(i).setSkutkey(mskuspSkutkey);
		    }
		    sqlMap.put("mskcstitem", mskcstMapList);
	    }
	    
	    //mskusiMapList
	    if(mskusiMapList != null) {
		    for(int i=0; i<mskusiMapList.size(); i++) {
		    	mskusiMapList.get(i).setSkuskey(mskuspSkuskey);
		    	mskusiMapList.get(i).setSkutkey(mskuspSkutkey);
		    }
		    sqlMap.put("mskusiitem", mskusiMapList);
	    }
	    
	    //mskupiMapList
	    if(mskupiMapList != null) {
		    for(int i=0; i<mskupiMapList.size(); i++) {
		    	mskupiMapList.get(i).setSkuskey(mskuspSkuskey);
		    	mskupiMapList.get(i).setSkutkey(mskuspSkutkey);
		    }
		    sqlMap.put("mskupiitem", mskupiMapList);
	    }
	    
	    //mskcstMapList
	    if(mskcstMapList != null) {
		    for(int i=0; i<mskcstMapList.size(); i++) {
		    	mskcstMapList.get(i).setSkuskey(mskuspSkuskey);
		    	mskcstMapList.get(i).setSkutkey(mskuspSkutkey);
		    }
		    sqlMap.put("mskcstitem", mskcstMapList);
	    }

	    int resultMskusp = unitsMapper.insertMskusp(sqlMap);
	    
	    if(mskuclMapList != null && !mskuclMapList.isEmpty()) {
	    	int resultMskucl = unitsMapper.insertMskucl(sqlMap);
	    }
	    
	    if(mskuitMapList != null && !mskuitMapList.isEmpty()) {
	    	int resultMskuit = unitsMapper.insertMskuit(sqlMap);
	    }
	    
	    if(mskuszMapList != null && !mskuszMapList.isEmpty()) {
	    	int resultMskusz = unitsMapper.insertMskusz(sqlMap);
	    }
	    
	    if(mskusbMapList != null && !mskusbMapList.isEmpty()) {
	    	int resultMskusb = unitsMapper.insertMskusb(sqlMap);
	    }
	    
	    if(mskupcMapList != null && !mskupcMapList.isEmpty()) {
	    	int resultMskupc = unitsMapper.insertMskupc(sqlMap);
	    }
	    
	    if(mskmchMapList != null && !mskmchMapList.isEmpty()) {
	    	int resultMskmch = unitsMapper.insertMskmch(sqlMap);
	    }
	    
	    if(mskcstMapList != null && !mskcstMapList.isEmpty()) {
	    	int resultMskcst = unitsMapper.insertMskcst(sqlMap);
	    }
	    
	    if(mskusiMapList != null && !mskusiMapList.isEmpty()) {
	    	int resultMskusi = unitsMapper.insertMskusi(sqlMap);
	    }
	    
	    if(mskupiMapList != null && !mskupiMapList.isEmpty()) {
	    	int resultMskupi = unitsMapper.insertMskupi(sqlMap);
	    }
	    
	    result.put("skutkey", mskuspSkutkey);
	    result.put("skuskey", mskuspSkuskey);
	    result.put("resultType", resultType);
	    
		return result;
	}

	
	/**
	 * 2022.08.30
	 * MDU6 조회
	 **/
	public List<MskuspVO> getMdu6ListSelect(Map<String, Object> param, UserVo userInfo){
		param.put("userData", userInfo);
		return unitsMapper.selectMdu6MskuspList(param);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setMdu6Save(Map<String, Object> param, UserVo userInfo) {
		Map<String, Object> result = new HashMap<>();
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    
	    Map<String, Object> dataMap = mapper.convertValue(param.get("list"), Map.class);
	    Map<String, Object> sqlMap = new HashMap<>();
	    
	    List<MskuspVO> updateList 	= new ArrayList<>(Arrays.asList(mapper.convertValue(dataMap.get("updateList"), MskuspVO[].class)));

	    //UserSessionData param put
	    sqlMap.put("userData", userInfo);

	    /**
	     * 실행순서
	     * 1.Call Procedure
	     * 
	     **/
	    
	    if(updateList != null && !updateList.isEmpty()) {
	    	sqlMap.put("updateList", updateList);

	    	for(int i=0; i<updateList.size(); i++)
	    	{
	    		sqlMap.put("item", updateList.get(i));
	    		unitsMapper.callSpMdMakesku(sqlMap);
		    	if(!sqlMap.get("O_ORESULT").equals(0)) {
	        		throw new procedureCheckedException((String) sqlMap.get("O_OMSGKEY"));
	        	}
	    	}
	    }
	}
}
