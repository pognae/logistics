package com.logistics.configuration.filter;

import lombok.extern.log4j.Log4j2;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContextHolder;

import com.logistics.sy.domain.UserRoleProgramDTO;
//import org.springframework.security.core.Authentication;
import com.logistics.sy.domain.UserVo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/*
init()
웹 컨테이너(톰캣)이 시작될 때 필터 최초 한 번 인스턴스 생성

doFilter()
클라이언트의 요청 시 전/후 처리
FilterChain을 통해 전달

public void destroy()
필터 인스턴스가 제거될 때 실행되는 메서드, 종료하는 기능
 */


@Log4j2
@WebFilter(urlPatterns = "/*") 
public class GlobalFilter implements Filter {
	private List<String> excludedPath; //필더에서 (세션 미 채크 대상) 제외 대상
	//private List<String> connectingloggingPath; //접속 이력 로깅 대상 
	
    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	log.info("=========================");
        log.info("필터 인스턴스 초기화 ");
        log.info("=========================");
        String excludePattern = "/,/css,/js,/img,/vender,/upload,/policy,/login,/user_access,/signUp,/accessPolicy,/signUp.do,/manifest.json,/com,/sy,/favicon.ico";
        excludedPath = Arrays.asList(excludePattern.split(","));
    }
    
    
    /*
    - 전/후 처리
    - Request, Response가 필터를 거칠 때 수행되는 메소드
    - chain.doFilter() 기점으로 request, response 나눠집니다.
	*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    HttpSession session = req.getSession();

	    String url = req.getRequestURI(); // 요청 URL 취득
	    String gprogrid = req.getParameter("gprogrid"); // 프로그햄 ID 취득
	    //String warekey = req.getParameter("warekey"); // 접속 창고 :: PT=N/A, 본사일 경우 창고KEY를 N/A 한다.
	     
	    boolean isAuth = true; //프로그램 데이권한이 없을경우 false
		 
		log.info(" getRequestURI:::::::::::::::::: " + url ); 
		 
        // 필터 예외(세션 미 채크 대상) 
        int secondIndex = url.indexOf("/", 1);
        String filterURL;
        if(secondIndex > 0 ){
        	filterURL = url.substring(0, secondIndex); // 모듈/ OR static/
        } else {
        	filterURL = url; // root
        }

	    if(!excludedPath.contains(filterURL))
	    {  
	    	// 세션이 없으면 로그인페이지로 이동
	        if(session != null  ) {
	        	String id = (String)session.getAttribute("useract");
        		if(id == null ) {
        			
        			String ajaxCall = (String) req.getHeader("AJAX");
        		    if( ajaxCall != null  ) { res.sendError(500); }        			
    				res.setContentType("text/html; charset=EUC-KR");
    				PrintWriter out = res.getWriter();
    				out.println("<script type='text/javascript' src='/js/common.js'></script>"); 
    				out.println("<script>");
    				out.println(" var msg='세션이 만료되었거나 회원전용 페이지 입니다. 다시로그인 하세요!  Your session has expired or is a member-only page. Log in again! '; ");
    				//out.println(" sessionTimeOut('"+messageSourceAccessor.getMessage("sy.main.auth.sessionout", Locale.getDefault())+ "');"); //세션 만료되었습니다. 다시 로그인 하세요!
    				out.println(" sessionTimeOut(msg);"); //세션 만료되었습니다. 다시 로그인 하세요!
    				out.println("</script>");
    				
        			return; 
        		} 
	        } 
	        
    		UserVo userVo = null;
    		try {
    			userVo = (UserVo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		} catch (Exception e) {  
    		    String ajaxCall = (String) req.getHeader("AJAX");
    		    if( ajaxCall != null  ) {res.sendError(500);}
				res.setContentType("text/html; charset=EUC-KR");
				PrintWriter out = res.getWriter();
				out.println("<script type='text/javascript' src='/js/common.js'></script>"); 
				out.println("<script>"); 
				out.println(" var msg='사용자정보 오류.   '; ");
				out.println("logout(msg);"); 
				out.println("</script>");
    			return;
			}        		
       
    	    // 권한 채크
    	    // GET=조회 POST=저장 PUT=수정 DELETE=삭제  
    		// gprogrid 없는요청은 무조건 true로 인식한다.(예:메인페이지)
	     	if (gprogrid != null && gprogrid.length() > 1) {
	     		isAuth = false;
	     		UserRoleProgramDTO itemRole = userVo.getUserRoleVOMap().get(gprogrid);
	     		if(itemRole!= null) { 
		            if (itemRole.getProgrid().equals(gprogrid)  ) { 
		            	if(req.getMethod().equals("PUT") && itemRole.getPutactv().equals("Y")  ) {
		            		log.info(" PUT수정권한. ");
		            		isAuth = true; 
		            	} else if(req.getMethod().equals("GET") && itemRole.getGetactv().equals("Y")) {
		            		log.info(" GET조회권한. "); 
		            		isAuth = true; 
		            	} else if(req.getMethod().equals("POST") && itemRole.getPosactv().equals("Y")) {
		            		log.info(" POST생성권한. ");
		            		isAuth = true; 
		            	} else if(req.getMethod().equals("DELETE") &&itemRole.getDelactv().equals("Y")) {
		            		log.info(" DELETE삭제권한. ");
		            		isAuth = true; 
		            	} else {
		            		log.info(" 권한이 없습니다."); 
		            	} 
		            } else {
		            	isAuth = false; 
		            	log.info(" 권한이 데이터 없습니다.");
		            }
	        	
	     		} else {
	     			isAuth = false; 
	     			log.info(" itemRole Null 발생.");
	     		}
	            
		 
	        }
	     	
	        if(!isAuth) {
    		    String ajaxCall = (String) req.getHeader("AJAX");
    		    if( ajaxCall != null  ) {res.sendError(400);}
    		    
				res.setContentType("text/html; charset=EUC-KR");
				PrintWriter out = res.getWriter();
				out.println("<script>"); 
				out.println("window.alert('" + messageSourceAccessor.getMessage("sy.main.auth.nonauth", Locale.getDefault()) + "');");
				out.println("window.history.back();");
				out.println("</script>"); 
	        }
	    } 
	     
        
	    // 접속 로깅
        // 모듈프로그램만 이력 저장
	    if(isAuth) { 
	    	chain.doFilter(request, response);
	    }
	}
	 
    /*
    - 필터 인스턴스 종료
    */
	@Override
	public void destroy() {
		log.info("=========================");
	    log.info(" 필터 인스턴스 종료 ");
	    log.info("=========================");
	}
  
}