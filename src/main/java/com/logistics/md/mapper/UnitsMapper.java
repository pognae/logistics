package com.logistics.md.mapper;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MpakmaVO;
import com.logistics.md.domain.MskcstVO;
import com.logistics.md.domain.MskmchVO;
import com.logistics.md.domain.MskuclVO;
import com.logistics.md.domain.MskuitVO;
import com.logistics.md.domain.MskupcVO;
import com.logistics.md.domain.MskupiVO;
import com.logistics.md.domain.MskusbVO;
import com.logistics.md.domain.MskusiVO;
import com.logistics.md.domain.MskuspVO;
import com.logistics.md.domain.MskuszVO;
import com.logistics.md.domain.MskuurVO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MtutmaVO;
import com.logistics.md.domain.MuommaVO;

/* xml mapper :  mybatis header */
@Mapper
public interface UnitsMapper {

	/**
	 * MDU1
	 **/
	MskuspVO selectMskuspCheckList(Map<String, Object> paramMap);
	
	List<MskuspVO> selectMskuspList(Map<String, Object> paramMap);
	List<MskuclVO> selectMskuclList(Map<String, Object> paramMap);
	List<MskuitVO> selectMskuitList(Map<String, Object> paramMap);
	List<MskuszVO> selectMskuszList(Map<String, Object> paramMap);
	List<MskusbVO> selectMskusbList(Map<String, Object> paramMap);
	List<MskupcVO> selectMskupcList(Map<String, Object> paramMap);
	List<MskmchVO> selectMskmchList(Map<String, Object> paramMap);
	List<MskcstVO> selectMskcstList(Map<String, Object> paramMap);
	List<MskusiVO> selectMskusiList(Map<String, Object> paramMap);
	List<MskupiVO> selectMskupiList(Map<String, Object> paramMap);

	//MDU1 : 작업지시서 - 작업지시서 정보 ALL DELETE
	int deleteUnitSkusKey(Map<String, Object> parmaMap);
		
	//MDU1 : 작업지시서 SEQ 채번
	String selectMskuspSkutKey(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 SEQ 채번
	String selectMskuspSkusKey(Map<String, Object> parmaMap);
	
	//MDU1 : 작업지시서 INSERT
	int insertMskusp(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 - 컬러 INSERT
	int insertMskucl(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 - 원단정보 INSERT
	int insertMskuit(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 - 제품 사이즈 INSERT
	int insertMskusz(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 - 부자재 INSERT
	int insertMskusb(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 - 공정 INSERT
	int insertMskupc(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 - 원단 조합 INSERT
	int insertMskmch(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 - 상세정보(원가계산) INSERT
	int insertMskcst(Map<String, Object> parmaMap);
	
	//MDU1 : 작업지시서 - 재단사이즈 INSERT
	int insertMskusi(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 - 공정별 작업이미지 INSERT
	int insertMskupi(Map<String, Object> parmaMap);
	
	//MDU1 : 작업지시서 UPDATE
	int updateMskusp(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 - 컬러 UPDATE
	int updateMskucl(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 - 원단정보 UPDATE
	int updateMskuit(Map<String, Object> parmaMap);
	//MDU1 : 작업지시서 - 사이즈정보 UPDATE
	int updateMskusz(Map<String, Object> parmaMap);
	
	
	
	
	/**
	 * MDU2
	 **/
	Map<String, Object> callSpMdSkuexky(Map<String, Object> param);
	
	//MDU2 : 상품조회
	List<MskuwcDTO> selectMskuwcList(Map<String, Object> paramMap);
	
	//MDU2 : 상품체크
	Map<String, Object> selectMskuwcCheck(Map<String, Object> paramMap);
	MskuwcDTO selectMskuwcData(Map<String, Object> paramMap);
	//MDU2 : 상품 INSERT
	int insertMskuwc(Map<String, Object> paramMap);
	
	//MDU2 : 상품이미지 UPDATE
	int updateMskuwcImage(Map<String, Object> paramMap);
	
	//MDU2 : 상품 관리 UPDATE
	int updateMskuwc(Map<String, Object> paramMap);
	
	
	// Skuwc SelectBox 조회 (상품명 + 수량)
	List<Map<String, Object>> selectSkuwcBoxQty(Map<String, Object> map);
	
	//MDU2 : Skuwc SelectBox 조회(공장별)
	List<Map<String, Object>> selectSkuwcBox(Map<String, Object> map);
	
	//MDU2 : OMS2 주문등록 화면용
	List<Map<String, Object>> selectSkuwcOms2SB(Map<String, Object> map);
	
	//MDU2 : Skuwc SelectBox 조회 OMB1
	List<Map<String, Object>> selectSkuwcBoxOmw1(Map<String, Object> map);
		
	/**
	 * MDU3
	 **/
	List<MuommaVO> selectMuomma(Map<String, Object> params);
	List<Map<String, Object>> selectUnitBox(Map<String, Object> params);		
	int updateMuomma(HashMap<String, Object> map);
	int insertMuomma(HashMap<String, Object> map);
	
	//mdu4	
	int insertMtutma(HashMap<String, Object> map);
	int updateMtutma(HashMap<String, Object> map);
	List<MtutmaVO> selectMtutma(Map<String, Object> params);
	List<Map<String, Object>> selectMtutBox(Map<String, Object> params);	
	
	//mdu5
	List<MpakmaVO> selectMpakma(Map<String, Object> params);	
	int updateMpakma(HashMap<String, Object> map);
	int insertMpakma(HashMap<String, Object> map);
	
	//mdu5 : Pack master & Transfer unit type SelectBox 조회
	List<Map<String, Object>> selectMpakmaBox(Map<String, Object> params);
	List<Map<String, Object>> selectMpakmaPackBox(Map<String, Object> params);
	List<Map<String, Object>> selectSkwucBoxByWarekey(Map<String, Object> param);
	Map<String, Object> selectSkwucBoxtByWarekey(Map<String, Object> param);

		
	/**
	 * MDU6
	 **/
	List<MskuspVO> selectMdu6MskuspList(Map<String, Object> paramMap);
	Map<String, Object> callSpMdMakesku(Map<String, Object> param);
	
	//OMS2 위젯용 (관심상품 & 세일상품)
	List<MskuurVO> selectWzGrid01(Map<String, Object> paramMap);

}