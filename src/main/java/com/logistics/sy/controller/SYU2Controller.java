package com.logistics.sy.controller;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.logistics.sy.domain.UserVo;
import com.logistics.sy.service.SYU2Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SYU2Controller implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private final SYU2Service su2Service;

	/*
	 * Menu Header List 조회
	 */
	@ResponseBody
	@GetMapping(value = "/sy/selectHeaderMenu.do")
	public ModelAndView selectHeaderMenu(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", su2Service.getHeaderMenuList(param, userInfo));
		return mv;
	}

	/*
	 * Menu Item List 조회
	 */
	@ResponseBody
	@GetMapping(value = "/sy/selectItemMenu.do")
	public ModelAndView selectItemMenu(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", su2Service.getItemMenuList(param, userInfo));
		return mv;
	}

	/*
	 * Menu Header&Item List 삭제
	 */
	@ResponseBody
	@RequestMapping(value = "/sy/deleteHeaderMenu.do", method = RequestMethod.DELETE)
	public ModelAndView deleteHeaderMenu(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		su2Service.setSYU2HeadDelete(param);
		mv.addObject("list", su2Service.getHeaderMenuList(param, userInfo));
		return mv;
	}
	
	/*
	 * Menu Item List 삭제
	 */
	@ResponseBody
	@RequestMapping(value = "/sy/deleteItemMenu.do", method = RequestMethod.DELETE)
	public ModelAndView deleteItemMenu(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		su2Service.setSYU2ItemDelete(param);
		mv.addObject("list", su2Service.getHeaderMenuList(param, userInfo));
		return mv;
	}

	/*
	 * Menu Header&Item List 저장
	 */
	@ResponseBody
	@PostMapping(value = "/sy/menuSave.do")
	public ModelAndView menuSave(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		su2Service.setSYU2Save(param, userInfo);
		mv.addObject("list", su2Service.getHeaderMenuList(param, userInfo));
		return mv;
	}

	/*
     * Menu SelectBox 조회
     * */
	@ResponseBody
	@GetMapping(value = "/sy/menuSelectBox.do")
	public ModelAndView areaSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", su2Service.getMenuSelectBox(param, userInfo));
		return mv;
    }

}
