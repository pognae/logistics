package com.logistics.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.logistics.configuration.handler.CustomAuthenticationFailureHandler;
import com.logistics.configuration.handler.CustomAuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor; 

@Configuration
@EnableWebSecurity        //spring security 를 적용한다는 Annotation
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter는 Spring Security의 설정파일로서의 역할을 하기 위해 상속해야 하는 클래스입니다.    
    @Autowired
    private final CustomUserDetailService customUserDetailService;
    
    /**
     * WebSecurityConfigurerAdapter를 상속받으면 오버라이드할 수 있습니다. 인증을 무시할 경로들을 설정해놓을 수 있습니다.
     * @param WebSecurity web 
     */
    @Override
    public void configure(WebSecurity web) {
    	// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
    	web.ignoring().antMatchers("/css/**", "/js/**", "/img/**" , "/vender/**","/sy/findId.do");
    }
    
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	//configure.authenticationDetailsSource 추가
	@Autowired
	private CustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource;
	
    /**
     * WebSecurityConfigurerAdapter를 상속받으면 오버라이드할 수 있습니다.
     * @param http
     * @throws Exception
     */ 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers( "/login", "/policy/**","/singUp", "/index.html", "/access_denied", "/resources/**", "/upload/**", "/sy/findId.do").permitAll() // 로그인 권한은 누구나, resources파일도 모든권한 
                .antMatchers("/userAccess").hasRole("USER") //USER롤을 가진 사용자만 /userAccess 접속가능
                .antMatchers("/userAccess").hasRole("ADMIN") //ADMIN롤을 가진 사용자만 /userAccess 접속가능
                .and()
            .formLogin() // 로그인에 관한 설정
                .loginPage("/login")
                .authenticationDetailsSource(customWebAuthenticationDetailsSource)
				.successHandler(customAuthenticationSuccessHandler)
				.failureHandler(customAuthenticationFailureHandler)
				.usernameParameter("useract")
				.passwordParameter("passwrd") 
                .loginProcessingUrl("/login_proc")// 로그인form의  action과 일치시켜주어야 함.
                //.defaultSuccessUrl("/user_access") // 로그인 성공 시 이동할 경로.==> ajax로그인 시 주석
               // .successForwardUrl("/user_access")
                //.failureUrl("/access_denied") // 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸다. .==> ajax로그인 시 주석
                .and()
            .logout() // 로그아웃에 관한 설정
            	.permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)	// 세션 초기화
            	.deleteCookies("JSESSIONID")	// 쿠키 삭제
                .and()
            .csrf().disable();		//(Cross-Site Request Forgery) 공격을 방지 ... 로그인 창
    }

    /**
     * 로그인 인증 처리 메소드
     * 로그인할 때 필요한 정보를 가져오는 곳
     * @param auth
     * @throws Exception
     */
    //@Override 
    //public void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    //}
    
    /**
    * 비밀번호 암호화 관련 설정
    */
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

    
    
    /**
    * 권한 인증 받기(로그인)
    */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    		.userDetailsService(customUserDetailService) 
    		.passwordEncoder(passwordEncoder());
    }
    
}