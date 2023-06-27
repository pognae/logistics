package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class McommaVO {

	private String rowkey; // rowkey
	private String compkey; // VARCHAR(20)
	private String codelyn; // VARCHAR(20)
	private String conamlc; // VARCHAR(10)
	private String conamko; // VARCHAR(10)
	private String conamen; // VARCHAR(10)
	private String coaddr1; // VARCHAR(1)
	private String coaddr2; // VARCHAR(1)
	private String coaddr3; // VARCHAR(1)
	private Double complat; // VARCHAR(1)
	private Double complon; // VARCHAR(1)
	private String cociynm; // VARCHAR(1)
	private String coregnm; // VARCHAR(1)
	private String coteln1; // VARCHAR(1)
	private String coteln2; // VARCHAR(1)
	private String coteln3; // VARCHAR(1)
	private String cotaxcd; // VARCHAR(1)
	private String coposbx; // VARCHAR(1)
	private String coposcd; // VARCHAR(1)
	private String corepnm; // VARCHAR(1)
	private String coreptl; // VARCHAR(1)
	private String corepem; // VARCHAR(1)
	private String comannm; // VARCHAR(1)
	private String comantl; // VARCHAR(1)
	private String comanem; // VARCHAR(1)
	private int cotmzon; // VARCHAR(1)
	private String credate; // VARCHAR(1)
	private String cretime; // VARCHAR(1)
	private String creuser; // VARCHAR(1)
	private String lmodate; // VARCHAR(1)
	private String lmotime; // VARCHAR(1)
	private String lmouser; // VARCHAR(1)
	private String indibzl; // biz logic indicator
	private String indiarc; // Archive indicator
	private int updtchk; // Update check

	private String crename; // 생성자 이름
}
