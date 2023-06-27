package com.logistics.md.controller;

import java.io.Serializable;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.md.service.MDO2Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDO2Controller implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final MDO2Service mdo2Service;
	
    /* 
     * wareHouse List 조회
     * */
	@GetMapping("/md/wareHouseSelect.do")
    public ModelAndView wareHouseSave(@RequestParam Map<String,Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", mdo2Service.getMwarmaListSelect(param, userInfo));
        return mv;
    }
	
	/*
     * wareHouse List 저장
     * */
	@PostMapping("/md/mdo2Save.do")	
	public ModelAndView mdo2Save(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
    	mdo2Service.saveMdo2(param, userInfo); 
		mv.addObject("list", mdo2Service.getMwarmaListSelect(param , userInfo));
        return mv;	
	}
	/*
     * 창고 SelectBox 조회
     * */
	@GetMapping("/md/warehouseSelectBox.do")
	public ModelAndView warehouseSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView"); 
		mv.addObject("item", mdo2Service.getWarehouseSelectBox(param, userInfo));
		return mv;
    }
	
    /*  
     * 사용자별 청고 SelectBox 조회
     * */ 
	@GetMapping("/md/warehouseUserSelectBox.do")
	public ModelAndView warehouseUserSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("item", mdo2Service.getWarehouseUserSelectBox(param, userInfo));
		return mv;
    }
	
}
