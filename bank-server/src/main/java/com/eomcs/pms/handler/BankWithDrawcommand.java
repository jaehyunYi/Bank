package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import com.eomcs.pms.domain.Bank;
import com.eomcs.util.Prompt;

public class BankWithDrawcommand implements Command {

  List<Bank> bankList;

  public BankWithDrawcommand(List<Bank> list) {
    bankList = list;
  }
  Scanner scanner = new Scanner(System.in);
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
    	out.println("   ＿＿＿＿＿＿＿＿＿＿＿＿＿");
    	  out.println("             출금    ");
    	  out.println("   ￣￣￣￣￣￣￣￣￣￣￣￣￣");
      int no = Prompt.inputInt("출금할 번호? ", out, in);
      Bank bank = findByNo(no);

      if (bank == null) {
        out.println("\t해당 번호의 계좌가 존재 하지 않습니다.");
        return;
      }

      int balance = Prompt.inputInt(
          String.format("출금할 돈(%s)? ", bank.getBalance()), out, in);
      

      String response = Prompt.inputString("출금 하시겠습니까?(Y/N) ", out, in);
      if (!response.equalsIgnoreCase("y")) {
        out.println("\t출금 취소");
        return;
      }
      int oldmoney;

     oldmoney = bank.getBalance();

     //+=
     bank.setBalance(oldmoney-balance);

     
      out.println("\t출금 완료");
      out.printf("\t출금 후 잔액은  %d 원 입니다.\n", bank.getBalance());


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
