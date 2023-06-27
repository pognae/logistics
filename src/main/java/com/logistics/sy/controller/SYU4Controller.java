package com.logistics.sy.controller;
 
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
import com.logistics.sy.service.SYU4Service;

import lombok.RequiredArgsConstructor;  

@RestController
@RequiredArgsConstructor
public class SYU4Controller { 
	
	@Autowired
	private final SYU4Service su4Service;
	
    /* 
     * Role Group List 조회
     * */
    @ResponseBody
    @GetMapping(value = "/sy/groupSelect.do")
    public ModelAndView userSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", su4Service.getGroupSelect(param, userInfo));
    	return mv;
    }  
    
    /* 
     * Role Group List 저장
     * */
    @ResponseBody
    @PostMapping(value = "/sy/groupSave.do")
    public ModelAndView userSave(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	su4Service.setSYU4Save(param, userInfo);
    	mv.addObject("list", su4Service.getGroupSelect(param, userInfo));
        return mv;
    }
    
    /* 
     *  Role Group List 삭제
     * */
    @ResponseBody 
    @RequestMapping(value = "/sy/groupDelete.do", method = RequestMethod.DELETE)
    public ModelAndView roleProgramDelete(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	su4Service.setSYU4GroupDelete(param);
    	mv.addObject("list", su4Service.getGroupSelect(param, userInfo));
    	return mv;
    }
    
    /*
     * Group SelectBox 조회
     * */
	@ResponseBody
	@GetMapping(value = "/sy/groupSelectBox.do")
	public ModelAndView roleSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", su4Service.getGroupSelectBox(param, userInfo));
    	return mv;
    }

}
