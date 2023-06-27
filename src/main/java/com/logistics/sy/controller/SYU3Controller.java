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
import com.logistics.sy.service.SYU3Service;

import lombok.RequiredArgsConstructor;  

@RestController
@RequiredArgsConstructor
public class SYU3Controller implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private final SYU3Service su3Service;
	
	/*
	 * Role Master List 조회
	 */
    @ResponseBody
    @GetMapping(value = "/sy/selectMRole.do")    
    public ModelAndView roleSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
        mv.addObject("list", su3Service.getMasterRoleList(param, userInfo));
        return mv;
    }
    
    /*
	 * Role Program & Warehouse List 조회
	 */
    @ResponseBody
    @GetMapping(value = "/sy/selectPWRole.do")
    public ModelAndView rolePWSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
        mv.addObject("PList", su3Service.getProgramRoleList(param, userInfo));
        mv.addObject("WList", su3Service.getWarehouseRoleList(param, userInfo)); 
        return mv;
    }
    
	/*
	 * Role Master & Program & Warehouse List 저장
	 */
	@ResponseBody
	@PostMapping(value = "/sy/roleSave.do")
	public ModelAndView roleSave(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		su3Service.setSYU3Save(param, userInfo);
		mv.addObject("list", su3Service.getMasterRoleList(param, userInfo));
		return mv;
	}

    /* 
     *  Role Master & Program & Warehouse List 삭제
     * */
    @ResponseBody 
    @RequestMapping(value = "/sy/roleMasterDelete.do", method = RequestMethod.DELETE)
    public ModelAndView roleMasterDelete(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	su3Service.setSYU3MasterDelete(param);
    	mv.addObject("list", su3Service.getMasterRoleList(param, userInfo));
    	return mv;
    }
    
    /* 
     *  Role Program List 삭제
     * */
    @ResponseBody 
    @RequestMapping(value = "/sy/roleProgramDelete.do", method = RequestMethod.DELETE)
    public ModelAndView roleProgramDelete(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	su3Service.setSYU3ProgramDelete(param);
    	mv.addObject("list", su3Service.getMasterRoleList(param, userInfo));
    	return mv;
    }
    
    /* 
     *  Role Warehouse List 삭제
     * */
    @ResponseBody 
    @RequestMapping(value = "/sy/roleWarehouseDelete.do", method = RequestMethod.DELETE)
    public ModelAndView roleWarehouseDelete(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	su3Service.setSYU3WarehouseDelete(param);
    	mv.addObject("list", su3Service.getMasterRoleList(param, userInfo));
    	return mv;
    }
    
    /*
     * Role SelectBox 조회
     * */
	@ResponseBody
	@GetMapping(value = "/sy/roleSelectBox.do")
	public ModelAndView roleSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", su3Service.getRoleSelectBox(param, userInfo));
    	return mv;
    }
 

}
