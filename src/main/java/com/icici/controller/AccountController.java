package com.icici.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.icici.model.Account;
import com.icici.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Create a new account
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    // Get account by ID
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
      @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id){
    	Account acc=accountService.deleteAccountById(id);
    	return new ResponseEntity<>("account deleted successfully"+ acc, HttpStatus.OK);
    }
    
    
}
