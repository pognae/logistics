package com.logistics.configuration.security;
 
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.logistics.sy.domain.UserVo;
import com.logistics.sy.service.UserService;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired 
	private UserService userService;
	 
    @Autowired
    MessageSourceAccessor messageSourceAccessor;
	
	/**
	 * 인증 하는 부분
	 */
	@Override
	public UserDetails loadUserByUsername(String useract ) {  
		UserVo userVo = null;
		if(RequestContextHolder.getRequestAttributes()!=null) {
	        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			String compkey = (String)req.getSession().getAttribute("compkey");
			String langkey = (String)req.getSession().getAttribute("langkey");
			 
			//언어설정
			Locale.setDefault(new Locale(langkey));
	   
			
			//사용자 조회
			userVo = userService.getUserInfo(compkey, useract); 
		 
		}
		return userVo; 
	} 
	 
    
}