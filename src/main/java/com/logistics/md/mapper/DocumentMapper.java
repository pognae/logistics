package com.logistics.md.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MrscmaDTO;

/* xml mapper :  mybatis header */
@Mapper
public interface DocumentMapper {

	//문서카테고리 
	List<MdocmaDTO> selectMdocmaDoccateGroupList(Map<String, Object> paramMap);
	
	//문서타입 
	List<MdocmaDTO> selectMdocmaDoctypeList(Map<String, Object> paramMap);

	List<MrscmaDTO> selectMrscmaSelectBox(Map<String, Object> param);
}