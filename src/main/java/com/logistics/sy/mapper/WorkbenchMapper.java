package com.logistics.sy.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.logistics.sy.domain.SloghiVO;
import com.logistics.sy.domain.SprogmVO;
import com.logistics.sy.domain.SprthiVO;
import com.logistics.sy.domain.SupahiVO;

@Mapper
public interface WorkbenchMapper { 
 
	List<SprogmVO> selectSyw1List(Map<String, Object> map);
	int insertSprogm(HashMap<String, Object> map);
	int updateSprogm(HashMap<String, Object> map);
	int deleteSprogm(HashMap<String, Object> map);
	
	List<SupahiVO> selectSyw2List(Map<String, Object> map);
	
	List<SloghiVO> selectSyw3List(Map<String, Object> map);
	
	List<SprthiVO> selectSyw4List(Map<String, Object> map);
}