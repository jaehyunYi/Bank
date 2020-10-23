package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

import com.eomcs.pms.domain.Bank;
import com.eomcs.util.Prompt;

// Command 규칙에 따라 클래스를 정의한다.
public class AccountOpeningCommand implements Command {

  List<Bank> accountList;

  public AccountOpeningCommand(List<Bank> list) {
    accountList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
  	  out.println("   ＿＿＿＿＿＿＿＿＿＿＿＿＿");
  	  out.println("           계좌 등록    ");
  	  out.println("   ￣￣￣￣￣￣￣￣￣￣￣￣￣");

      Bank account = new Bank();
      account.setNo(Prompt.inputInt("번호? ", out, in));
      account.setAno(Prompt.inputString("계좌번호?", out, in));
      account.setName(Prompt.inputString("이름? ", out, in));
      account.setBalance(Prompt.inputInt("잔고? ", out, in));

      accountList.add(account);

      out.println("\t계좌 등록 완료 ");

    } catch(Exception e) {
      out.printf("\t작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }
}
