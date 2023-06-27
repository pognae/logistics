package com.logistics.configuration;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
public class TransactionConfig {

	private static final int TX_METHOD_TIMEOUT = -1; // 초 단위 테스트용 : 타임아웃 120초, 운영용 : 20초로 바꿔야함.
	private static final String TRANSACTION_POINTCUT = "execution(* com.logistics..*Service..*(..))";

	@Autowired
	private DBConfiguration dbConfiguration;

	@Bean
	public PlatformTransactionManager txMariaManager() {
		return new DataSourceTransactionManager(dbConfiguration.dataSource());
	}

	@Bean
	public TransactionInterceptor txMariaInterceptor() {
		TransactionInterceptor txInterceptor = new TransactionInterceptor();
		Properties txAttributes = new Properties();
		List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();
		rollbackRules.add(new RollbackRuleAttribute(Exception.class));
		DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED);
		readOnlyAttribute.setReadOnly(true);
		readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);
		RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
		writeAttribute.setTimeout(TX_METHOD_TIMEOUT);
		String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
		String writeTransactionAttributesDefinition = writeAttribute.toString();
		txAttributes.setProperty("select*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("get*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("search*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("find*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("count*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("cnt*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("*", writeTransactionAttributesDefinition);
		txInterceptor.setTransactionAttributes(txAttributes);
		txInterceptor.setTransactionManager(txMariaManager());
		return txInterceptor;
	}

	@Bean
	public Advisor txAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(TRANSACTION_POINTCUT);
		return new DefaultPointcutAdvisor(pointcut, txMariaInterceptor());
	}

}
