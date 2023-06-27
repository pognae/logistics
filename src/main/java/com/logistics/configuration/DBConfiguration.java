package com.logistics.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig  = new HikariConfig();
		
		try { 
			System.out.println("====================================================================================================");
			System.out.println("HikariConfig  DB접속환경 확인 " + hikariConfig.getJdbcUrl()  ); 
			System.out.println("====================================================================================================");
		} catch(Exception e ) {
			System.out.println("====================================================================================================");
			System.out.println("HikariConfig  DB접속환경 확인 " + e.getMessage().toString()); 
			System.out.println("====================================================================================================");
		}
		return hikariConfig;
		//return new HikariConfig();
	}

	@Bean
	public DataSource dataSource() {   
		
		HikariConfig hikariConfig = hikariConfig();

		//================================================
		//DB 커넥션 정보 확인용
		//System.out.println("getDriverClassName => " + hikariConfig.getDriverClassName());
		//System.out.println("getJdbcUrl => " + hikariConfig.getJdbcUrl());
		//System.out.println("getUsername => " + hikariConfig.getUsername());
		//System.out.println("getPassword => " + hikariConfig.getPassword());
		//================================================

		try { 
			DataSource dataSource = new HikariDataSource(hikariConfig);  
			System.out.println("====================================================================================================");
			System.out.println("DataSource connection 성공 " + dataSource.toString()); 
			System.out.println("====================================================================================================");
			return dataSource;
		} catch(Exception e ) {
			System.out.println("====================================================================================================");
			System.out.println("DataSource connection 오류 " + e.getMessage().toString());  
			System.out.println("====================================================================================================");
			return null; 
		}
		
		//return new HikariDataSource(hikariConfig());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		factoryBean.setTypeAliasesPackage("com.logistics");
		
		//Mybatis CashDisable
		mybatisConfg().setLocalCacheScope(LocalCacheScope.STATEMENT);
		mybatisConfg().setCacheEnabled(false);	
		factoryBean.setConfiguration(mybatisConfg());
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfg(){

		return new org.apache.ibatis.session.Configuration();
	}
	
	@Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }


}
