package com.logistics;

import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.logistics.configuration.session.SessionListener;

@SpringBootApplication
@ServletComponentScan //필터용 추가
public class LogisticsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LogisticsApplication.class);
	}
	
	@Bean
	public HttpSessionListener httpSessionListener(){
		return new SessionListener();
	}	
}
