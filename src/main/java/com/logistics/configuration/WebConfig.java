package com.logistics.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    
//    /**
//     * 인터셉터 등록				---->> 따로 인터셉터 클래스 만들어서 관리할것!
//     * LocaleChangeInterceptor 를 스프링 컨테이너에 등록한다.
//     * WebMvcConfigurer 를 상속받고 addInterceptors를 오버라이딩 한다.
//     * */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
    
    
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/md/mdo3").setViewName("/md/mdo3");
//    }
    //
	
	@Value("${uploadfile.path.resource}")
	private String uploadfileResourcepath;
	
	@Value("${uploadfile.path.url}")
	private String uploadfileUrlPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(uploadfileUrlPath)
				.addResourceLocations(uploadfileResourcepath);
	}
}
