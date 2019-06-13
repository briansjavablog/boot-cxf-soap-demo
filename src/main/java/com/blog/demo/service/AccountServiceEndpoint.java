package com.blog.demo.service;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.blog.demo.webservices.accountservice.AccountDetailsRequest;
import com.blog.demo.webservices.accountservice.AccountDetailsResponse;

@Service
@WebService(portName = "Accounts", serviceName = "Accounts", 
			endpointInterface = "com.briansdevblog.accounts.AccountService", 
			targetNamespace = "http://www.briansjavablog.com/AccountService/")
public class AccountServiceEndpoint implements AccountService {

	private AccountService accountService;

	@Autowired	
	public AccountServiceEndpoint(@Qualifier("accountServiceImpl") AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public AccountDetailsResponse getAccountDetails(AccountDetailsRequest parameters) {

		return accountService.getAccountDetails(parameters);
	}
}