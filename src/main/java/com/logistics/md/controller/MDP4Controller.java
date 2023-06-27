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
import com.logistics.md.service.MDP1Service;
import com.logistics.md.service.MDP4Service;
import com.logistics.sy.domain.UserVo;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MDP4Controller implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final MDP4Service mdp4Service;
	private final MDO2Service mdo2Service;
	private final MDP1Service mdp1Service;
	
	/*
     * 매장별 관리창고 Header 리스트 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/mdp4HeaderSelect.do")
	public ModelAndView getMdp4HeaderList(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", mdp4Service.getMdp4HeaderList(param, userInfo));
		return mv;
	}

	/*
     * 매장별 관리창고 Item 리스트 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/mdp4ItemSelect.do")
	public ModelAndView getMdp4ItemList(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", mdp4Service.getMdp4ItemList(param, userInfo));
		return mv;
	}
	
	/*
     * 매장별 관리창고 Item 리스트 저장
     * */
	@ResponseBody
	@PostMapping(value = "/md/mdp4Save.do")
	public ModelAndView getMdp4Save(@RequestBody Map<String , Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mdp4Service.setMcuswhSave(param, userInfo);
		mv.addObject("list", mdp4Service.getMdp4ItemList(param, userInfo));
		return mv;
	}
	
	@ResponseBody
	@GetMapping(value = "/md/mdp4Init.do")
	public ModelAndView mdp4Init(@RequestParam Map<String , Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String, Object> ownerParam = new HashMap<>();
		ownerParam.put("owdelyn", "N");
		mv.addObject("ownerky", mdp1Service.getOwnerSelectBox(ownerParam, userInfo));
		
		Map<String, Object> warehouseParam = new HashMap<>();
		warehouseParam.put("whdelyn", "N");
		mv.addObject("mngwhky", mdo2Service.getWarehouseUserSelectBox(warehouseParam, userInfo));
		return mv;
	}
}
