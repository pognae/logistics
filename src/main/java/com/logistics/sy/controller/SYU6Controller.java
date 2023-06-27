package com.logistics.sy.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.sy.domain.UserVo;
import com.logistics.sy.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SYU6Controller {
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/sy/userProfile.do")
	public ModelAndView userProfilePopup(@AuthenticationPrincipal UserVo userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
    	mv.addObject("list", userService.selectUserList(userInfo));
    	return mv;
	}
	
	@PostMapping("/sy/findPwd.do")
	@ResponseBody
	public boolean autheticateByUserPwd(@RequestBody String passwrd, @AuthenticationPrincipal UserVo userInfo, Authentication authentication) throws Exception{
		String userpasswrd = userService.autheticateByUserPwd(passwrd, userInfo);   
		if(!passwordEncoder.matches(passwrd, userpasswrd)){
             throw new Exception();
         };
         
    	return true;
	}
	
	@PostMapping("/sy/saveUserProfile.do")
	@ResponseBody
	public ModelAndView userProfile(@RequestBody Map<String,Object> param , @AuthenticationPrincipal UserVo userInfo, HttpServletRequest req){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("result", userService.saveUserProfile(param , userInfo, req));
     	return mv;
	}
	
	@PostMapping("/sy/changePwd.do")
	@ResponseBody
	public ModelAndView changePwd(@RequestBody Map<String,Object> param , @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", userService.changeUserPwd(param , userInfo));
		return mv;
	}
	
	@PostMapping("/sy/signOutUser.do")
	@ResponseBody
	public ModelAndView signOut(@RequestBody Map<String,Object> param , @AuthenticationPrincipal UserVo userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", userService.signOutUser(param , userInfo));
		return mv;
	}
}
