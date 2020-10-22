package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.eomcs.pms.domain.Bank;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

// Bank랑 연결해야함!
public class DepositCommand implements Command {

	Scanner keyScanner = new Scanner(System.in);
	List<Bank> bankList;
	List<Bank> accountList;

	public DepositCommand(List<Bank> list) {
		bankList = list;
	}

	Scanner scanner = new Scanner(System.in);

	@Override
	public void execute(PrintWriter out, BufferedReader in) {
		try {
			out.println("[1]입금   [2]출금  [3]종료");

			int balance = 0;
			int money;
			int op = Prompt.inputInt("번호 입력 > ", out, in);
			
			switch (op) {
			case 1:
				
				Bank bank = new Bank();
				Bank bank = findByNo(no);
				bank.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
				out.println("입금할 번호 입력");
				if (bank == null) {
			        out.println("해당 번호의 계좌가 없습니다.");
			        return;
			      }
				String newbalance = Prompt.inputString(
				          String.format("입금할 돈(%s)? ", bank.getBalance(), out, in));
				String response = Prompt.inputString("정말 입금하시겠습니까?(y/N) ", out, in);
			      if (!response.equalsIgnoreCase("y")) {
			        out.println("입금을 취소하였습니다.");
			        return;
			      }
			      balance += bank.getMoney();
			      bank.setBalance(balance);
			
			      out.println("입금 되었습니다.");
				out.printf("입금 후 잔액은  %d 원 입니다.\n", balance);

				bankList.add(bank);
				break;

			case 2:
				out.println("[ 출금  ]");
				Bank bankout = new Bank();
				bankout.setMoney(Prompt.inputInt("출금액 >  ", out, in));

				if (balance - bankout.getMoney() < 0) {
					out.print("잔액이 부족합니다.\n");
				} else {
					balance -= bankout.getMoney();
					out.println("출금 완료!");
				}
				out.printf("잔액은  %d 원 입니다.\n", balance);

				bankout.setBalance(balance);
				bankList.add(bankout);
				break;

			default:
				out.println("취소 되었습니다.");
			}

		} catch (Exception e) {
			out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
		}
	}
	private Bank findByNo(int no) {
	    for (int i = 0; i < bankList.size(); i++) {
	      Bank bank = bankList.get(i);
	      if (bank.getNo() == no) {
	        return bank;
	      }
	    }
	    return null;
	  }

}
