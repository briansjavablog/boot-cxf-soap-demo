package com.demo.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.briansjavablog.accounts.Accounts;
import com.demo.service.AccountServiceEndpoint;

@Configuration
@ComponentScan("com.demo.service")
public class TestConfig {

	private static final String SERVICE_URL = "http://localhost:8080/services/accounts";
	
	@Bean("accountServiceClient")
	public Accounts accountServiceClient() {

		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setServiceClass(Accounts.class);
		jaxWsProxyFactoryBean.setAddress(SERVICE_URL);		    
		return (Accounts) jaxWsProxyFactoryBean.create();
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