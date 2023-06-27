package com.logistics.tplt.mapper;

import com.logistics.tplt.domain.TpltVO;
import com.logistics.tplt.domain.TpsbVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TpltMapper {

	List<TpsbVO> selectTpltSelectBoxList(Map<String, Object> param);
	List<TpltVO> selectTplt1List(Map<String, Object> param);

	int insertTplt1Data(Map<String, Object> param);
	int updateTplt1Data(Map<String, Object> param);

	int deleteTplt1Data(Map<String, Object> param);
}
