package com.eomcs.pms.listener;

import java.util.List;
import java.util.Map;

import com.eomcs.context.ApplicationContextListener;
import com.eomcs.pms.domain.Bank;
import com.eomcs.pms.handler.AccountDeleteCommand;
import com.eomcs.pms.handler.AccountListCommand;
import com.eomcs.pms.handler.AccountOpeningCommand;
import com.eomcs.pms.handler.BankDepositcommand;
import com.eomcs.pms.handler.BankWithDrawcommand;

// 클라이언트 요청을 처리할 커맨드 객체를 준비한다.
public class RequestMappingListener implements ApplicationContextListener {

  @SuppressWarnings("unchecked")
  @Override
  public void contextInitialized(Map<String,Object> context) {
    // 옵저버가 작업한 결과를 맵에서 꺼낸다.

    List<Bank> bankList = (List<Bank>) context.get("bankList");

    context.put("1",new AccountOpeningCommand(bankList)); // 계좌개설
    context.put("2", new AccountListCommand(bankList));
    context.put("3", new AccountDeleteCommand(bankList));
    // context.put("/bank/list", new DepositListCommand(bankList));
    context.put("4",new BankDepositcommand(bankList));
    context.put("5",new BankWithDrawcommand(bankList));
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
  }
}
