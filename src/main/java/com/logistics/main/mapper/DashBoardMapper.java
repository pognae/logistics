package com.logistics.main.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashBoardMapper { 
	
	//차트 프로시저
	Map<String, Object> callSpDsChart(Map<String, Object> param);
	
	Map<String, Object> selectWmPosdate(Map<String, Object> param);
}
