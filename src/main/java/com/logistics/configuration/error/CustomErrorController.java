package com.logistics.configuration.error;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class CustomErrorController implements ErrorController { 
	private String VIEW_PATH = "/com/error/";
	
	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request, Model model) { 
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
		if(status != null){ 
			int statusCode = Integer.valueOf(status.toString()); 
			
	        model.addAttribute("code", status.toString());
	        model.addAttribute("msg", httpStatus.getReasonPhrase());
	        model.addAttribute("timestamp", new Date());
	        model.addAttribute("QueryString", request.getQueryString());
	        model.addAttribute("ParameterMap", request.getParameterMap());
	        model.addAttribute("referer", request.getHeader("Referer")); 
	         
	        		
			if(statusCode == HttpStatus.FORBIDDEN.value()){ //Forbidden
				return VIEW_PATH + "403"; //접근 거부 오류 
			} 
			
			if(statusCode == HttpStatus.NOT_FOUND.value()){ 
				return VIEW_PATH + "404"; //해당 리소스를 찾을 수 없음
			}
			
			if(statusCode == HttpStatus.REQUEST_TIMEOUT.value()){ 
				return VIEW_PATH + "408"; //요청 시간 초과 오류
			}
			
			if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
				return VIEW_PATH + "500"; //서버 오류 
			} 
		} 
		return "error";
	}
	 
	
	
	//@Override 
	public String getErrorPath() {
		return null;
		} 
	}


 