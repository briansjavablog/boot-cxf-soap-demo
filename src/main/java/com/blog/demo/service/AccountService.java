package com.blog.demo.service;

import com.blog.demo.webservices.accountservice.AccountDetailsRequest;
import com.blog.demo.webservices.accountservice.AccountDetailsResponse;

public interface AccountService {

	AccountDetailsResponse getAccountDetails(AccountDetailsRequest parameters);
}
