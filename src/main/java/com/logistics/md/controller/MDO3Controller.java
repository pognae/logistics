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
import com.logistics.md.service.MDO3Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDO3Controller implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final MDO3Service mdo3Service;
	private final MDO2Service mdo2Service;
	private final MDC7Service mdc7Service;
	/*
     * 영역 리스트 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/areaSelect.do")
	public ModelAndView areaSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("areaList", mdo3Service.getAreaList(param, userInfo));
    	return mv;
    }
    
    /*
     * 영역 리스트 저장
     * */
    @ResponseBody
    @PostMapping(value = "/md/areaSave.do")
    public ModelAndView areaSave(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mdo3Service.setMDO3Save(param, userInfo);
    	mv.addObject("areaList", mdo3Service.getAreaList(param, userInfo));
        return mv;
    }
    
    /*
     * 영역 SelectBox 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/areaSelectBox.do")
	public ModelAndView areaSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdo3Service.getAreaSelectBox(param, userInfo));
    	return mv;
    }
	
	@ResponseBody
	@GetMapping(value = "/md/mdo3Init.do")
	public ModelAndView mdo3Init(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String , Object> wareHouseParam = new HashMap<>();
		wareHouseParam.put("whdelyn", "N");
		mv.addObject("warekey", mdo2Service.getWarehouseUserSelectBox(wareHouseParam, userInfo));

		Map<String , Object> areaParam = new HashMap<>();
		areaParam.put("comcdky", "AREATYP");
		mv.addObject("areatyp", mdc7Service.getMcodemSelect(areaParam, userInfo));
    	return mv;
    }
}
