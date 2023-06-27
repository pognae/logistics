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

import com.logistics.md.service.MDC7Service;
import com.logistics.md.service.MDV1Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDV1Controller implements Serializable {

	private static final long serialVersionUID = 1L;

	private final MDV1Service mdv1Service;
	private final MDC7Service mdc7Service;

    /* 
     * 차량마스터 List 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/selectMvhcmaList.do")
	public ModelAndView selectMvhcmaList(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", mdv1Service.getMvhalist(param, userInfo));
		return mv;
	}

    /* 
     * 차량마스터 List 조회
     * */
	@ResponseBody
	@PostMapping(value = "/md/mdv1Save.do")
	public ModelAndView mdv1Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mdv1Service.setMDV1Save(param, userInfo);
		mv.addObject("list", mdv1Service.getMvhalist(param, userInfo));
		return mv;
	}
	
	@GetMapping("/md/mdv1Init.do")
	public ModelAndView mdv1Init(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		param.put("comcdky", "DLVTYPE");
		mv.addObject("dlvtype", mdc7Service.getMcodemSelect(param, userInfo));
		
		param.put("comcdky", "VHCOPTY");
		mv.addObject("vhcopty",  mdc7Service.getMcodemSelect(param, userInfo));
		
		param.put("comcdky", "VHCTYPE");
		mv.addObject("vhctype",  mdc7Service.getMcodemSelect(param, userInfo));
		
		param.put("comcdky", "VHCTNCD");
		mv.addObject("vhctncd",  mdc7Service.getMcodemSelect(param, userInfo));
		return mv;
    }
}