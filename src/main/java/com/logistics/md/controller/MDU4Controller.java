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
import com.logistics.md.service.MDU4Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDU4Controller implements Serializable {

	private static final long serialVersionUID = 1L;

	private final MDU4Service mdu4Service;
	private final MDU3Service mdu3Service;
	
    /* 
     * Transfer unit type List 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/selectMtutmaList.do")
	public ModelAndView selectMtutmaList(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", mdu4Service.getMtutlist(param, userInfo));
		return mv;
	}
	
	/*
	 * Transfer unit type List 저장
	 */
	@ResponseBody
	@PostMapping(value = "/md/mdu4Save.do")
	public ModelAndView mdu4Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mdu4Service.setMDU4Save(param, userInfo);
		mv.addObject("list", mdu4Service.getMtutlist(param, userInfo));
		return mv;
	}

    /*
     * Transfer unit type SelectBox 조회
     * */
	@ResponseBody
	@GetMapping(value = "/md/mtutSelectBox.do")
	public ModelAndView mtytSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdu4Service.getMtutSelectBox(param, userInfo));
    	return mv;
    }

	@GetMapping("/md/mdu4Init.do")
	public ModelAndView mdp4Init(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		param.put("uodelyn", "N");
		mv.addObject("uomekey", mdu3Service.getUnitSelectBox(param, userInfo));
		return mv;
    }
}
