package com.logistics.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.logistics.configuration.error.procedureCheckedException;
import com.logistics.main.mapper.DashBoardMapper;
import com.logistics.sy.domain.UserVo;


@Service
public class DashBoardService{
	
	@Autowired
	private DashBoardMapper dashBoardMapper;
	
	/*************************************
	 * 빈블러 대시보드
	 *************************************/
	
	public Map<String, Object> getDashBoardDataSelect(Map<String, Object> param, UserVo userInfo) throws JsonMappingException, JsonProcessingException {
		
		Map<String, Object> rData = new HashMap<>();
		param.put("userData", userInfo);
		param.put("compkey", userInfo.getCompkey());
		rData.put("BINBLUR_POSDATE", dashBoardMapper.selectWmPosdate(param).get("POSDATE"));
		
		String datatype = (String) param.get("datatype");
		
		switch(datatype) {
			case "ALL":
				//카드형 데이터1 - 오더 ~ SKU
				param.put("chatype", "BINBLUR_CARD_1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_CARD_1_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//카드형 데이터2 - 미송, 반품
				param.put("chatype", "BINBLUR_CARD_2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_CARD_2_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//입고현황 원형그래프
				param.put("chatype", "BINBLUR_INBOUND_CHART_CIRCLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_INBOUND_CHART_CIRCLE_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//입고현황 가로막대그래프
				param.put("chatype", "BINBLUR_INBOUND_CHART_BAR_HORI");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_INBOUND_CHART_BAR_HORI_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				
				//출고현황 원형그래프
				param.put("chatype", "BINBLUR_OUTBOUND_CHART_CIRCLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_OUTBOUND_CHART_CIRCLE_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//출고현황 가로막대그래프
				param.put("chatype", "BINBLUR_OUTBOUND_CHART_BAR_HORI");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_OUTBOUND_CHART_BAR_HORI_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				
				//*********************************
				// 하단 그래프4개
				//*********************************
				
				//입고상품 그래프
				param.put("chatype", "BINBLUR_CHART_THB1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_CHART_THB1_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    		rData.put("BINBLUR_CHART_THB1_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    	}
				
				//재고 그래프
				param.put("chatype", "BINBLUR_CHART_THB2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_CHART_THB2_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//출고 SKU 그래프
				param.put("chatype", "BINBLUR_CHART_THB3");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_CHART_THB3_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    		rData.put("BINBLUR_CHART_THB3_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    	}
				
				//출고처 그래프 (미정)
				param.put("chatype", "BINBLUR_CHART_THB4");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_CHART_THB4_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    		rData.put("BINBLUR_CHART_THB4_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    	}
			break;
			
			case "ORDER":
			case "LINE":
			case "SKUQTY":
			case "SKU":
				//카드형 데이터1 - 오더 ~ SKU
				param.put("chatype", "BINBLUR_CARD_1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_CARD_1_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "UNFULFILLED":
			case "RETURNORDER":
				//카드형 데이터2 - 미송, 반품
				param.put("chatype", "BINBLUR_CARD_2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_CARD_2_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "INBOUNDCHART":
				//입고현황 원형그래프
				param.put("chatype", "BINBLUR_INBOUND_CHART_CIRCLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_INBOUND_CHART_CIRCLE_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//입고현황 가로막대그래프
				param.put("chatype", "BINBLUR_INBOUND_CHART_BAR_HORI");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_INBOUND_CHART_BAR_HORI_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "OUTBOUNDCHART":
				//출고현황 원형그래프
				param.put("chatype", "BINBLUR_OUTBOUND_CHART_CIRCLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_OUTBOUND_CHART_CIRCLE_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//출고현황 가로막대그래프
				param.put("chatype", "BINBLUR_OUTBOUND_CHART_BAR_HORI");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("BINBLUR_OUTBOUND_CHART_BAR_HORI_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
			break;
		}
		
		return rData;
	}
	
	/*************************************
	 * 화주 대시보드
	 *************************************/
	
	public Map<String, Object> getDashBoardOwnerDataSelect(Map<String, Object> param, UserVo userInfo) throws JsonMappingException, JsonProcessingException {
		
		Map<String, Object> rData = new HashMap<>();
		param.put("userData", userInfo);
		param.put("compkey", userInfo.getCompkey());
		rData.put("OWNER_POSDATE", dashBoardMapper.selectWmPosdate(param).get("POSDATE"));
		
		String datatype = (String) param.get("datatype");
		
		switch(datatype) {
			case "ALL":
				//카드형 데이터 - 입고현황 ~ 미수현황
				param.put("chatype", "OWNER_CARD_1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CARD_1_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//카드형 데이터 - 매출현황
				param.put("chatype", "OWNER_CARD_2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CARD_2_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 매출정보 (일)
				param.put("chatype", "OWNER_CHART1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART1_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART1_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 매출정보 (월)
				param.put("chatype", "OWNER_CHART2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART2_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART2_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 주간상품정보 (BEST)
				param.put("chatype", "OWNER_CHART3");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART3_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART3_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 주간상품정보 (WORST)
				param.put("chatype", "OWNER_CHART4");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART4_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART4_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}

				
				//샘플 현황 - 일별
				param.put("chatype", "OWNER_CHART5");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART5_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART5_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				param.put("chatype", "OWNER_CHART5_TABLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART5_TABLE_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//샘플 현황 - 월별
				param.put("chatype", "OWNER_CHART6");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART6_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART6_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//미송미출 현황 - 일별
				param.put("chatype", "OWNER_CHART7");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART7_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART7_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				param.put("chatype", "OWNER_CHART7_TABLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART7_TABLE_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//미송미출 현황 - 월별
				param.put("chatype", "OWNER_CHART8");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART8_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART8_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "INBOUND":
			case "PACK":
			case "SALES":
			case "ACCREABLE":
				//카드형 데이터 - 입고현황 ~ 미수현황
				param.put("chatype", "OWNER_CARD_1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CARD_1_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "STORE":
				//카드형 데이터 - 매출현황
				param.put("chatype", "OWNER_CARD_2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CARD_2_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "SALESINFO":
				//그래프 데이터 - 매출정보 (일)
				param.put("chatype", "OWNER_CHART1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART1_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART1_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 매출정보 (월)
				param.put("chatype", "OWNER_CHART2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART2_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART2_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "WEEKLYSKU":
				//그래프 데이터 - 주간상품정보 (BEST)
				param.put("chatype", "OWNER_CHART3");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART3_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART3_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 주간상품정보 (WORST)
				param.put("chatype", "OWNER_CHART4");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART4_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART4_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "SAMPLE":
				//샘플 현황 - 일별
				param.put("chatype", "OWNER_CHART5");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART5_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART5_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				param.put("chatype", "OWNER_CHART5_TABLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART5_TABLE_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//샘플 현황 - 월별
				param.put("chatype", "OWNER_CHART6");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART6_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART6_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "NSHIPPING":
				param.put("chatype", "OWNER_CHART7");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART7_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART7_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				param.put("chatype", "OWNER_CHART7_TABLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART7_TABLE_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//미송미출 현황 - 월별
				param.put("chatype", "OWNER_CHART8");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("OWNER_CHART8_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("OWNER_CHART8_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
			break;
		}
		
		return rData;
	}
	
	/*************************************
	 * 공장 대시보드
	 *************************************/
	
	public Map<String, Object> getDashBoardVenderDataSelect(Map<String, Object> param, UserVo userInfo) throws JsonMappingException, JsonProcessingException {
		Map<String, Object> rData = new HashMap<>();
		param.put("userData", userInfo);
		param.put("compkey", userInfo.getCompkey());
		rData.put("VENDER_POSDATE", dashBoardMapper.selectWmPosdate(param).get("POSDATE"));
		
		String datatype = (String) param.get("datatype");
		
		switch(datatype) {
			case "ALL":
				//그래프 데이터1 - 출고현황 (일)
				param.put("chatype", "VENDER_CHART_1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("VENDER_CHART1_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("VENDER_CHART1_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				param.put("chatype", "VENDER_CHART_1_TABLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("VENDER_CHART_1_TABLE_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터2 - 수주현황 (일)
				param.put("chatype", "VENDER_CHART_2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("VENDER_CHART2_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("VENDER_CHART2_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				param.put("chatype", "VENDER_CHART_2_TABLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("VENDER_CHART_2_TABLE_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터3 - 결제현황 (일)
				param.put("chatype", "VENDER_CHART_3");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("VENDER_CHART3_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("VENDER_CHART3_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				param.put("chatype", "VENDER_CHART_3_TABLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("VENDER_CHART_3_TABLE_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
			break;
		}
		return rData;
	}
	
	
	/*************************************
	 * 매장 대시보드
	 *************************************/
	
	public Map<String, Object> getDashBoardWholesaleDataSelect(Map<String, Object> param, UserVo userInfo) throws JsonMappingException, JsonProcessingException {
		
		Map<String, Object> rData = new HashMap<>();
		param.put("userData", userInfo);
		param.put("compkey", userInfo.getCompkey());
		rData.put("WHOLESALE_POSDATE", dashBoardMapper.selectWmPosdate(param).get("POSDATE"));
		
		String datatype = (String) param.get("datatype");
		
		switch(datatype) {
			case "ALL":
				//카드형 데이터 - 입고현황 ~ 미수현황
				param.put("chatype", "WHOLESALE_CARD_1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CARD_1_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//카드형 데이터 - 매출현황
				param.put("chatype", "WHOLESALE_CARD_2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CARD_2_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 매출정보 (일)
				param.put("chatype", "WHOLESALE_CHART1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART1_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART1_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 매출정보 (월)
				param.put("chatype", "WHOLESALE_CHART2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART2_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART2_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 주간상품정보 (BEST)
				param.put("chatype", "WHOLESALE_CHART3");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART3_XLData", parsingStringToListMap((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART3_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 주간상품정보 (WORST)
				param.put("chatype", "WHOLESALE_CHART4");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART4_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART4_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}

				
				//샘플 현황 - 일별
				param.put("chatype", "WHOLESALE_CHART5");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART5_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART5_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				param.put("chatype", "WHOLESALE_CHART5_TABLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART5_TABLE_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//샘플 현황 - 월별
				param.put("chatype", "WHOLESALE_CHART6");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART6_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART6_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//미송미출 현황 - 일별
				param.put("chatype", "WHOLESALE_CHART7");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART7_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART7_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				param.put("chatype", "WHOLESALE_CHART7_TABLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART7_TABLE_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//미송미출 현황 - 월별
				param.put("chatype", "WHOLESALE_CHART8");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART8_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART8_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "INBOUND":
			case "PACK":
			case "SALES":
			case "ACCREABLE":
				//카드형 데이터 - 입고현황 ~ 미수현황
				param.put("chatype", "WHOLESALE_CARD_1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CARD_1_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "STORE":
				//카드형 데이터 - 매출현황
				param.put("chatype", "WHOLESALE_CARD_2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CARD_2_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "SALESINFO":
				//그래프 데이터 - 매출정보 (일)
				param.put("chatype", "WHOLESALE_CHART1");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART1_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART1_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 매출정보 (월)
				param.put("chatype", "WHOLESALE_CHART2");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART2_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART2_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "WEEKLYSKU":
				//그래프 데이터 - 주간상품정보 (BEST)
				param.put("chatype", "WHOLESALE_CHART3");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART3_XLData", parsingStringToListMap((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART3_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
				
				//그래프 데이터 - 주간상품정보 (WORST)
				param.put("chatype", "WHOLESALE_CHART4");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART4_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART4_RSData", parsingStringToListMap((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "SAMPLE":
				//샘플 현황 - 일별
				param.put("chatype", "WHOLESALE_CHART5");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART5_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART5_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				param.put("chatype", "WHOLESALE_CHART5_TABLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART5_TABLE_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//샘플 현황 - 월별
				param.put("chatype", "WHOLESALE_CHART6");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART6_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART6_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
			break;
			
			case "NSHIPPING":
				param.put("chatype", "WHOLESALE_CHART7");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART7_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART7_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				param.put("chatype", "WHOLESALE_CHART7_TABLE");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART7_TABLE_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
				
				//미송미출 현황 - 월별
				param.put("chatype", "WHOLESALE_CHART8");
				dashBoardMapper.callSpDsChart(param);
				if(!param.get("O_ORESULT").equals(0)) {
		    		throw new procedureCheckedException((String) param.get("O_OMSGKEY"));
		    	}else {
		    		rData.put("WHOLESALE_CHART8_XLData", parsingStringToListArray((String)param.get("O_OXLDATA")));
		    		rData.put("WHOLESALE_CHART8_RSData", parsingStringToListArray((String)param.get("O_ORSDATA")));
		    	}
			break;
		}
		
		return rData;
	}
	
	//Procedure => JSON Parsing Method
	
	public List<Map<String, Object>> parsingStringToListMap(String data) throws JsonMappingException, JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		List<Map<String, Object>> parsingData = new ArrayList<Map<String, Object>>();
		
		try {
			parsingData = mapper.readValue(data, TypeFactory.defaultInstance().constructCollectionType(List.class, Map.class));
		}catch(Exception e) {
			parsingData = null;
		}
		
		return parsingData;
	}
	
	//Procedure => JSON Parsing Method
	
	public List<Map<String, Object>> parsingStringToListArray(String data) throws JsonMappingException, JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		List<Map<String, Object>> parsingData = new ArrayList<Map<String, Object>>();
		
		try {
			parsingData = mapper.readValue(data, TypeFactory.defaultInstance().constructCollectionType(List.class, Object.class));
		}catch(Exception e) {
			parsingData = null;
		}
		
		return parsingData;
	}
}
