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
import com.eomcs.pms.handler.DepositListCommand;

// 클라이언트 요청을 처리할 커맨드 객체를 준비한다.
public class RequestMappingListener implements ApplicationContextListener {

  @SuppressWarnings("unchecked")
  @Override
  public void contextInitialized(Map<String,Object> context) {
    // 옵저버가 작업한 결과를 맵에서 꺼낸다.

    List<Bank> bankList = (List<Bank>) context.get("bankList");

    context.put("/account/opening",new AccountOpeningCommand(bankList)); // 계좌개설
    context.put("/account/list", new AccountListCommand(bankList));
    context.put("/account/delete", new AccountDeleteCommand(bankList));
    context.put("/bank/list", new DepositListCommand(bankList));
    context.put("/bank/deposit",new BankDepositcommand(bankList));
    context.put("/bank/withdraw",new BankWithDrawcommand(bankList));
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
  }
}
