package com.logistics.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

//import java.util.Locale;

@Configuration
public class I18nConfig implements WebMvcConfigurer  {

	@Value("${message.path}")
	private String messagePath;
    /**
     * 변경된 언어 정보를 기억할 로케일 리졸버를 생성한다. 스프링이 클라이언트의 언어,국가 정보를 인식하게 하는 클래스
	 * AcceptHeaderLocaleResolver : Http 헤더의 Accept-Language의 값을 사용한다. (기본값)
	 * CookieLocaleResolver : 쿠키의 값을 저장하여 사용한다.
	 * SessionLocaleResolver : 세션에 값을 저장하여 사용한다.
	 * FixedLocaleResolver : 요청과 관계없이 default locale 사용한다
     * @return
     */ 
    //@Bean // 세션 방식을 사용한다.
    //public SessionLocaleResolver localeResolver() {
    //    return new SessionLocaleResolver();
   // } 
    
	@Bean // 쿠키방식 
	public LocaleResolver localeResolver() {

      CookieLocaleResolver resolver = new CookieLocaleResolver();
      resolver.setDefaultLocale(Locale.getDefault());
      //resolver.setDefaultLocale(Locale.KOREA);
      resolver.setCookieName("langkey");
      return resolver;
	} 
 

    /**
     * Locale 값이 변경되면 인터셉터가 동작한다.(언어 변경을 위한 인터셉터를 생성한다.)
     * url의 query parameter에 지정한 값이 들어올 때 동작한다.
     * ex) http://localhost:8080?lang=ko
     * */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("langkey");
        return interceptor;
    } 
 
 
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        System.out.println("====================================================================================================");
        System.out.println("메세지 프로퍼티파일의 위치 " + messagePath);
        System.out.println("====================================================================================================");
        source.setBasename(messagePath);// 메세지 프로퍼티파일의 위치와 이름을 지정한다.
        source.setDefaultEncoding("UTF-8");// 기본 인코딩을 지정한다. 
        source.setCacheSeconds(60); // 프로퍼티 파일의 변경을 감지할 시간 간격을 지정한다. 리로드 시간 
        source.setUseCodeAsDefaultMessage(true); // 없는 메세지일 경우 예외를 발생시키는 대신 코드를 기본 메세지로 한다.
        return source;
    }
    
    
    /**
     * 메세지 엑세서 MessageSource 사용해도 되는데, 메소드가 더 많음 
     * @return
     */
    @Bean 
    public MessageSourceAccessor getMessageSourceAccessor(){
    	return new MessageSourceAccessor(messageSource());
    }
    
    /**
     * 인터셉터 등록
     * LocaleChangeInterceptor 를 스프링 컨테이너에 등록한다.
     * WebMvcConfigurer 를 상속받고 addInterceptors를 오버라이딩 한다.
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }    
}