package com.logistics.md.domain;

import lombok.Data;

@Data
public class MskumaDTO {

	/**
	 * TABLE : MSKUMA
	 */
	
	private String rowkey;			//					Grid RowKey
	private String compkey; 		// VARCHAR(20) 		Company Key
	private String skumkey; 		// VARCHAR(50) 		상품코드
	private String ownerky; 		// VARCHAR(20) 		화주
	private String delmark; 		// VARCHAR(10) 		Deletion mark
	private String skudesc; 		// VARCHAR(100)		상품명
	private String skutype; 		// VARCHAR(10) 		상품유형
	private String vendkey; 		// VARCHAR(20) 		공장
	private String packkey; 		// VARCHAR(10) 		포장 키
	private String eancode; 		// VARCHAR(20) 		EAN 코드
	private String upccode; 		// VARCHAR(20) 		UPC 코드
	private String brandcd; 		// VARCHAR(20) 		브랜드 
	private String skugr01; 		// VARCHAR(20) 		SKU Group 1
	private String skugr02; 		// VARCHAR(20) 		SKU Group 2 Season 
	private String skugr03; 		// VARCHAR(20) 		SKU Group 3 Size 
	private String skugr04; 		// VARCHAR(20) 		SKU Group 4 Color 
	private String skugr05; 		// VARCHAR(20) 		SKU Group 5
	private String skualt1; 		// VARCHAR(50) 		SKU Alternative1
	private String skualt2; 		// VARCHAR(50) 		SKU Alternative2
	private String skualt3; 		// VARCHAR(50) 		SKU Alternative3
	private String skuat01; 		// VARCHAR(10) 		SKU Attribute1 디자이너
	private String skuat02; 		// VARCHAR(10) 		SKU Attribute2
	private String skuat03; 		// VARCHAR(10) 		SKU Attribute3
	private String skuat04; 		// VARCHAR(10) 		SKU Attribute4
	private String skuat05; 		// VARCHAR(10) 		SKU Attribute5
	private Double sgrweig; 		// DECIMAL(10,3) 	Gross weight
	private Double sneweig; 		// DECIMAL(10,3) 	Net weight
	private Double skuleng; 		// DECIMAL(10,3) 	Length
	private Double skuwidh; 		// DECIMAL(10,3) 	Width
	private Double skuheig; 		// DECIMAL(10,3) 	Height
	private Double skucubi; 		// DECIMAL(10,3) 	Cubic meter
	private String suomkey; 		// VARCHAR(10) 		Default unit of measure
	private String skumabc; 		// VARCHAR(10) 		SKU ABC analyzed value 
}