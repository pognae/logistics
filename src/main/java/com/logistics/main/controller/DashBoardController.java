package com.logistics.main.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.logistics.main.service.DashBoardService;
import com.logistics.sy.domain.UserVo;
import com.logistics.sy.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DashBoardController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private final DashBoardService dashBoardService;
	
	@Autowired
	private final UserService userService;
	
	/**********************************
	 * DashBoard - 데이터s
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 **********************************/
	@ResponseBody
    @GetMapping(value = "/main/DashBoardList.do")    
	public ModelAndView getDashBoardListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) throws JsonMappingException, JsonProcessingException{
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	List<Map<String, Object>> boardData 	= userService.getBinBlurList(param, userInfo);
    	Map<String, Object> infoData 			= dashBoardService.getDashBoardDataSelect(param, userInfo);

        mv.addObject("boardData", boardData);
        mv.addObject("infoItem", infoData);
        mv.addObject("code", "200");
    	
        return mv;
    }
	
	@ResponseBody
    @GetMapping(value = "/main/DashBoardOwnerList.do")    
	public ModelAndView getDashBoardOwnerListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) throws JsonMappingException, JsonProcessingException{
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	List<Map<String, Object>> boardData 	= userService.getBinBlurList(param, userInfo);
    	Map<String, Object> infoData 			= dashBoardService.getDashBoardOwnerDataSelect(param, userInfo);

        mv.addObject("boardData", boardData);
        mv.addObject("infoItem", infoData);
        mv.addObject("code", "200");
    	
        return mv;
    }
	
	@ResponseBody
    @GetMapping(value = "/main/DashBoardVenderList.do")    
	public ModelAndView getDashBoardVenderListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) throws JsonMappingException, JsonProcessingException{
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	List<Map<String, Object>> boardData 	= userService.getBinBlurList(param, userInfo);
    	Map<String, Object> infoData 			= dashBoardService.getDashBoardVenderDataSelect(param, userInfo);

        mv.addObject("boardData", boardData);
        mv.addObject("infoItem", infoData);
        mv.addObject("code", "200");
    	
        return mv;
    }

	@ResponseBody
    @GetMapping(value = "/main/DashBoardWholesaleList.do")    
	public ModelAndView getDashBoardWholesaleListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) throws JsonMappingException, JsonProcessingException{
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	List<Map<String, Object>> boardData 	= userService.getBinBlurList(param, userInfo);
    	Map<String, Object> infoData 			= dashBoardService.getDashBoardWholesaleDataSelect(param, userInfo);

        mv.addObject("boardData", boardData);
        mv.addObject("infoItem", infoData);
        mv.addObject("code", "200");
    	
        return mv;
    }
}
