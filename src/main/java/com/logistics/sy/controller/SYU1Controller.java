package com.logistics.sy.controller;

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

import com.logistics.sy.domain.UserVo;
import com.logistics.sy.service.SYU1Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SYU1Controller implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private final SYU1Service syu1Service;
    
    /* 
     * 사용자 리스트 조회
     * */
    @ResponseBody
    @GetMapping(value = "/sy/userSelect.do")
    public ModelAndView userSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", syu1Service.getUserSelect(param, userInfo));
    	return mv;
    }  
    
    /* 
     * 사용자 리스트 저장
     * */
    @ResponseBody
    @PostMapping(value = "/sy/userSave.do")
    public ModelAndView userSave(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	syu1Service.setSYU1Save(param, userInfo);
    	mv.addObject("list", syu1Service.getUserSelect(param, userInfo));
        return mv;	
    }  

    
}
