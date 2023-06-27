package com.logistics.configuration.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;



//configure.authenticationDetailsSource 추가
//public class CustomWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
//    @Override
//    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
//        return new CustomWebAuthenticationDetails(context);
//    }
//}


public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

	   private static final long serialVersionUID = -3984468376168493070L;

	   private final String compkey;
	   private final String warekey;
	   private final String langkey;



	   public CustomWebAuthenticationDetails(HttpServletRequest request) {
	       super(request);
	       // 로그인 폼에서 선언한 파라미터 명으로 request
	       compkey = request.getParameter("compkey");
	       warekey = request.getParameter("warekey");
	       langkey = request.getParameter("langkey");
	       
	       // 세션에 넣고 이후에는 세션 참조
	       //request.getSession().setAttribute("compkey", compkey);
	       //return new CustomWebAuthenticationDetails(request);;
	   }



	   public String getCompkey() {
	       return compkey;
	   }

	   public String getWarekey() {
	       return warekey;
	   }

	   public String getLangkey() {
	       return langkey;
	   }

	}