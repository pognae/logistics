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
import com.logistics.md.service.MDP1Service;
import com.logistics.md.service.MDP2Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDP2Controller implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final MDP2Service mdp2Service;
	private final MDP1Service mdp1Service;
	private final MDC7Service mdc7Service;

    /* 
     * Logist Partner List 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/selectMptnmaList.do")
	public ModelAndView selectMptnmaList(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", mdp2Service.getMptnmaList(param, userInfo));
    	return mv;
    }

    /* 
     * Logist Partner List 저장
     * */
	@ResponseBody
	@PostMapping(value = "/md/mdp2Save.do")
    public ModelAndView mdp2Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mdp2Service.setMdp2Save(param, userInfo);
    	mv.addObject("list", mdp2Service.getMptnmaList(param, userInfo));
        return mv;
    }
	
    /*
     * Logist Partner SelectBox 조회 ( 파트너 타입 vendor )
     * */
	@ResponseBody
	@GetMapping(value = "/md/mdp2SelectBox.do")
    public ModelAndView mp2SelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdp2Service.getMp2SelectBox(param, userInfo));
        return mv;
    }
	
	@ResponseBody
	@GetMapping(value = "/md/mdp2SelectBoxByAll.do")
    public ModelAndView mp2SelectBoxByAll(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdp2Service.getMp2SelectBoxByAll(param, userInfo));
        return mv;
    }
	
	
	@GetMapping("/md/mdp2Ptnrkey.do")
	public ModelAndView mdp2Ptnrkey(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("ptnrkey", mdp2Service.mdp2Ptnrkey(param));
        return mv;
    } 
	
	@GetMapping("/md/mdp2Init.do")
	public ModelAndView mdp2Init(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	Map<String, Object> ownerParam = new HashMap<>();
    	ownerParam.put("owdelyn" , "N");
    	ownerParam.put("ownerky" , userInfo.getOwnerky());
    	mv.addObject("ownerky", mdp1Service.getOwnerSelectBox(ownerParam, userInfo));
    	
    	Map<String, Object> codeParam = new HashMap<>();
    	codeParam.put("comcdky" , "PTNRTYP");
    	codeParam.put("comcdvl" , "CARRIER,VENDER");
    	mv.addObject("ptnrtyp", mdc7Service.getMcodemSelect(codeParam, userInfo));
    	
    	Map<String, Object> partnerParam = new HashMap<>();
    	partnerParam.put("comcdky" , "PTNGRO1");
    	mv.addObject("ptngro1", mdc7Service.getMcodemSelect(partnerParam, userInfo));
    	return mv;
    } 
}
