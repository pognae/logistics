package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MlocmaVO {
	
	private String rowkey;	// rowkey
	private String compkey; // Company Key
	private String warekey; // Warehouse Key
	private String whnamlc; // Warehouse Name<커스텀>
	private String areakey; // Area key
	private String areanam; // Area Name<커스텀>
	private String zonekey; // Zone Key
	private String zonenam; // Zone Name<커스텀>
	private String locakey; // Location Key
	private String lodelyn; // Location Deletion YN
	private String locanam; // Location Name
	private String loctype; // Location type
	private String capachk; // Capacity check
	private String locstat; // Location Status
	private int locleng;    // Length
	private int locwidh;    // Width
	private int locheig;    // Height
	private int loccubi;    // Cubic meter
	private int inbrtno;    // Route for inbound
	private String mixdsku; // Mixed stock by SKU
	private int otbrtno;    // Route for outbound
	private String mixdlot; // Mixed stock by Lot
	private String remtloc; // Replenishment Loc
	private String usptloc; // Use for putaway
	private String uspkloc; // VUse for picking
	private String blockid; // Block
	private String physrow; // Row X열
	private String physsec; // Section Y행
	private String physflo; // floor Z단
	private String equstat;	// Bin 상태 (보관,이동,PORT,제거)
	private String credate; // 생성일자
	private String cretime; // 생성시간
	private String creuser; // 생성사용자
	private String lmodate; // 수정일자
	private String lmotime; // 수정시간
	private String lmouser; // 수정사용자
	private String indibzl;	// biz logic indicator
	private String indiarc;	// Archive indicator
	private int updtchk;	// Update check
	
	private String oldwarekey;	
	private String oldareakey;	
	private String oldzonekey;	
	private String oldlocakey;
	
	private String stockky;      // 재고키 
	private String truntyp;      // transfer unit type
	private String trunpid;      // transfer unit id
	private String lotnmky;      // lot number
	private String ownerky;      
	private String skumkey;      // 상품
	private String skudesc;      // 상품명
	private String vendkey;
	private int stotqty;      	 // 총재고 수량
	private Double gpakcnt;		 //절
	private int sallqty;         // 할당 수량
	private int sinbqty;         // 입고중 수량
	private int soubqty;         // 출고중 수량
	private int sbloqty;         // 블락 수량
	private String suomkey;      // Stock UOM
	private String skuwabc;		 //WH SKU ABC analyzed value
	private String brandcd;		 //브랜드
	private String proddat;      // 생산일자
	private int shelife;      	 // * 유통기한
	private String expidat;      // * 유통기간
	private String rcvdcdt;      // 실물입고 일자
	private String rcvfrdt;      // 최초 RECEVING DATE
	private String lotfrky;      // 최초 LOT NUMBER
	private String stkstat;      // 재고상태
	
		
}
