package com.logistics.md.controller;

import java.io.Serializable;
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

import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MwarmaVO;
import com.logistics.md.service.MDC7Service;
import com.logistics.md.service.MDO2Service;
import com.logistics.md.service.MDP1Service;
import com.logistics.md.service.MDP2Service;
import com.logistics.md.service.MDP3Service;
import com.logistics.md.service.MDU2Service;
import com.logistics.sy.domain.UserVo;
import com.logistics.sy.service.UserService;

import lombok.RequiredArgsConstructor; 

@RestController
@RequiredArgsConstructor
public class UnitsController implements Serializable{
	
	/*
	 * 2022.06.10
	 * SWH
	 * [MDU2] 재고관리
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private final MDU2Service mdu2Service;
	
	@Autowired
	private final MDO2Service mdo2Service;
	
	@Autowired
	private final MDC7Service mdc7Service;
	
	@Autowired
	private final MDP1Service mdp1Service;
	
	@Autowired
	private final MDP2Service mdp2Service;
	
	@Autowired
	private final MDP3Service mdp3Service;
	
	@Autowired
	private final UserService userService;
	
	//페이지 조회
    @ResponseBody
    @GetMapping(value = "/md/mdu2InitData.do")    
	public ModelAndView getMdu2InitData(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	Map<String, Object> returnParam = new HashMap<>();
    	
    	param.put("compkey", userInfo.getCompkey());
    	param.put("comcdky", "SKUGR02");
    	returnParam.put("seasonaItem", mdc7Service.getMcodemSelect(param, userInfo));		//시즌 코드
    	
    	param.put("comcdky", "SIZECOD");
    	returnParam.put("sizeItem", mdc7Service.getMcodemSelect(param, userInfo));		//사이즈 코드
    	
    	param.put("whdelyn", "N");
    	returnParam.put("warekeyList", mdo2Service.getWarehouseUserSelectBox(param, userInfo));		//창고
    	
    	param.put("useract", userInfo.getUseract());
    	
    	param.put("comcdky", "SKUGR01");
    	param.put("cdcate1", "L");
    	returnParam.put("skugr01Item", mdc7Service.getMcodemSelect(param, userInfo));		//SKUGR01 코드
    	
    	returnParam.put("skuat01Item", userService.findDesigner(param, userInfo));			//SKU Attribute1 디자이너
    	param.put("ownerky", userInfo.getOwnerky());
    	
    	param.put("ptnrtyp", "VENDER");
    	param.put("notptngro1", "WHOLESALE");
    	param.put("ptdelyn", "N");
    	returnParam.put("mptnmaItem", mdp2Service.getMp2SelectBox(param, userInfo));	//공장

    	param.put("custtyp", "WHOLESALE");
    	param.put("custkey", userInfo.getCustkey().trim());
    	returnParam.put("custkeyList", mdp3Service.getCustomerSelectBox(param, userInfo));				//고객코드	  - mcusma => WHOLESALE
    	returnParam.put("ownerkyList", mdp1Service.getMowrmaSelect(param , userInfo));
    	
    	returnParam.put("compkey", userInfo.getCompkey());
    	returnParam.put("ownerky", userInfo.getOwnerky());
    	returnParam.put("custkey", userInfo.getCustkey());
    	
		mv.addObject("item", returnParam);
		mv.addObject("code", "200");  
    	
        return mv;
    }
    
	//페이지 조회
    @ResponseBody
    @GetMapping(value = "/md/mdu2SearchOptionSelect.do")    
	public ModelAndView getMdu2SearchOptionSelect(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	Map<String, Object> returnParam = new HashMap<>();
    	
    	boolean ownerWriteAuthCheck = "BINBLUR".equals(userInfo.getUsertyp()) ? true : false;
		List<MwarmaVO> waremaList 	= mdo2Service.getMwarmaListSelect(param, userInfo);
		
		returnParam.put("waremaList", waremaList);
		returnParam.put("ownerWriteAuthCheck", ownerWriteAuthCheck);
		
		mv.addObject("returnData", returnParam);
		mv.addObject("code", "200");  
    	
        return mv;
    }
    
    //단일리스트 조회
    @ResponseBody
    @GetMapping(value = "/md/mdu2Select.do")    
	public ModelAndView getMdu2Select(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	String objcbid = (String)param.get("objcbid");

		param.put("userData", userInfo);
		
    	List<MskuwcDTO> dataList = mdu2Service.getMdu2ListSelect(param, userInfo);
    	mv.addObject("objcbid", objcbid);
    	mv.addObject("code", "200");
		mv.addObject("item", dataList);
		mv.addObject("itemColumn", param.get("imgcolumn"));

        return mv;
    }
    
    /* 
     * 상품관리 List 조회
     * */
    @ResponseBody
    @GetMapping(value = "/md/mdu2List.do")    
	public ModelAndView getMdu2ListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", mdu2Service.getMdu2ListSelect(param, userInfo));
    	return mv;
    }
    
    /* 
     *  mdu2 상품생성
     * */
    @PostMapping(value = "/md/mdu2SkuCreateSave.do")
    @ResponseBody
    public ModelAndView mdu2SkuCreateSave(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	
    	Map<String, Object> result = mdu2Service.setMDU2SkuCreateSave(param, userInfo);
    	if("OK".equals(result.get("status"))) {
    		mv.addObject("code", "200");  
    	}
    	else if("DUPLICATION".equals(result.get("status"))){
    		mv.addObject("code", "300");  
    	}
    	
    	return mv;
    }
    
    
    /* 
     *  mdu2 그리드 저장
     * */
    @PostMapping(value = "/md/mdu2Save.do")
    @ResponseBody
    public ModelAndView mdu2Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	
		mdu2Service.setMDU2Save(param, userInfo);
		mv.addObject("code", "200");  
    	
    	return mv;
    }
    
    /* 
     *  mdu2 이미지 모달 팝업 저장
     * */
    @PostMapping(value = "/md/mdu2SaveImage.do")
    @ResponseBody
    public ModelAndView mdu2SaveImage(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");

		param.put("userData", userInfo);

		mdu2Service.setMDU2SaveImage(param, userInfo);
		mv.addObject("code", "200");  
            
    	return mv;
    }
    
    //팝업
    @ResponseBody
    @GetMapping(value = "/md/popup/mdu2PopupSelect.do")    
	public ModelAndView getMdu2PopupSelect(@RequestParam Map<String, Object> param) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	Map<String, Object> returnParam = new HashMap<>();

    	mv.addObject("returnData", returnParam);
    	mv.addObject("code", "200");  
        
        return mv;
    }
    

    //MDU7 페이지 조회
    @ResponseBody
    @GetMapping(value = "/md/mdu7InitData.do")    
	public ModelAndView getMdu7InitData(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	Map<String, Object> returnParam = new HashMap<>();
    	
    	param.put("compkey", userInfo.getCompkey());
    	param.put("comcdky", "SKUGR02");
    	returnParam.put("seasonaItem", mdc7Service.getMcodemSelect(param, userInfo));		//시즌 코드
    	
    	param.put("comcdky", "SIZECOD");
    	returnParam.put("sizeItem", mdc7Service.getMcodemSelect(param, userInfo));		//사이즈 코드
    	
    	param.put("whdelyn", "N");
    	returnParam.put("warekeyList", mdo2Service.getWarehouseUserSelectBox(param, userInfo));		//창고
    	
    	param.put("useract", userInfo.getUseract());
    	
    	param.put("comcdky", "SKUGR01");
    	param.put("cdcate1", "L");
    	returnParam.put("skugr01Item", mdc7Service.getMcodemSelect(param, userInfo));		//SKUGR01 코드
    	
    	returnParam.put("skuat01Item", userService.findDesigner(param, userInfo));			//SKU Attribute1 디자이너
    	param.put("ownerky", userInfo.getOwnerky());
    	
    	param.put("ptnrtyp", "VENDER");
    	param.put("notptngro1", "WHOLESALE");
    	param.put("ptdelyn", "N");
    	returnParam.put("mptnmaItem", mdp2Service.getMp2SelectBox(param, userInfo));	//공장

    	param.put("custtyp", "WHOLESALE");
    	param.put("custkey", userInfo.getCustkey().trim());
    	returnParam.put("custkeyList", mdp3Service.getCustomerSelectBox(param, userInfo));				//고객코드	  - mcusma => WHOLESALE
    	returnParam.put("ownerkyList", mdp1Service.getMowrmaSelect(param , userInfo));
    	
    	returnParam.put("compkey", userInfo.getCompkey());
    	returnParam.put("ownerky", userInfo.getOwnerky());
    	returnParam.put("custkey", userInfo.getCustkey());
    	
		mv.addObject("item", returnParam);
		mv.addObject("code", "200");  
    	
        return mv;
    }
    
	//MDU7 페이지 조회
    @ResponseBody
    @GetMapping(value = "/md/mdu7SearchOptionSelect.do")    
	public ModelAndView getMdu7SearchOptionSelect(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	Map<String, Object> returnParam = new HashMap<>();
    	
    	boolean ownerWriteAuthCheck = "BINBLUR".equals(userInfo.getUsertyp()) ? true : false;
		List<MwarmaVO> waremaList 	= mdo2Service.getMwarmaListSelect(param, userInfo);
		
		returnParam.put("waremaList", waremaList);
		returnParam.put("ownerWriteAuthCheck", ownerWriteAuthCheck);
		
		mv.addObject("returnData", returnParam);
		mv.addObject("code", "200");  
    	
        return mv;
    }
    
    //단일리스트 조회
    @ResponseBody
    @GetMapping(value = "/md/mdu7Select.do")    
	public ModelAndView getMdu7Select(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	String objcbid = (String)param.get("objcbid");

		param.put("userData", userInfo);
		
    	List<MskuwcDTO> dataList = mdu2Service.getMdu2ListSelect(param, userInfo);
    	mv.addObject("objcbid", objcbid);
    	mv.addObject("code", "200");
		mv.addObject("item", dataList);
		mv.addObject("itemColumn", param.get("imgcolumn"));

        return mv;
    }
    
    /* 
     * MDU7 List 조회
     * */
    @ResponseBody
    @GetMapping(value = "/md/mdu7List.do")    
	public ModelAndView getMdu7ListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", mdu2Service.getMdu2ListSelect(param, userInfo));
    	return mv;
    }
    
    
    @PostMapping(value = "/md/mdu7Save.do")
    @ResponseBody
    public ModelAndView mdu7Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> result = mdu2Service.setMDU7Save(param, userInfo);
		if("DUPLICATION".equals((String) result.get("status"))){
			mv.addObject("code", "300");  
		}else {
			mv.addObject("code", "200");  
		}
		
    	
    	return mv;
    }
    
    
    
    
    
    
    
    
    
    /*
     * Skuwc SelectBox 조회 (상품명 + 수량)
     * */
	@ResponseBody
	@GetMapping("/md/skuwcSelectBoxQty.do")
	public ModelAndView skuwcSelectBoxQty(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("item", mdu2Service.getSkuwcSelectBoxQty(param, userInfo));
		return mv;
    }
    
	/*
     * Skuwc SelectBox 조회
     * */
	@ResponseBody
	@GetMapping("/md/skuwcSelectBox.do")
	public ModelAndView skuwcSelectBox(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("item", mdu2Service.getSkuwcSelectBox(param, userInfo));
		mv.addObject("code", "200");
        
		return mv;
    }
	
	/* 
     * Skuwc SelectBox 속성값 포함 조회 ( 공장별 )
     * */
    @ResponseBody
    @PostMapping(value = "/md/skuwcSelectBoxPost.do")
    public ModelAndView skuwcSelectBoxPost(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdu2Service.getSkuwcSelectBox(param, userInfo));
    	return mv;
    }
    
    /* 
     * Skuwc SelectBox 속성값 포함 조회 ( OMS2 주문등록 화면용 )
     * */
    @ResponseBody
    @PostMapping(value = "/md/skuwcOms2SB.do")
    public ModelAndView skuwcOms2SB(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdu2Service.getSkuwcOms2SB(param, userInfo));
    	return mv;
    }  
    
    /*
     * Skuwc SelectBox 조회 (OMB1)
     * */
	@ResponseBody
	@GetMapping("/md/skuwcSelectBoxOmw1.do")
	public ModelAndView skuwcSelectBoxOmb1(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo) {
		
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("item", mdu2Service.getSkuwcSelectBoxOmw1(param, userInfo));
		mv.addObject("code", "200");
        
		return mv;
    }
	
	@ResponseBody
    @PostMapping(value = "/md/skuwcSelectBoxOmw1Post.do")
    public ModelAndView skuwcSelectBoxOmb1Post(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdu2Service.getSkuwcSelectBoxOmw1(param, userInfo));
    	return mv;
    }

    //combo text만 화면에서 보여줘야 함..
    @ResponseBody
    @GetMapping("/md/skuwcSelectBoxByWarekey.do")
    public ModelAndView skuwcSelectBoxByWarekey(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdu2Service.getSkucSelectBoxByWarekey(param ,userInfo));
		mv.addObject("code", "200");
    	
    	return mv;
    }
    
  //combo text 선택 시 화면에서 다른 컬럼의 value 값 자동 변경
    @ResponseBody
    @GetMapping("/md/skuwcSelectBoxtByWarekey.do")
    public ModelAndView skuwcSelectBoxtByWarekey(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("item", mdu2Service.getSkucSelectBoxtByWarekey(param ,userInfo));
		mv.addObject("code", "200");
    	
    	return mv;
    }
}
