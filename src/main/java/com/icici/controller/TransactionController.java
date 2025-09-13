package com.icici.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import com.icici.model.Transaction;
import com.icici.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transferFunds(@RequestParam Long sourceAccountId, 
                                                     @RequestParam Long destinationAccountId, 
                                                     @RequestParam double amount) {
        Transaction transaction = transactionService.transferFunds(sourceAccountId, destinationAccountId, amount);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
