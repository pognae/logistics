package com.logistics.configuration.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

//configure.authenticationDetailsSource 추가
@Component 
public class CustomWebAuthenticationDetailsSource
	implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {



   @Override
   public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
	   
	   String compkey = context.getParameter("compkey");
	   String warekey = context.getParameter("warekey");
	   String langkey = context.getParameter("langkey");
       
	   //compkey = context.getSession().setAttribute("compkey");
	   context.getSession().setAttribute("compkey", compkey);
	   context.getSession().setAttribute("warekey", warekey);
	   context.getSession().setAttribute("langkey", langkey);
	   
	   
	   
       return new CustomWebAuthenticationDetails(context);

   }

}
