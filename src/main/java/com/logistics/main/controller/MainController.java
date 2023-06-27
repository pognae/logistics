package com.logistics.main.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.sy.domain.UserVo;
import com.logistics.sy.service.UserService;

import lombok.RequiredArgsConstructor; 

@RestController
@RequiredArgsConstructor
public class MainController {
	
	@Autowired
	LocaleResolver localeResolver;

    @Autowired
    MessageSource messageSource;
    
    @Autowired
    UserService userService;
    
	//private final UserService userService;
	//private final UserVo userVO;
	
    //CustomAuthenticationSuccessHandler.onAuthenticationSuccess
    //<A href="/main/mainPage1LeftSD.do">[01 Left Single Doc 메인] </A>  MAIN1SD
    //<A href="/main/mainPage2LeftTAB.do">[02 Left TAB Doc 메인] </A>  MAIN2TAB
    //<A href="/main/mainPage3TopTAB.do">[03 Top TAB Doc 메인] </A> MAIN3TAB 
    /* ********************************************
     * 01 Left layout [Single Document]
     * 메인 페이지 이동 
     ******************************************** */
    @GetMapping("/main/mainPage1LeftSD.do")
    public ModelAndView leftMainSD(@RequestParam Map<String, Object> params, HttpServletRequest request ){
    	HttpSession session = request.getSession();
    	session.setAttribute("themety", "MAIN1SD");
    	
    	ModelAndView mv = new ModelAndView("jsonView");				//jsonView = 데이터를 JSON형태의 응답으로 반환하기 위한 뷰 이름
    	mv.addObject("gprogcmd", params.get("gprogcmd"));			//데이터 전송, 데이터를 보낼때는 addObject( ) 메소드 이용, HTTP요청에서 gprogcmd 파라미터 값을 가져옴
    	mv.setViewName("/main/mainPage1SD");						//뷰의 이름, mainPage1SD.html
        return mv; 
    }    
     
    /* ********************************************
     * 02 Left layout [TAB Document] 
     * 메인 페이지 이동
     ******************************************** */
    @GetMapping("/main/mainPage2LeftTAB.do")
    public ModelAndView leftMainTab(@RequestParam Map<String, Object> params, HttpServletRequest request){
    	HttpSession session = request.getSession();
    	session.setAttribute("themety", "MAIN2TAB");
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.setViewName("/main/mainPage2TAB");
    	return mv;
    }
    
    /* ********************************************
     * 03 Top layout [Tab Document ]
     * 메인 페이지 이동 
     ******************************************** */    
    @GetMapping("/main/mainPage3TopTAB.do")
    public ModelAndView topMainTab(@RequestParam Map<String, Object> params, HttpServletRequest request){
    	HttpSession session = request.getSession();
    	session.setAttribute("themety", "MAIN3TAB");
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.setViewName("/main/mainPage3TAB");
    	return mv;
    }
    
    
    
    /* ********************************************
     * 01 OPENTYP = SINGLE
     * 메뉴클릭시 페이지 이동 공통 컨트롤러 Get 방식
     ******************************************** */
    @GetMapping("/main/singlePageCMD.do")
    public ModelAndView menuSinglPage(@RequestParam Map<String, Object> params, @AuthenticationPrincipal UserVo userInfo) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.setViewName("/main/mainPage1SD");
    	
        // 사용자 접속 프로그램 이력 저장 
		userService.insertUserProgram(params, userInfo);
        
        mv.addObject("gprogrid", params.get("gprogrid"));
        mv.addObject("gprogcmd", params.get("gprogcmd"));
        mv.addObject("gnatitle", params.get("gnatitle"));
        mv.addObject("gprodata", params.get("gprodata"));
        mv.addObject("userRole", userInfo.getUserRoleVOMap().get(params.get("gprogrid")));
        
        params.remove("gprogrid");
        params.remove("gprogcmd");
        params.remove("gnatitle");
        params.remove("gprodata");
        if(params.size() > 0) {
        	mv.addObject("params", params);
        }
        
