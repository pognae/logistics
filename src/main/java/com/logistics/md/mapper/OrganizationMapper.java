package com.logistics.md.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MaremaDTO;
import com.logistics.md.domain.McommaVO;
import com.logistics.md.domain.MlocmaVO;
import com.logistics.md.domain.MwarmaVO;
import com.logistics.md.domain.MzonmaVO;

/* xml mapper :  mybatis header */
@Mapper
public interface OrganizationMapper {
    // ***********************************************
    // Company : mcomma
    // ***********************************************
	List<McommaVO> selectMcommaList(Map<String, Object> paramMap);
	int updateMcomma(HashMap<String, Object> map);	
	int insertMcomma(HashMap<String, Object> map);
	void sp_mcomma2_test(Map<String, Object> map);
		
    // ***********************************************
    // Warehouse : mwarma
    // ***********************************************
	//mdo2 : 창고 조회
	List<MwarmaVO> selectMwarmaList(Map<String, Object> paramMap);
	//mdo2 : 창고 생성
	int insertMwarma(HashMap<String, Object> map);
	//mdo2 : 창고 수정
	int updateMwarma(HashMap<String, Object> list);
	//mdo2 : 창고 SelectBox 조회
	List<Map<String, Object>> selectWarehouseBox(Map<String, Object> map);
	//mdo2 : 사용자별 청고 SelectBox 조회
	List<Map<String, Object>> selectWarehouseUserBox(Map<String, Object> map);
	
    // ***********************************************
    // Area : marema
    // ***********************************************
	//mdo3 : 영역 조회
	List<MaremaDTO> selectAreaList(Map<String, Object> map);
	//mdo3 : 영역 수정
	int updateArea(HashMap<String, Object> map);
	//mdo3 : 영역 등록
	int insertArea(HashMap<String, Object> map);
	//mdo3 : 영역 SelectBox 조회
	List<Map<String, Object>> selectAreaBox(Map<String, Object> map);
		
    // ***********************************************
    // Zone : mzonma
    // ***********************************************
	//mdo4 : 존 조회  
	List<MzonmaVO> selectMzonmaList(Map<String, Object> paramMap);
	//mdo4 : 존 생성
	int insertMzonma(HashMap<String, Object> map);
	//mdo4 : 존 수정
	int updateMzonma(HashMap<String, Object> map);
	//mdo4 : 영역 SelectBox 조회
	List<Map<String, Object>> selectZoneBox(Map<String, Object> params);
	
    // ***********************************************
    // Location : mlocma
    // ***********************************************  
	List<MlocmaVO> selectMlocmaList(Map<String, Object> params);
	int updateMlocma(Map<String, Object> paramMap);	
	int insertMlocma(Map<String, Object> paramMap);
	List<Map<String, Object>> selectLocationBox(Map<String, Object> map);
	List<MlocmaVO> selectMlocma(Map<String, Object> params);
}