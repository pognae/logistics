package com.logistics.md.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.md.service.MDC7Service;
import com.logistics.md.service.MDO2Service;
import com.logistics.md.service.MDO5Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDO5Controller implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final MDO5Service mdo5Service;
	private final MDO2Service mdo2Service;
	private final MDC7Service mdc7Service;

    /* 
     * location List 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/selectMlocmaList.do")
	public ModelAndView selectMlocmaList(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) { 
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("locationList", mdo5Service.selectMlocmaList(param, userInfo));
		return mv;
	}
	
	/*
     * location List 저장
     * */
    @ResponseBody
    @PostMapping(value = "/md/mdo5Save.do")
	public ModelAndView mdo5Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mdo5Service.setMDO5Save(param, userInfo);
    	mv.addObject("locationList", mdo5Service.selectMlocmaList(param, userInfo));
    	return mv;
    }
	
    /*
     * location SelectBox 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/locationSelectBox.do")
	public ModelAndView locationSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdo5Service.getLocationSelectBox(param, userInfo));
    	return mv;
    }
	
	@ResponseBody
	@GetMapping(value = "/md/mdo5Init.do")
	public ModelAndView mdo5Init(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String, Object> warekeyParam = new HashMap<>();	
		warekeyParam.put("whdelyn", "N");
		mv.addObject("warekey", mdo2Service.getWarehouseUserSelectBox(param, userInfo));
    	
		param.put("comcdky", "LOCTYPE");
		mv.addObject("loctype", mdc7Service.getMcodemSelect(param, userInfo));
		param.put("comcdky", "LOCSTAT");
		mv.addObject("locstat", mdc7Service.getMcodemSelect(param, userInfo));
		param.put("comcdky", "EQUSTAT");
    	mv.addObject("equstat", mdc7Service.getMcodemSelect(param, userInfo));
    	return mv;
    }
	
	/* 
     * location 단건 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/selectMlocma.do")
	public ModelAndView selectMlocma(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) { 
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", mdo5Service.selectMlocma(param, userInfo));
		return mv;
	}

}
