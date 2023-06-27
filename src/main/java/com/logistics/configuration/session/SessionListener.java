package com.logistics.configuration.session;

 
//import org.slf4j.Logger;

//import org.slf4j.LoggerFactory; 

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.extern.log4j.Log4j2;

//사용안함
@Log4j2
public class SessionListener implements HttpSessionListener {
	private int userCount;
   // private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override 
    public void sessionCreated(HttpSessionEvent se) {
    	++userCount;
        se.getSession().setMaxInactiveInterval(60*60*6); //timeout 세션만료 : 60=60초, 60*1=1분, 60*30=30분, 60*60*6=6시간
        
    	log.info("=========================");
    	log.info(" Session 생성 : 사용자수= ", userCount);
    	log.info("=========================");	
    }

 

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	--userCount;
    	log.info("=========================");
    	log.info(" Session 제거 : 사용자수=", userCount);
    	log.info("=========================");	        
    }

}