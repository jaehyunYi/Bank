package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import com.eomcs.pms.domain.Bank;

public class DepositListCommand implements Command {

  List<Bank> bankList;

  public DepositListCommand(List<Bank> list) {
    bankList = list;
  }


  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("[입출금 내역]");

    // 전체 목록을 조회할 때 `Iterator` 객체를 사용한다.
    // 만약 목록의 일부만 조회하면다면 인덱스를 직접 다루는 이전 방식을 사용해야 한다.
    Iterator<Bank> iterator = bankList.iterator();

    while (iterator.hasNext()) {
      Bank bank = iterator.next();
      out.printf("%d %s %s %s %d\n",
          bank.getNo(),
    	   bank.getAno(),
    	   bank.getName(),
    	   bank.getRegisteredDate(),
    	   bank.getBalance());
    }
  }

}
