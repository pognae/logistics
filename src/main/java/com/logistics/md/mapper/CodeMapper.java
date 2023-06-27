package com.logistics.md.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.McountVO;

/* xml mapper :  mybatis header */
@Mapper
public interface CodeMapper {

	
	//국가코드 SELECT
	List<McountVO> selectMcountList(Map<String, Object> paramMap);
		
	/**
	 * MDC6
	 **/
	//MDC6 : 사유코드 SELECT
	List<MrscmaDTO> selectMrscmaList(Map<String, Object> paramMap);
	
	//MDC6 : 사유코드 INSERT
	int insertMrscma(Map<String, Object> paramMap);
		
	//MDC6 : 사유코드 UPDATE
	int updateMrscma(Map<String, Object> paramMap);
	
	//MDC6 : 사유코드 DELETE
	int deleteMrscma(Map<String, Object> paramMap);


	/**
	 * MDC7 Mapper
	 **/
	//MDC7 : 공통코드 
	List<Map<String, Object>> selectMcodem(Map<String, Object> paramMap);
	List<McodemDTO> selectMcodemList(Map<String, Object> paramMap);
	
	//MDC6 : 공통코드 INSERT
	int insertMcodem(Map<String, Object> paramMap);
	
	//MDC7 : 공통코드 UPDATE
	int updateMcodem(Map<String, Object> paramMap);
	
}