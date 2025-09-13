package com.icici.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icici.model.Account;
import com.icici.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

public  Account deleteAccountById(Long id) {
	Account acc=accountRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Account not found") );		
	 accountRepository.deleteById(id);
	 return acc;
	}}
