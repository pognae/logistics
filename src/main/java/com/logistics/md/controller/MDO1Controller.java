package com.logistics.md.controller;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.md.service.MDO1Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDO1Controller implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private final MDO1Service mdo1Service;
	
	/*
     * Company 리스트 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/CompanySearch.do")	
	public ModelAndView CompanySearch(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("commaList", mdo1Service.getMcommaListSelect(param, userInfo));
    	return mv;
    }
    
    /*
     * Company 리스트 저장
     * */
	@ResponseBody
	@PostMapping(value = "/md/mdo1Save.do")
    public ModelAndView mdo1Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mdo1Service.setMDO1Save(param, userInfo);
    	mv.addObject("commaList", mdo1Service.getMcommaListSelect(param, userInfo));
        return mv;
    } 
}
