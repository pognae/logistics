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
import com.logistics.sy.service.WorkbenchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WorkbenchController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private final WorkbenchService workbenchservice;
	
    /* 
     * syw1 : Program Master List 조회
     * */
    @ResponseBody
    @GetMapping(value = "/sy/selectSyw1List.do")
    public ModelAndView selectWma1List(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", workbenchservice.getSyw1List(param, userInfo));
    	return mv;
    }
    
    /* 
     * syw1 : Program Master List 저장
     * */
    @ResponseBody
    @PostMapping(value = "/sy/syw1Save.do")
    public ModelAndView syw1Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	workbenchservice.setSyw1List(param, userInfo);
    	mv.addObject("list", workbenchservice.getSyw1List(param, userInfo));
    	return mv;
    }
    
    /* 
     *  syw1 : Program Master List 삭제
     * */
    @ResponseBody 
    @RequestMapping(value = "/sy/syw1Delete.do", method = RequestMethod.DELETE)
    public ModelAndView syw1Delete(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	workbenchservice.setSYW1Delete(param, userInfo);
    	mv.addObject("list", workbenchservice.getSyw1List(param, userInfo));
    	return mv;
    }
    
    /* 
     * syw2 : 프로그램 사용 이력 List 조회
     * */
    @ResponseBody
    @GetMapping(value = "/sy/selectSyw2List.do")
    public ModelAndView selectSyw2List(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", workbenchservice.getSyw2List(param, userInfo));
    	return mv;
    }
    
    /* 
     * syw3 : 사용자 접속 이력 List 조회
     * */
    @ResponseBody
    @GetMapping(value = "/sy/selectSyw3List.do")
    public ModelAndView selectSyw3List(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", workbenchservice.getSyw3List(param, userInfo));
    	return mv;
    }
    
    /* 
     * syw4 : 인쇄 이력 List 조회
     * */
    @ResponseBody
    @GetMapping(value = "/sy/selectSyw4List.do")
    public ModelAndView selectSyw4List(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", workbenchservice.getSyw4List(param, userInfo));
    	return mv;
    }
}
