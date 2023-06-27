package com.logistics.md.controller;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.md.service.MDP1Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDP1Controller implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private final MDP1Service mdp1Service;
	
    /* 
     * Owner List 조회
     * */
	@GetMapping("/md/mdp1Select.do")
	public ModelAndView mdp1Select(@RequestParam Map<String,Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list",mdp1Service.getMowrmaSelect(param , userInfo));	
		return mv;
	}
	
    /* 
     * Owner List 저장
     * */
	@PostMapping("/md/mdp1Save.do")
	public ModelAndView mdp1Save(@RequestBody Map<String,Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mdp1Service.saveMpd1(param , userInfo);
		mv.addObject("list",mdp1Service.getMowrmaSelect(param , userInfo));	
		return mv;
	}
	
	/*
     * Owner SelectBox 조회
     * */
	@GetMapping(value = "/md/ownerSelectBox.do")
	public ModelAndView areaSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("item", mdp1Service.getOwnerSelectBox(param, userInfo));
		return mv;
    }
}
