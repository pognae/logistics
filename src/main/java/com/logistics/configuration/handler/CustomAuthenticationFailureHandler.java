package com.logistics.configuration.handler;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.ResponseDataDTO;
import com.logistics.sy.service.UserService;

/**
 * 로그인 실패시 로직
 *
 */ 
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Autowired
    MessageSource messageSource;
	
    @Autowired
    MessageSourceAccessor messageSourceAccessor;
    
    @Autowired 
    private UserService userService;
    
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

    	// 로그인 폼에서 선언한 파라미터 명으로 request 
		String compkey = request.getParameter("compkey"); 
		String warekey = " ";
		String useract = request.getParameter("useract"); 
        String langkey = request.getParameter("langkey");
    	Locale.setDefault(new Locale(langkey));   
		
    	//로그인 이력 저장
		String loginip = request.getHeader("X-Forwarded-For"); //loginip 접속자ip   
		if(loginip == null || loginip.length() == 0 || loginip.toLowerCase().equals("unknown")) 
			loginip = request.getRemoteAddr();
		String secchua = request.getHeader("sec-ch-ua");          //브라우져
		String secchmb = request.getHeader("sec-ch-ua-mobile");   //모바일
		String secchpf = request.getHeader("sec-ch-ua-platform"); //platform OS
		String haccept = request.getHeader("accept"); //accept : http header accept값 
        String navplat = request.getHeader( "User-Agent" ).toUpperCase(); //navplat 접솝브라우져 및 OS 정보
        String navvers = ""; //navvers 접속브라우져    
        if ( navplat.indexOf("TRIDENT") > -1 || navplat.indexOf("MSIE") > -1 ) { //IE
            if ( navplat.indexOf("TRIDENT/7") > -1 ) { navvers = "IE 11";
            } else if ( navplat.indexOf("TRIDENT/6") > -1 ) { navvers = "IE 10";
            } else if ( navplat.indexOf("TRIDENT/5") > -1 ) { navvers = "IE 9";
            } else if ( navplat.indexOf("TRIDENT/4") > -1 ) { navvers = "IE 8";
            } else if ( navplat.indexOf("EDG") > -1 || navplat.indexOf("EDGE") > -1 ) { navvers = "IE Edge"; } 
        } else if ( navplat.indexOf("WHALE") > -1 ) { //네이버 WHALE
        	navvers = "Whale";
        } else if ( navplat.indexOf("OPERA") > -1 || navplat.indexOf("OPR") > -1 ) { //오페라
        	navvers = "Opera";
        } else if ( navplat.indexOf("FIREFOX") > -1 ) { //파이어폭스
        	navvers = "Firefox";
        } else if ( navplat.indexOf("SAFARI") > -1 && navplat.indexOf("CHROME") == -1 ) { //사파리
        	navvers = "Safari";
        } else if ( navplat.indexOf("CHROME") > -1 ) { //크롬
        	navvers = "Chrome";
        } else {
        	navvers = "Other";
        }; 
        String loginsf = "N"; //접속성공실패여부
        userService.insertLoginUser(compkey, useract, warekey, loginip, secchua, secchmb, secchpf, haccept, navplat, navvers, loginsf);
           
    	
		ObjectMapper mapper = new ObjectMapper();	//JSON 변경용
		String exceptionKey="";
    	ResponseDataDTO responseDataDTO = new ResponseDataDTO();
    	
    	responseDataDTO.setCode("999");
    	responseDataDTO.setStatus("ERROR");
		if (exception instanceof AuthenticationServiceException) {
			exceptionKey = "sy.main.login.ExceptionUseract"; // 존재하지 않는 사용자입니다.
		} else if(exception instanceof BadCredentialsException) {
			exceptionKey = "sy.main.login.ExceptionFalse";  //아이디 혹은 비밀번호가 일치하지 않습니다.
		} else if(exception instanceof LockedException) {
			exceptionKey = "sy.main.login.ExceptionLocked";// 잠긴 계정입니다.
		} else if(exception instanceof DisabledException) {
			exceptionKey = "sy.main.login.ExceptionDisabled";// 비활성화된 계정입니다.
		} else if(exception instanceof AccountExpiredException) {
			exceptionKey = "sy.main.login.ExceptionExpired";// 만료된 계정입니다.
		} else if(exception instanceof CredentialsExpiredException) {
			exceptionKey = "sy.main.login.ExceptionlsAuth";// 관리자자에 문의 하세요.
		}		
    	responseDataDTO.setMessagekey(exceptionKey);
    	responseDataDTO.setMessage(messageSourceAccessor.getMessage(exceptionKey, Locale.getDefault()));    	 
     
    	//String msg = messageSource.getMessage("sy.main.login.loginfalse", null,  langkey );
    	
    	response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(mapper.writeValueAsString(responseDataDTO));
        response.getWriter().flush();
	
	}
}