package com.icici.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@ManyToOne
    private Account sourceAccount;

    @ManyToOne
    private Account destinationAccount;

    private double amount;
    private LocalDateTime timestamp;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Account getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(Account sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public Account getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(Account destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	 public Transaction() {
	    }

	 public Transaction(Account sourceAccount, Account destinationAccount, double amount, LocalDateTime timestamp) {
	        this.sourceAccount = sourceAccount;
	        this.destinationAccount = destinationAccount;
	        this.amount = amount;
	        this.timestamp = timestamp;
	    }

    // Getters, Setters, Constructors
}
