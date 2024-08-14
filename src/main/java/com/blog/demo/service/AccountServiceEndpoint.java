package com.blog.demo.service;

import org.springframework.stereotype.Service;

import com.blog.demo.webservices.accountservice.Account;
import com.blog.demo.webservices.accountservice.AccountDetailsRequest;
import com.blog.demo.webservices.accountservice.AccountDetailsResponse;
import com.blog.demo.webservices.accountservice.EnumAccountStatus;
import com.blog.demo.webservices.accountservice.ObjectFactory;

@Service
public class AccountServiceEndpoint implements com.briansdevblog.accounts.AccountService {
	
	@Override
	public AccountDetailsResponse getAccountDetails(AccountDetailsRequest parameters) {

		ObjectFactory factory = new ObjectFactory();
		AccountDetailsResponse response = factory.createAccountDetailsResponse();
		
		Account account = factory.createAccount();
		account.setAccountNumber("12345");
		account.setAccountStatus(EnumAccountStatus.ACTIVE);
		account.setAccountName("Joe Bloggs");
		account.setAccountBalance(3400);
		
		response.setAccountDetails(account);		
		return response;
	}
	
}