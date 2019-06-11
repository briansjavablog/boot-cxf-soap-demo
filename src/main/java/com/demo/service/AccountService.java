package com.demo.service;

import com.blog.samples.webservices.accountservice.AccountDetailsRequest;
import com.blog.samples.webservices.accountservice.AccountDetailsResponse;

public interface AccountService {

	AccountDetailsResponse getAccountDetails(AccountDetailsRequest parameters);
}
