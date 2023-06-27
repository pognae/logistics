package com.logistics.configuration.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.project.demo.common.config.ResponseDataCode;
//import com.project.demo.common.config.ResponseDataStatus;
import com.logistics.configuration.ResponseDataDTO;
import com.logistics.sy.domain.UserMenuDTO;
import com.logistics.sy.domain.UserVo;
import com.logistics.sy.service.UserService;

import lombok.extern.log4j.Log4j2;

/**
 * 로그인 성공시 핸들러
 *
 */
@Log4j2
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired 
    private UserService userService;
    
	/**
	 * 로그인이 성공하고나서 로직
	 */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
    	
    	// 로그인 폼에서 선언한 파라미터 명으로 request
        //String compkey = request.getParameter("compkey");
        
        //super.onAuthenticationSuccess(request, response, authentication);
        String mainFragment = "";
    	HttpSession session = request.getSession(false);
    	if(session==null)
            session = request.getSession();
    	
    	UserVo userVo = (UserVo) authentication.getPrincipal();
    	String compkey = userVo.getCompkey();
    	String useract = userVo.getUseract();
    	userVo.setUsertyp(userVo.getUsertyp() != null ? userVo.getUsertyp().trim() : null);
    	userVo.setOwnerky(userVo.getOwnerky() != null ? userVo.getOwnerky().trim() : null);
    	userVo.setPtnrkey(userVo.getPtnrkey() != null ? userVo.getPtnrkey().trim() : null);
    	userVo.setCustkey(userVo.getCustkey() != null ? userVo.getCustkey().trim() : null);
		try {			
			// 유저의 Role 셋팅 
			userVo.setUserRoleVOMap (userService.getUserByRoleMap(compkey, useract)); 
			
			// 유저의 Menu/bookmark 셋팅
			List<UserMenuDTO> userMenusList = userService.getUserByMenu(compkey, useract);
			//userVo.setUserMenuVOList( userService.getUserByMenu(compkey, useract) );
			userVo.setUserMenuVOList( userMenusList );
			userVo.setUserMenubookmarkMap( userService.getUserBookmarkMenu(userMenusList) );
			
			// 유저의 접속가능 창고		
			userVo.setUserWarehousr(userService.getUserByWarehouse(compkey, useract));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	//[Client 사용] 세션 속성 세팅
    	session.setAttribute("compkey", userVo.getCompkey());
    	session.setAttribute("useract", userVo.getUseract());
    	session.setAttribute("usernam", userVo.getUsernam());
    	session.setAttribute("natn2ky", userVo.getNatn2ky());
    	session.setAttribute("themety", userVo.getThemety());
    	session.setAttribute("usertyp", userVo.getUsertyp());
    	session.setAttribute("datefmt", userVo.getDatefmt());
    	session.setAttribute("timefmt", userVo.getTimefmt());
    	session.setAttribute("decifmt", userVo.getDecifmt());
    	session.setAttribute("utimzon", userVo.getUtimzon());
    	session.setAttribute("ownerky", userVo.getOwnerky());
    	session.setAttribute("ptnrkey", userVo.getPtnrkey());
    	session.setAttribute("custkey", userVo.getCustkey());
    	session.setAttribute("rolgkey", userVo.getRolgkey());
    	session.setAttribute("favoryn", userVo.getFavoryn()); // 즐겨찾기메뉴 자동 Open YN
    	session.setAttribute("bookmark", userVo.getUserMenubookmarkMap());
		
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
        String loginsf = "Y"; //접속성공실패여부
        userService.insertLoginUser(compkey, useract, " ", loginip, secchua, secchmb, secchpf, haccept, navplat, navvers, loginsf);
               	
    	
        System.out.println("--- Headers - start ---");
        request.getHeaderNames().asIterator()
        	.forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));
        System.out.println("--- Headers - end ---");

    	ObjectMapper mapper = new ObjectMapper();	//JSON 변경용 
    	ResponseDataDTO responseDataDTO = new ResponseDataDTO();
    	responseDataDTO.setCode("200");
    	responseDataDTO.setStatus("SUCCESS");
    	responseDataDTO.setMessage("성고.");
    	 
        log.info(" ====================="); 
        log.info(" 사용자별 메인프레임 이동   "); 
        log.info(" ==================== "); 
        if (userVo.getThemety().equals("MAIN1SD")) {
        	mainFragment = "main/mainPage1LeftSD.do";
        } else if (userVo.getThemety().equals("MAIN2TAB") ) {
        	mainFragment = "/main/mainPage2LeftTAB.do";
		} else if (userVo.getThemety().equals("MAIN3TAB") ) {
        	mainFragment = "/main/mainPage3TopTAB.do";
        }  else {
        	mainFragment = "/user_access";
        }
        
        
        
        
    	Map<String, String> items = new HashMap<String,String>();	 
    	//items.put("url", "/user_access");	// 로그인 성공 페이지
    	items.put("url", mainFragment);	// 로그인 성공 페이지
    	responseDataDTO.setItem(items);
    	
    	response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(mapper.writeValueAsString(responseDataDTO));
        response.getWriter().flush();
        

        //response.sendRedirect("/user_access");
    }
}
