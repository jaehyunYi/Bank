package com.eomcs.pms.domain;

import java.sql.Date;

public class Bank {
	private Date registeredDate;
	private String status;
	private int money;	// 거래금액
	private int balance;

	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String staus) {
		status = staus;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}



}
