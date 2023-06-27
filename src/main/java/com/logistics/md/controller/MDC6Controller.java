package com.logistics.md.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.service.MDC6Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor; 

@RestController
@RequiredArgsConstructor
public class MDC6Controller {
	
	/*
	 * 2022.06.02
	 * SWH
	 * [MDC6] 사유코드 컨트롤러 
	 */
	@Autowired
	private final MDC6Service mdc6Service;
	
	
	@ResponseBody
    @GetMapping(value = "/md/mdc6InitData.do")    
	public ModelAndView getMdc6InitDataSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		
		ModelAndView mv = new ModelAndView("jsonView");

		param.put("compkey", userInfo.getCompkey());
        mv.addObject("data", param);
    	mv.addObject("code", "200");

        return mv;
    }
	
	@ResponseBody
    @GetMapping(value = "/md/mdc6DoccateGroupList.do")    
	public ModelAndView getDoccateGroupListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		List<MdocmaDTO> dataList = mdc6Service.getDoccateGroupListSelect(param, userInfo); 

        mv.addObject("item", dataList);
    	mv.addObject("code", "200");

        return mv;
    }
	
	@ResponseBody
    @GetMapping(value = "/md/mdc6DoctypeList.do")    
	public ModelAndView getDoctypeListSelectGet(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
        List<MdocmaDTO> dataList = mdc6Service.getDoctypeListSelect(param, userInfo); 
        mv.addObject("item", dataList);
        return mv;
    }
	
	//리스트 조회
    @ResponseBody
    @GetMapping(value = "/md/mdc6List.do")    
	public ModelAndView getCodeListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");

       	param.put("userData", userInfo);
        List<MrscmaDTO> dataList = mdc6Service.getMrscmaListSelect(param, userInfo);
        mv.addObject("item", dataList);
    	mv.addObject("code", "200");

        return mv;
    }
    
    
    /* 
     *  mdc6 그리드 저장
     * */
    @PostMapping(value = "/md/mdc6Save.do")
    @ResponseBody
    public ModelAndView mdc6Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mdc6Service.setMDC6Save(param, userInfo);
    	mv.addObject("code", "200");  
    	return mv;
    }
    
    /* 
     *  mdc6 selectBox
     * */
    @GetMapping("/md/mdc6SelectBox.do")
    @ResponseBody
    public ModelAndView mdc6SelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdc6Service.mdc6SelectBox(param, userInfo));
    	mv.addObject("code", "200");  
    	return mv;
    }
}
