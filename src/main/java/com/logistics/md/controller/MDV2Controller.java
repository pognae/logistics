package com.logistics.md.controller;
import java.io.Serializable;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.md.service.MDC7Service;
import com.logistics.md.service.MDP3Service;
import com.logistics.md.service.MDV2Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDV2Controller implements Serializable {

	private static final long serialVersionUID = 1L;

	private final MDV2Service mdv2Service;
	private final MDC7Service mdc7Service;
	private final MDP3Service mdp3Service;

    /* 
     * Shuttle Routing Header List 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/selectMshrhdList.do")
	public ModelAndView selectMshrhdList(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", mdv2Service.getMshrhdList(param, userInfo));
		return mv;
	}

    /* 
     * Shuttle Routing Item List 조회
     * */
    @ResponseBody
    @GetMapping(value = "/md/selectMshritList.do")
    public ModelAndView selectMshritList(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", mdv2Service.getMshritList(param, userInfo));
    	return mv;
    }
    
    /* 
     * Shuttle Routing Header&Item List 삭제
     * */
    @ResponseBody
    @RequestMapping(value = "/md/deleteHeadList.do", method = RequestMethod.DELETE)
    public ModelAndView deleteHeadList(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mdv2Service.setHeadDelete(param, userInfo);
    	mv.addObject("list", mdv2Service.getMshrhdList(param, userInfo));
    	return mv;
    }
    
    /* 
     * Shuttle Routing Item List 삭제
     * */
    @ResponseBody
    @RequestMapping(value = "/md/deleteItemList.do", method = RequestMethod.DELETE)
    public ModelAndView deleteItemList(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mdv2Service.setItemDelete(param, userInfo);
    	mv.addObject("list", mdv2Service.getMshrhdList(param, userInfo));
    	return mv;
    }
    
	/*
	 * Shuttle Routing Header&Item List 저장
	 */
	@ResponseBody
	@PostMapping(value = "/md/mdv2Save.do")
	public ModelAndView mdv2Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mdv2Service.setMDV2Save(param, userInfo);
		mv.addObject("list", mdv2Service.getMshrhdList(param, userInfo));
		return mv;
	}
	
	@GetMapping("/md/mdv2Init.do")
	public ModelAndView mdv2Init(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		param.put("comcdky", "SHTRPTY");
		mv.addObject("shtrpty", mdc7Service.getMcodemSelect(param, userInfo));
		
		mv.addObject("custkey",  mdp3Service.getRouteSelectBox(param, userInfo));
		return mv;
    }
}