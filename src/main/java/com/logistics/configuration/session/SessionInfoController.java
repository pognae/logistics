package com.logistics.configuration.session;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

//사용안함
@Log4j2
@RestController
public class SessionInfoController {
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null){
        	log.info("=========================");
        	log.info("세션이 없음");
        	log.info("=========================");	
            return "세션이 없습니다.";
        }

        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}",name, session.getAttribute(name) ));
 
	    log.info("=========================");	    
        log.info("sessionId={}", session.getId());
        log.info("maxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessTjme={}",new Date(session.getLastAccessedTime()));
        log.info("isNew={}", session.isNew());
        log.info("=========================");
        return "세션 출력";
    }
}