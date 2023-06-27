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

import com.logistics.md.service.MDC7Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor; 

@RestController
@RequiredArgsConstructor
public class MDC7Controller implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private final MDC7Service mdc7Service;
	
	/*
     * mdc7 공통코드 List 조회
     * */
    @ResponseBody
    @GetMapping(value = "/md/mdc7List.do")    
	public ModelAndView getCodeListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
        ModelAndView mv = new ModelAndView("jsonView");
        mv.addObject("list", mdc7Service.getMcodemListSelect(param, userInfo));
        return mv;
    }
	
	/* 
     *  mdc7 공통코드 List 저장
     * */
    @ResponseBody
    @PostMapping(value = "/md/mdc7Save.do")
    public ModelAndView mdc6Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mdc7Service.setMDC7Save(param, userInfo);
    	mv.addObject("list", mdc7Service.getMcodemListSelect(param, userInfo)); 
    	return mv;
    }
	
	/*
	 * 공통코 키에 해당하는 리스트만 리턴
	 * html 공통크드, selectbox ID 받아서 selectbox 코드 바인딩
	 * */
    @ResponseBody
    @GetMapping("/md/mdc7code2.do")
    public ModelAndView getCodeSelect2(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdc7Service.getMcodemSelect(param, userInfo));
		return mv;
    }  	
     
    /*
     * 
     * */
	@ResponseBody
	@GetMapping(value = "/md/mdc7code.do")    
    public ModelAndView getCodeSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("item", mdc7Service.getMcodemSelect(param, userInfo)); 
		return mv;
    }
    
}
