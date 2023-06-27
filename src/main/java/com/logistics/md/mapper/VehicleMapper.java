package com.logistics.md.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MshrhdVO;
import com.logistics.md.domain.MshritVO;
import com.logistics.md.domain.MvhcmaVO;

@Mapper
public interface VehicleMapper {
	
	//mdv : 차량 관리
	List<MvhcmaVO> selectMvhcma(Map<String, Object> params);
	int updateMvhcma(HashMap<String, Object> map);
	int insertMvhcma(HashMap<String, Object> map);

	
	//mdv2 헤더 조회
	List<MshrhdVO> selectMshrhdList(Map<String, Object> params);
	
	//mdv2 item 조회
	List<MshritVO> selectMshritList(Map<String, Object> params);
	
	int deleteMshrhd(HashMap<String, Object> map);
	int deleteMshrit(HashMap<String, Object> map);
	
	//mdv2 
	String getShtrtky();
	
	//mdv2 헤더 추가
	int insertMshrhd(HashMap<String, Object> map);
	
	//mdv2 헤더 수정
	int updateMshrhd(HashMap<String, Object> map);
	
	//mdv2 아이템 추가
	int insertMshrit(HashMap<String, Object> map);	
	
	//mdv2 아이템 수정
	int updateMshrit(HashMap<String, Object> map);
	
}
