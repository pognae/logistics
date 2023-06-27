package com.logistics.md.domain;

import lombok.Data;

@Data
public class MskusiVO {
	//MD SKU시방서-재단사이즈
	private String compkey;		//varchar(20)		Company Key	
	private String ownerky;     //varchar(20)       화주
	private String skuskey;     //varchar(50)       상품시방서 SKEY
	private String skutkey;     //varchar(50)       상품시방서 TKEY
	private String skuupdn;     //varchar(2)        상의UP하의DN
	private String sizecod;     //varchar(6)        사이즈
	private String upsizn01;    //varchar(60)       오비
	private String upsizn02;    //varchar(60)       앞밑위
	private String upsizn03;    //varchar(60)       뒷맡위
	private String upsizn04;    //varchar(60)       허벅지통
	private String upsizn05;    //varchar(60)       촌장
	private String upsizn06;    //varchar(60)       밑단부리
	private String upsizn07;    //varchar(60)       상의 임시
	private String upsizn08;    //varchar(60)       상의 임시
	private String upsizn09;    //varchar(60)       상의 임시
	private String upsizn10;    //varchar(60)       상의 임시
	private String upsizn11;    //varchar(60)       상의 임시
	private String upsizn12;    //varchar(60)       상의 임시
	private String upsizn13;    //varchar(60)       상의 임시
	private String upsizn14;    //varchar(60)       상의 임시
	private String upsizn15;    //varchar(60)       상의 임시
	private String upsizn16;    //varchar(60)       상의 임시
	private String upsizn17;    //varchar(60)       상의 임시
	private String upsizn18;    //varchar(60)       상의 임시
	private String upsizn19;    //varchar(60)       상의 임시
	private String upsizn20;    //varchar(60)       상의 임시
	private float upsize01;    //float(11)           오비
	private float upsize02;    //float(11)           앞밑위
	private float upsize03;    //float(11)           뒷맡위
	private float upsize04;    //float(11)           허벅지통
	private float upsize05;    //float(11)           촌장
	private float upsize06;    //float(11)			밑단부리
	private float upsize07;    //float(11)           상의 임시
	private float upsize08;    //float(11)           상의 임시
	private float upsize09;    //float(11)           상의 임시
	private float upsize10;    //float(11)           상의 임시
	private float upsize11;    //float(11)           상의 임시
	private float upsize12;    //float(11)           상의 임시
	private float upsize13;    //float(11)           상의 임시
	private float upsize14;    //float(11)           상의 임시
	private float upsize15;    //float(11)           상의 임시
	private float upsize16;    //float(11)           상의 임시
	private float upsize17;    //float(11)           상의 임시
	private float upsize18;    //float(11)           상의 임시
	private float upsize19;    //float(11)           상의 임시
	private float upsize20;    //float(11)           상의 임시
	private String dnsizn01;    //varchar(60)       기장
	private String dnsizn02;    //varchar(60)       어께넓이
	private String dnsizn03;    //varchar(60)       상동
	private String dnsizn04;    //varchar(60)       하동
	private String dnsizn05;    //varchar(60)       소매기장
	private String dnsizn06;    //varchar(60)       소매통
	private String dnsizn07;    //varchar(60)       소매부리
	private String dnsizn08;    //varchar(60)       암홀
	private String dnsizn09;    //varchar(60)       목넓이
	private String dnsizn10;    //varchar(60)       목깊이
	private String dnsizn11;    //varchar(60)       하의 임시
	private String dnsizn12;    //varchar(60)       하의 임시
	private String dnsizn13;    //varchar(60)       하의 임시
	private String dnsizn14;    //varchar(60)       하의 임시
	private String dnsizn15;    //varchar(60)       하의 임시
	private String dnsizn16;    //varchar(60)       하의 임시
	private String dnsizn17;    //varchar(60)       하의 임시
	private String dnsizn18;    //varchar(60)       하의 임시
	private String dnsizn19;    //varchar(60)       하의 임시
	private String dnsizn20;    //varchar(60)       하의 임시
	private float dnsize01;    //float(11)           기장
	private float dnsize02;    //float(11)           어께넓이
	private float dnsize03;    //float(11)           상동
	private float dnsize04;    //float(11)           하동
	private float dnsize05;    //float(11)           소매기장
	private float dnsize06;    //float(11)           소매통
	private float dnsize07;    //float(11)           소매부리
	private float dnsize08;    //float(11)           암홀
	private float dnsize09;    //float(11)           목넓이
	private float dnsize10;    //float(11)           목깊이
	private float dnsize11;    //float(11)           하의 임시
	private float dnsize12;    //float(11)           하의 임시
	private float dnsize13;    //float(11)           하의 임시
	private float dnsize14;    //float(11)           하의 임시
	private float dnsize15;    //float(11)           하의 임시
	private float dnsize16;    //float(11)           하의 임시
	private float dnsize17;    //float(11)           하의 임시
	private float dnsize18;    //float(11)           하의 임시
	private float dnsize19;    //float(11)           하의 임시
	private float dnsize20;    //float(11)           하의 임시
	private String credate;     //varchar(8)        생성일자
	private String cretime;     //varchar(6)        생성시간
	private String creuser;     //varchar(60)       생성사용자
	private String lmodate;     //varchar(8)        수정일자
	private String lmotime;     //varchar(6)        수정시간
	private String lmouser;     //varchar(60)       수정사용자

	private String indibzl;		//varchar(1)			Business logic indicator
	private String indiarc;		//varchar(1)			Archive indicator
	private int	   updtchk;		//float(11)				Update check
	
}
