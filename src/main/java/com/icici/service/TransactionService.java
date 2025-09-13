package com.icici.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icici.model.Account;
import com.icici.model.Transaction;
import com.icici.repository.AccountRepository;
import com.icici.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMessageProducer messageProducer;

    @Transactional
    public Transaction transferFunds(Long sourceAccountId, Long destinationAccountId, double amount) {
        Account sourceAccount = accountRepository.findById(sourceAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account destinationAccount = accountRepository.findById(destinationAccountId)
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        if (sourceAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        Transaction transaction = new Transaction(sourceAccount, destinationAccount, amount, LocalDateTime.now());
        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        // Send a message to RabbitMQ about this transaction
        String transactionMessage = String.format("Transferred %s from %s to %s", amount, sourceAccountId, destinationAccountId);
        messageProducer.sendTransactionMessage(transactionMessage);

        return transactionRepository.save(transaction);
    }
}
