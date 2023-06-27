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

import com.logistics.md.service.MDO2Service;
import com.logistics.md.service.MDU3Service;
import com.logistics.md.service.MDU4Service;
import com.logistics.md.service.MDU5Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDU5Controller implements Serializable {

	private static final long serialVersionUID = 1L;

	private final MDU5Service mdu5Service;
	private final MDO2Service mdo2Service;
	private final MDU4Service mdu4Service;
	private final MDU3Service mdu3Service;
	
    /* 
     * Pack master List 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/selectMpakmaList.do")
	public ModelAndView selectMpakmaList(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", mdu5Service.getMpaklist(param, userInfo));
		return mv;
	}

	/*
	 * Pack master List 저장
	 */
	@ResponseBody
	@PostMapping(value = "/md/mdu5Save.do")
	public ModelAndView mdu5Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mdu5Service.setMDU5Save(param, userInfo);
		mv.addObject("list", mdu5Service.getMpaklist(param, userInfo));
		return mv;
	}
	
	/*
     * Pack master & Transfer unit type SelectBox 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/mpakmaSelectBox.do")
	public ModelAndView mtytSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdu5Service.getMpakmaSelectBox(param, userInfo));
    	return mv;
    }
	
	/*
     * Pack master & Transfer unit type SelectPackBox 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/mpakmaSelectPackBox.do")
	public ModelAndView mtytSelectPackBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdu5Service.getMpakmaSelectPackBox(param, userInfo));
    	return mv;
    }
	
	@GetMapping("/md/mdu5Init.do")
	public ModelAndView mdu5Init(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String, Object> warehouseParam = new HashMap<>();
		warehouseParam.put("whdelyn", "N");
		mv.addObject("warekey", mdo2Service.getWarehouseUserSelectBox(warehouseParam, userInfo));
		
		param.put("trdelyn", "N");
		mv.addObject("truntyp",  mdu4Service.getMtutSelectBox(param, userInfo));
		
		Map<String, Object> unitParam = new HashMap<>();
		unitParam.put("uodelyn", "N");
		mv.addObject("puomkey",  mdu3Service.getUnitSelectBox(unitParam, userInfo));
		return mv;
    }
}
