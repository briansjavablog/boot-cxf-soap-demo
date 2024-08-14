package com.demo.config;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.blog.demo.service.AccountServiceEndpoint;
import com.briansdevblog.accounts.AccountService;

@Configuration
@ComponentScan("com.blog.demo.service")
public class TestConfig {

	private static final String SERVICE_URL = "http://localhost:8080/services/accounts";
	
	@Bean("accountServiceClient")
	public AccountService accountServiceClient() {

		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setServiceClass(AccountService.class);
		jaxWsProxyFactoryBean.setAddress(SERVICE_URL);		    
		return (AccountService) jaxWsProxyFactoryBean.create();
	}
	
	@Bean(name=Bus.DEFAULT_BUS_ID)
	public SpringBus springBus(LoggingFeature loggingFeature) {
		
		SpringBus bus = new  SpringBus();
		bus.getFeatures().add(loggingFeature);
		
		return bus;
	}

	@Bean
	public LoggingFeature loggingFeature() {
		
		LoggingFeature loggingFeature = new LoggingFeature();
		loggingFeature.setPrettyLogging(true);
		
		return loggingFeature;
	}
	
	@Bean
	public Endpoint endpoint(Bus bus, LoggingFeature loggingFeature, AccountServiceEndpoint accountServiceEndpoint) {
		
		EndpointImpl endpoint = new EndpointImpl(bus, accountServiceEndpoint);
		endpoint.publish(SERVICE_URL);
		
		return endpoint;
	}
	
}