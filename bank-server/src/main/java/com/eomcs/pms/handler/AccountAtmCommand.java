package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

import com.eomcs.pms.domain.Account;
import com.eomcs.util.Prompt;

// Command 규칙에 따라 클래스를 정의한다.
public class AccountAtmCommand implements Command {
	
	List<Account> accountList;
	
	String accountId;
	  long balance;
	  
	  public AccountAtmCommand(List<Account> list) {
		    this.accountList = list;
		  }
	  @Override
	  public void execute(PrintWriter out, BufferedReader in) {
	    try {
	    	synchronized public long withdraw(long money) {

	    	    // 1) 이 메서드처럼 여러 스레드가 같은 메모리(balance 필드)의 값을 동시에 변경할 때
	    	    //    문제가 발생하는 코드를 "크리티컬 섹션(임계영역; critical section) 또는 크리티컬 리전(critical region)"
	    	    //    이라 부른다.
	    	    // 2) 크리티컬 섹션에 동시에 접근하지 못하게 하는 기법을
	    	    //    "뮤텍스(mutex)" 또는 "세마포어(1)(semaphore)"라 부른다.
	    	    // 3) 자바에서 뮤텍스를 구현하는 방법
	    	    //    => 크리티컬 섹션에 해당하는 메서드나 코드 블록에 sychronized 키워드를 붙여
	    	    //       한 번에 한 스레드만 진입할 수 있도록 lock을 건다.
	    	    //
	    	    long b = this.balance;

	    	    delay();  // CPU를 뺏길 기회를 제공

	    	    b -= money;

	    	    delay();  // CPU를 뺏길 기회를 제공

	    	    if (b < 0)
	    	      return 0;

	    	    delay();  // CPU를 뺏길 기회를 제공

	    	    this.balance = b;

	    	    delay();  // CPU를 뺏길 기회를 제공

	    	    return money;
	    	  }

	    	  
	    	}
	    }
	  private void delay() {
  	    int delayCount = (int)(Math.random() * 1000);
  	    for (int i = 0; i < delayCount; i++)
  	      Math.asin(45.765); // CPU를 뺏길 기회를 제공
  	  }
}
	 