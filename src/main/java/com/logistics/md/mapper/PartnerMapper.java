package com.logistics.md.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.McusmaVO;
import com.logistics.md.domain.McuswhVO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MptnmaVO;

@Mapper
public interface PartnerMapper {
	List<MowrmaDTO> selectMowrma(Map<String, Object> paramList);

	int insertMowrma(HashMap<String , Object> map);
	int updateMowrma(HashMap<String , Object> map);
	int deleteMowrma(HashMap<String , Object> map);
	List<Map<String, Object>> selectOwnerBox(Map<String, Object> map);

	List<McusmaVO> getCusttyp(Map<String, Object> param);
	List<McusmaVO> getMcusmaList(Map<String, Object> map);
	
	int insertMcusma(McusmaVO param);
	int updateMcusma(HashMap<String, Object> map);
	List<Map<String, Object>> selectCustomerBox(Map<String, Object> map);
	List<Map<String, Object>> selectCustWareBox(Map<String, Object> map);
	List<Map<String, Object>> selectCustWareBoxGroup(Map<String, Object> map);
	
	//mdp2 :로직 파트너 조회	
	List<MptnmaVO> selectMptnmaList(Map<String, Object> params);
	//mdp2 :로직 파트너 생성
	int insertMptnma(HashMap<String, Object> map);
	//mdp2 :로직 파트너 수정	
	int updateMptnma(HashMap<String, Object> map);
	List<MptnmaVO> getPtnrtyp(String compkey);
	String getPtnrkey();
	
	//Route 고객 조회
	List<Map<String, Object>> selectRouteBox(Map<String, Object> params);
	
	McusmaVO selectSalrqtm(Map<String, Object> map);

	List<Map<String, Object>> getMdp2SelectBox(Map<String, Object> param);
	List<Map<String, Object>> getMdp2SelectBoxByAll(Map<String, Object> param);
	
	//mdp4 :고객별 관리창고
	List<McusmaVO> getMdp4McusmaList(Map<String, Object> param);
	List<McuswhVO> getMdp4McuswhList(Map<String, Object> param);
	int saveMcuswhList(HashMap<String, Object> sqlMap);
	int updateMcuswhList(HashMap<String, Object> sqlMap);
	int deleteMcuswhList(HashMap<String, Object> sqlMap);
	String selectMcuswh(Map<String, Object> param);

	//mdp5 : Customer 소매
	List<McusmaVO> getMdp5List(Map<String, Object> param);
	int insertMptnmaByRetail(HashMap<String, Object> ptnMap);
	String selectFnCustkey(Map<String, Object> param);
	void updateMcusmaList(Map<String, Object> call);
	
	//oml1 : 금일 내역 조회
	List<Map<String, Object>> selectCustomerBoxByOml1(Map<String, Object> param);




}
