package com.logistics.md.controller;

import java.util.HashMap;
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

import com.logistics.md.domain.MskuspVO;
import com.logistics.md.service.MDC7Service;
import com.logistics.md.service.MDP2Service;
import com.logistics.md.service.MDP3Service;
import com.logistics.md.service.MDU1Service;
import com.logistics.sy.domain.UserVo;

import lombok.RequiredArgsConstructor; 

@RestController
@RequiredArgsConstructor
public class MDU1Controller {
	
	/*
	 * 2022.06.27
	 * SWH
	 * [MDU2] 상품지시서
	 */
	@Autowired
	private final MDP2Service mdp2Service;
	
	@Autowired
	private final MDP3Service mdp3Service;
	
	//@Autowired
	private final MDC7Service mdc7Service;
	
	@Autowired
	private final MDU1Service mdu1Service;
	
	
	//페이지 조회
    @ResponseBody
    @GetMapping(value = "/md/mdu1InitSelect.do")    
	public ModelAndView getMdu1InitSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	Map<String, Object> returnParam = new HashMap<>();
    	
    	
    	
		returnParam.put("selectMskuspList", mdu1Service.getMskuspListSelect(param, userInfo));
		
    	param.put("comcdky", "SKUGR02");
    	returnParam.put("seasonaItem", mdc7Service.getMcodemSelect(param, userInfo));		//시즌 코드
    	
    	param.put("comcdky", "SIZECOD");
    	returnParam.put("sizeItem", mdc7Service.getMcodemSelect(param, userInfo));		//사이즈 코드
    	
    	param.put("comcdky", "SPROCNO");
    	returnParam.put("materialProcessItem", mdc7Service.getMcodemSelect(param, userInfo));	//공정 코드
    	
    	param.put("ownerky", userInfo.getOwnerky());
    	param.put("ptnrtyp", "VENDER");
    	param.put("notptngro1", "WHOLESALE");
    	param.put("ptdelyn", "N");
    	returnParam.put("mptnmaItem", mdp2Service.getMp2SelectBox(param, userInfo));	//공정 (공장 정보)
    	
    	param.put("custtyp", "WHOLESALE");
    	
    	
    	returnParam.put("customerItem", mdp3Service.getCustomerSelectBox(param, userInfo));		//브랜드
    	returnParam.put("compkey", userInfo.getCompkey());
    	returnParam.put("ownerky", userInfo.getOwnerky());
    	
    	
    	param.put("comcdky", "CUTITEM");
    	param.put("cdcate1", "UP");
    	returnParam.put("mskusiUpItem", mdc7Service.getMcodemSelect(param, userInfo));	//공정 코드
    	param.put("cdcate1", "DOWN");
    	returnParam.put("mskusiDownItem", mdc7Service.getMcodemSelect(param, userInfo));	//공정 코드
    	
		mv.addObject("item", returnParam);
		mv.addObject("code", "200");  
    	
        return mv;
    }
    
    //작업지시서 리스트 블러오기
    @ResponseBody
    @GetMapping(value = "/md/mdu1MskuspListSelect.do")    
	public ModelAndView getMdu1MskuspListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	Map<String, Object> returnParam = new HashMap<>();

		returnParam.put("selectMskuspList", mdu1Service.getMskuspListSelect(param, userInfo));
		
		mv.addObject("item", returnParam);
		mv.addObject("code", "200");  
    	
        return mv;
    }
    
    //메인 작업지시서 Init
    @ResponseBody
    @GetMapping(value = "/md/mdu1InitSelectData.do")    
	public ModelAndView getMdu1InitDataSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("item", mdu1Service.getMdu1InitDataSelect(param, userInfo));
		mv.addObject("code", "200");  
        return mv;
    }
    
    /* 
     *  mdu1 저장
     * */
      
    @PostMapping(value = "/md/mdu1Save.do")
    @ResponseBody
    public ModelAndView mdu1Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	Map<String, Object> result = mdu1Service.setMDU1Save(param, userInfo);

		if((int)result.get("resultType") > 0) {
			mv.addObject("skutkey", result.get("skutkey"));
			mv.addObject("skuskey", result.get("skuskey"));
			mv.addObject("resultType", (int)result.get("resultType"));
			mv.addObject("code", "200");
		}
		else {
			mv.addObject("code", "300");
			mv.addObject("message", "정보를 입력해 주세요.");
		}
    	
    	return mv;
    }
    
    
    @ResponseBody
    @GetMapping(value = "/md/mdu6InitData.do")    
	public ModelAndView getMdu6InitDataSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		
		ModelAndView mv = new ModelAndView("jsonView");

    	param.put("compkey", userInfo.getCompkey());
    	param.put("ownerky", userInfo.getOwnerky());
    	mv.addObject("data", param);
		mv.addObject("code", "200");

        return mv;
    }
	
	//리스트 조회
    @ResponseBody
    @GetMapping(value = "/md/mdu6List.do")    
	public ModelAndView getMdu6ListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");

		param.put("userData", userInfo);
		
    	List<MskuspVO> dataList = mdu1Service.getMdu6ListSelect(param, userInfo);
		mv.addObject("code", "200");
		mv.addObject("item", dataList);

        return mv;
    }
    
    //MDU6 Save
    @PostMapping(value = "/md/mdu6Save.do")
    @ResponseBody
    public ModelAndView setMdu6Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mdu1Service.setMdu6Save(param, userInfo);
    	mv.addObject("code", "200");
    	return mv;
    }
}
