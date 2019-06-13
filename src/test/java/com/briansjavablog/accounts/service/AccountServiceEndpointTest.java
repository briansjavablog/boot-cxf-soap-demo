package com.briansjavablog.accounts.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.demo.webservices.accountservice.AccountDetailsRequest;
import com.blog.demo.webservices.accountservice.AccountDetailsResponse;
import com.blog.demo.webservices.accountservice.EnumAccountStatus;
import com.blog.demo.webservices.accountservice.ObjectFactory;
import com.briansdevblog.accounts.AccountService;
import com.demo.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class AccountServiceEndpointTest {
	
	@Autowired
	@Qualifier("accountServiceClient")
	private AccountService accountsServiceClient;
	private AccountDetailsRequest accountDetailsRequest;
	
	@Before
	public void setUp() throws Exception {
		
		ObjectFactory objectFactory = new ObjectFactory();
		accountDetailsRequest = objectFactory.createAccountDetailsRequest();
		accountDetailsRequest.setAccountNumber("12345");
	}

	@Test
	public void testGetAccountDetails() throws Exception {
		
		AccountDetailsResponse response = accountsServiceClient.getAccountDetails(accountDetailsRequest);
		assertTrue(response.getAccountDetails()!= null);
		assertTrue(response.getAccountDetails().getAccountNumber().equals("12345"));
		assertTrue(response.getAccountDetails().getAccountName().equals("Joe Bloggs"));
		assertTrue(response.getAccountDetails().getAccountBalance() == 3400);
		assertTrue(response.getAccountDetails().getAccountStatus().equals(EnumAccountStatus.ACTIVE));
	}

}