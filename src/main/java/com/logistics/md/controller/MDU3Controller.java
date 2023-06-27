package com.logistics.md.controller;

import java.io.Serializable;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.md.service.MDU3Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDU3Controller implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final MDU3Service mdu3Service;
		
    /* 
     * Unit of measure List 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/selectMuommaList.do")
	public ModelAndView selectMuommaList(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", mdu3Service.getUnitList(param, userInfo));
    	return mv;
    }
	
    /*
     * Unit of measure List 저장
     * */
    @ResponseBody
    @PostMapping(value = "/md/mdu3Save.do")
    public ModelAndView mdu3Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mdu3Service.setMDU3Save(param, userInfo);
    	mv.addObject("list", mdu3Service.getUnitList(param, userInfo));
        return mv;
    }	
	
    /*
     * Unit of measure SelectBox 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/unitSelectBox.do")
	public ModelAndView unitSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdu3Service.getUnitSelectBox(param, userInfo));
    	return mv;
    }	


	
}
