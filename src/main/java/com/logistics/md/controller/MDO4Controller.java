package com.logistics.md.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.md.service.MDC7Service;
import com.logistics.md.service.MDO2Service;
import com.logistics.md.service.MDO4Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor
public class MDO4Controller {
	
	private final MDO4Service  mdo4Service;
	private final MDO2Service  mdo2Service;
	private final MDC7Service  mdc7Service;
	
    @GetMapping("/md/mdo4Select.do") 
    public ModelAndView mdo4Select(@RequestParam Map<String,Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("mzonmaList", mdo4Service.getMzonmaSelect(param , userInfo));
        return  mv;
    }
    
    
    @PostMapping("/md/mdo4Save.do")
	public ModelAndView mdo4Save(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mdo4Service.saveMdo4(param , userInfo);
		mv.addObject("mzonmaList", mdo4Service.getMzonmaSelect(param, userInfo));	
		return mv;
	}
	
	/*
     * Zone SelectBox 조회
     * */
	@GetMapping("/md/zoneSelectBox.do")
	public ModelAndView zoneSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("item", mdo4Service.getZoneSelectBox(param, userInfo));
		return mv;
    }
	
	@GetMapping("/md/mdo4Init.do")
	public ModelAndView mdo4Init(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String , Object> wareHouseParam = new HashMap<>();
		wareHouseParam.put("whdelyn", "N");
		mv.addObject("warekey", mdo2Service.getWarehouseUserSelectBox(wareHouseParam, userInfo));
		
		param.put("comcdky", "ZONETYP");
		mv.addObject("zonetyp", mdc7Service.getMcodemSelect(param, userInfo));
		return mv;
    }
	
}
