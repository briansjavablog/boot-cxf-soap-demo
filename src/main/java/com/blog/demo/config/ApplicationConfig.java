package com.blog.demo.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.blog.demo.service.AccountServiceEndpoint;

@Configuration
public class ApplicationConfig {

	@Bean
	public ServletRegistrationBean<CXFServlet> dispatcherServlet() {
		return new ServletRegistrationBean<CXFServlet>(new CXFServlet(), "/soap-api/*");
	}
	
	@Bean
	@Primary
	public DispatcherServletPath dispatcherServletPathProvider() {
	    return () -> "";
	}

	@Bean(name=Bus.DEFAULT_BUS_ID)
	public SpringBus springBus(LoggingFeature loggingFeature) {
		
		SpringBus cxfBus = new  SpringBus();
		cxfBus.getFeatures().add(loggingFeature);
		
		return cxfBus;
	}

	@Bean
	public LoggingFeature loggingFeature() {
		
		LoggingFeature loggingFeature = new LoggingFeature();
		loggingFeature.setPrettyLogging(true);
		
		return loggingFeature;
	}

	@Bean
	public Endpoint endpoint(Bus bus, AccountServiceEndpoint accountServiceEndpoint) {

		EndpointImpl endpoint = new EndpointImpl(bus, accountServiceEndpoint);
		endpoint.publish("/service/accounts");
		
		return endpoint;
	}

}