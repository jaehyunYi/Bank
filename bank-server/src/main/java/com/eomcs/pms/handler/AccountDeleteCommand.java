package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

import com.eomcs.pms.domain.Account;
import com.eomcs.util.Prompt;

public class AccountDeleteCommand implements Command {

  List<Account> accountList;

  public AccountDeleteCommand(List<Account> list) {
    this.accountList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[계좌 삭제]");
      int no = Prompt.inputInt("번호? ", out, in);
      int index = indexOf(no);

      if (index == -1) {
        out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ", out, in);
      if (!response.equalsIgnoreCase("y")) {
        out.println("게시글 삭제를 취소하였습니다.");
        return;
      }

      accountList.remove(index);
      out.println("게시글을 삭제하였습니다.");

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }

  private int indexOf(int no) {
    for (int i = 0; i < accountList.size(); i++) {
    	Account account = accountList.get(i);
      if (account.getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
