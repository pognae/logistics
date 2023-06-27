package com.logistics.md.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.md.service.MDC7Service;
import com.logistics.md.service.MDP1Service;
import com.logistics.md.service.MDP3Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MDP5Controller implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final MDP3Service mdp3Service;
	private final MDP1Service mdp1Service;
	private final MDC7Service mdc7Service;
	
	@GetMapping("/md/mdp5Init.do")
	public ModelAndView mdp5Init(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		
		Map<String, Object> ownerParam = new HashMap<>();
    	ownerParam.put("owdelyn" , "N");
    	mv.addObject("ownerky", mdp1Service.getOwnerSelectBox(ownerParam, userInfo));
    	
    	Map<String, Object> comcodeParam = new HashMap<>();
		comcodeParam.put("comcdky" , "CUSPECD");
		mv.addObject("cuspecd", mdc7Service.getMcodemSelect(comcodeParam, userInfo));

		comcodeParam.put("comcdky" , "CUSGRO2");
		mv.addObject("cusgro2", mdc7Service.getMcodemSelect(comcodeParam, userInfo));
		return mv;
    }
	
    /* 
     * Customer List 조회
     * */
	@GetMapping("/md/mdp5Select.do")
	public ModelAndView getMcustmaSelect(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list",  mdp3Service.getMcusmaList(param , userInfo));
		return mv;
	}
	
    /* 
     * Customer List 저장
     * */
	@PostMapping("/md/mdp5Save.do")
	public ModelAndView saveMcustmaList(@RequestBody Map<String,Object> param , @AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mdp3Service.saveMdp3(param, userInfo);
		mv.addObject("list", mdp3Service.getMcusmaList(param , userInfo));
		return mv;
	}
	
	
	
}
