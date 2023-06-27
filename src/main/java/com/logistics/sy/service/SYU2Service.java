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
import com.logistics.sy.domain.UserMenuDTO;
import com.logistics.sy.domain.UserVo;
import com.logistics.sy.mapper.UserMapper;

@Service  
public class SYU2Service {
	
	@Autowired
	private UserMapper userMapper;
	
	
    public List<UserMenuDTO> getHeaderMenuList(Map<String, Object> params, UserVo userInfo)  {
		params.put("userData", userInfo);
		List<UserMenuDTO> dataList = userMapper.selectHeaderMenu(params);
    	if(dataList == null) {
    		return Collections.<UserMenuDTO>emptyList();
    	}
        return dataList;
    }
	
	public List<UserMenuDTO> getItemMenuList(Map<String, Object> params, UserVo userInfo)  {
		List<UserMenuDTO> dataList = userMapper.selectItemMenu(params);
    	if(dataList == null) {
    		return Collections.<UserMenuDTO>emptyList();
    	}
        return dataList;
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    public void setSYU2HeadDelete(Map<String, Object> param) {
		List<UserMenuDTO> head = convertDTO((ArrayList) param.get("data"));
	    HashMap<String, Object> hmap = new HashMap<>();
	    hmap.put("list", head);
	    int deleteHeadCnt = userMapper.deleteHeaderMenu(hmap);
	    if(deleteHeadCnt == 0) {
    		throw new DeleteCheckedException();
    	}
	    
	    List<UserMenuDTO> item = new ArrayList<>();
	    UserMenuDTO vo = new UserMenuDTO();
	    vo.setCompkey(head.get(0).getCompkey());
	    vo.setMenukey(head.get(0).getMenukey());
	    item.add(vo);
	    
	    HashMap<String, Object> imap = new HashMap<>();
	    imap.put("list", item);
	    int deleteItemCnt = userMapper.deleteItemMenu(imap);
	    if(deleteItemCnt == 0) {
	    	throw new DeleteCheckedException();
	    }
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    public void setSYU2ItemDelete(Map<String, Object> param) {
		List<UserMenuDTO> list = convertDTO((ArrayList) param.get("data"));
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("list", list);
	    int deleteItemCnt = userMapper.deleteItemMenu(map);
	    if(deleteItemCnt == 0) {
	    	throw new DeleteCheckedException();
	    }
    }
	
	
	@Transactional(rollbackFor = Exception.class)
	public void setSYU2Save(Map<String, Object> param, UserVo userInfo) {
		List<UserMenuDTO> head = convertDTO((ArrayList)param.get("head"));
		Map item = (Map) param.get("item");
		List<UserMenuDTO> updateList = convertDTO((ArrayList)item.get("updateList"));
		List<UserMenuDTO> addList = convertDTO((ArrayList)item.get("addList"));
		
		head.forEach(dto -> {
			dto.setLmouser(userInfo.getUseract());
			if(dto.getRowkey() == null) {
				dto.setCreuser(userInfo.getUseract());
				int inserCnt = userMapper.insertHeaderMenu(dto);
				if(inserCnt == 0) {
	    			throw new InsertCheckedException();
	    		}
			}else {
				int updateCnt = userMapper.updateHeaderMenu(dto);
				if(updateCnt == 0) {
	        		throw new UpdateCheckedException();
	        	}
			}
    	});
		
		//item List
		if(!addList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", addList);
			int inserCnt = userMapper.insertItemMenu(map);
			if(inserCnt == 0) {
    			throw new InsertCheckedException();
    		}
		}
		if(!updateList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
	    	map.put("list", updateList);
	    	int updateCnt = userMapper.updateItemMenu(map);
	    	if(updateCnt == 0) {
        		throw new UpdateCheckedException();
        	}
		}
	}
	
	
	private List<UserMenuDTO> convertDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, UserMenuDTO.class));
    }
    
    
    public List<Map<String, Object>> getMenuSelectBox(Map<String, Object> params, UserVo userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return userMapper.selectMenuBox(params);
	}
	
}
