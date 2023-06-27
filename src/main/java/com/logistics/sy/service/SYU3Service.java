package com.logistics.sy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistics.sy.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.sy.domain.UserRoleHeaderDTO;
import com.logistics.sy.domain.UserRoleWarehouseDTO;
import com.logistics.sy.domain.UserVo;
import com.logistics.sy.domain.UserRoleProgramDTO;

@Service  
public class SYU3Service {
	
	@Autowired
	private UserMapper userMapper;
	
	
	public List<UserRoleHeaderDTO> getMasterRoleList(Map<String, Object> params, UserVo userInfo){
		params.put("userData", userInfo);
		List<UserRoleHeaderDTO> dataList = userMapper.selectRoleMaster(params);
		if(dataList == null) {
			return Collections.<UserRoleHeaderDTO>emptyList();
		}
		return dataList;
	}
	
	
	public List<UserRoleProgramDTO> getProgramRoleList(Map<String, Object> params, UserVo userInfo){
		List<UserRoleProgramDTO> dataList = userMapper.selectRoleProgram(params);
		if(dataList == null) {
			return Collections.<UserRoleProgramDTO>emptyList();
		}
		return dataList;
	}
	
	
	public List<UserRoleWarehouseDTO> getWarehouseRoleList(Map<String, Object> params, UserVo userInfo){
		List<UserRoleWarehouseDTO> dataList = userMapper.selectRoleWarehouse(params);
		if(dataList == null) {
			return Collections.<UserRoleWarehouseDTO>emptyList();
		}
		return dataList;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setSYU3Save(Map<String, Object> params, UserVo userInfo) {
		// Master
		List<UserRoleHeaderDTO> master = convertMsterDTO((ArrayList)params.get("master"));
		master.forEach(dto -> {
			dto.setLmouser(userInfo.getUseract());
			if(dto.getRowkey() == null) {
				dto.setCreuser(userInfo.getUseract());
				userMapper.insertRoleMaste(dto);
			}else {
				userMapper.updateRoleMaste(dto);
			}
		});
		
		// Program
		Map<String, Object> progrm = (Map)params.get("progrm");
		List<UserRoleProgramDTO> progrmUpdate = convertProgramDTO((ArrayList)progrm.get("updateList"));
		List<UserRoleProgramDTO> progrmAdd = convertProgramDTO((ArrayList)progrm.get("addList"));
		
		if(progrmAdd.size() > 0) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", progrmAdd);
			int inserCnt = userMapper.insertRoleProgram(map);
			if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
		}
		
		if(progrmUpdate.size() > 0) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", progrmUpdate);
			int updateCnt = userMapper.updateRoleProgram(map);
			if(updateCnt == 0) {
    			throw new InsertCheckedException();
    		}
		}
		
		// Warehouse
		Map<String, Object> warhse = (Map)params.get("warhse");
		List<UserRoleWarehouseDTO> warhseUpdate = convertWarehouseDTO((ArrayList)warhse.get("updateList"));
		List<UserRoleWarehouseDTO> warhseAdd = convertWarehouseDTO((ArrayList)warhse.get("addList"));
		
		if(warhseAdd.size() > 0) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", warhseAdd);
			int inserCnt = userMapper.insertRoleWarehouse(map);
			if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
		}
		
		if(warhseUpdate.size() > 0) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", warhseUpdate);
			int updateCnt = userMapper.updateRoleWarehouse(map);
			if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
		}
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setSYU3MasterDelete(Map<String, Object> params) {
		List<UserRoleHeaderDTO> list = convertMsterDTO((ArrayList)params.get("data"));
		list.forEach(dto -> {
			userMapper.deleteRoleMaste(dto);
	    	userMapper.deleteALLRoleProgram(dto);
	    	userMapper.deleteALLRoleWarehouse(dto);
		});
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setSYU3ProgramDelete(Map<String, Object> params) {
		List<UserRoleProgramDTO> list = convertProgramDTO((ArrayList)params.get("data"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		int deleteCnt = userMapper.deleteRoleProgram(map);
		if(deleteCnt == 0) {
    		throw new DeleteCheckedException();
    	}
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setSYU3WarehouseDelete(Map<String, Object> params) {
		List<UserRoleWarehouseDTO> list = convertWarehouseDTO((ArrayList)params.get("data"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		int deleteCnt = userMapper.deleteRoleWarehouse(map);
		if(deleteCnt == 0) {
    		throw new DeleteCheckedException();
    	}
	}
	
	private List<UserRoleHeaderDTO> convertMsterDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, UserRoleHeaderDTO.class));
    }
	
	private List<UserRoleProgramDTO> convertProgramDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, UserRoleProgramDTO.class));
    }
	
	private List<UserRoleWarehouseDTO> convertWarehouseDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, UserRoleWarehouseDTO.class));
    }
	
	
	public List<Map<String, Object>> getRoleSelectBox(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return userMapper.selectRoleBox(params);
	}
    
    
}
