package com.microservices.merchantOnboarding.merchantOnboarding.EntityModel;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionTd;

    private String username;
    @NotNull(message = "Please provide transaction amount")
    @DecimalMin("100.00")
    private double transactionAmount;
    
    private Date transactionDate;

    @NotEmpty(message = "Please provide transaction type ")
    private String transactionType;
    private boolean status;

    protected Transaction() {
    }

	public Transaction(Long transactionTd, String username, double transactionAmount, Date transactionDate,
			String transactionType, boolean status) {
		super();
		this.transactionTd = transactionTd;
		this.username = username;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.status = status;
	}

	public Long getTransactionTd() {
		return transactionTd;
	}

	public void setTransactionTd(Long transactionTd) {
		this.transactionTd = transactionTd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


}