        return mv;  
    }
    
    /* ********************************************
     * 01. 페이지 이동 공통 컨트롤러 Post 방식
     ******************************************** */
    @ResponseBody
    @PostMapping(value = "/main/singlePagePostCMD.do")
    public ModelAndView menuSinglPagePost(@RequestBody MultiValueMap<String, Object> params, @AuthenticationPrincipal UserVo userInfo, Model model) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	mv.setViewName("redirect:/main/mainPage1SD");
    	
    	Map<String, Object> map = new HashMap<>();
    	map.put("gprogrid", params.get("gprogrid").get(0));
    	map.put("gnatitle", params.get("gnatitle").get(0));
    	
    	// 사용자 접속 프로그램 이력 저장 
		userService.insertUserProgram(map, userInfo);
        
        mv.addObject("gprogrid", params.get("gprogrid").get(0));
        mv.addObject("gprogcmd", params.get("gprogcmd").get(0));
        mv.addObject("gnatitle", params.get("gnatitle").get(0));
        
        mv.addObject("userRole", userInfo.getUserRoleVOMap().get(params.get("gprogrid").get(0)));
        
        params.remove("gprogrid");
        params.remove("gprogcmd");
        params.remove("gnatitle");
        if(params.size() > 0) {
        	mv.addObject("params", params);
        }
        
        return mv;
    }
    
    /* ********************************************
     * 02 OPENTYP = TAB
     * 메뉴클릭시 페이지 이동 공통 컨트롤러  
     ******************************************** */
	@GetMapping("/main/tabPageCMD.do") 
	public ModelAndView menuTabPage(@RequestParam Map<String, Object> params, @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.setViewName((String) params.get("gprogcmd"));
		
        // 사용자 접속 프로그램 이력 저장 
        userService.insertUserProgram(params, userInfo);
        
        mv.addObject("gprogrid", params.get("gprogrid"));
        mv.addObject("gprogcmd", params.get("gprogcmd"));
        mv.addObject("gnatitle", params.get("gnatitle"));
        mv.addObject("gprodata", params.get("gprodata"));
        mv.addObject("userRole", userInfo.getUserRoleVOMap().get(params.get("gprogrid")));
        
        params.remove("gprogrid");
        params.remove("gprogcmd");
        params.remove("gnatitle");
        params.remove("gprodata");
        if(params.size() > 0) {
        	mv.addObject("params", params);
        }
        
    	return mv;
	} 


    /* ********************************************
     * 공통 : Modal 페이지 링크 
     ******************************************** */
	@GetMapping("/main/modalLoad") 
	public ModelAndView modalLoadPage(@RequestParam Map<String, Object> params) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.setViewName((String) params.get("gprogurl"));
		mv.addObject("params", params);
    	return mv;
	}

    /* ********************************************
     * 메뉴
     ******************************************** */
    /* 
     * 사용자 메뉴 조회
     * */
    @ResponseBody
    @GetMapping("/main/userMenu.do")
    public String userMenu() throws JsonProcessingException{
        UserVo userVo = (UserVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ObjectMapper mapper = new ObjectMapper();
    	return mapper.writeValueAsString(userVo.getUserMenuVOList()); 
    }    
    
    /* 
     * 사용자 즐겨찾기 추가/삭제 토글
     *
     */
    @ResponseBody
    @GetMapping("/main/userMenuBookmark.do")
    public ModelAndView userBookmarkToggle(@RequestParam Map<String, Object> params, @AuthenticationPrincipal UserVo userInfo){
    	
    	userService.toggleUserBookmarkMenu(params, userInfo);
        
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("result", "Y");
		return mv;   

    }   
    
    @GetMapping("/main/binblur.do")
    public ModelAndView binBlurList(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVo userInfo){
    	ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", userService.getBinBlurList(param, userInfo));
    	return mv;
    }
 
}
