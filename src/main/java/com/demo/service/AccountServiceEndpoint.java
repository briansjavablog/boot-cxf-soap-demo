package com.demo.service;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.samples.webservices.accountservice.AccountDetailsRequest;
import com.blog.samples.webservices.accountservice.AccountDetailsResponse;
import com.briansjavablog.accounts.Accounts;

@Service
@WebService(portName = "Accounts", serviceName = "Accounts", 
			endpointInterface = "com.briansjavablog.accounts.Accounts", 
			targetNamespace = "http://www.briansjavablog.com/Accounts/")
public class AccountServiceEndpoint implements Accounts {

	private AccountService accountService;

	@Autowired
	public AccountServiceEndpoint(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public AccountDetailsResponse getAccountDetails(AccountDetailsRequest parameters) {

		return accountService.getAccountDetails(parameters);
	}
}