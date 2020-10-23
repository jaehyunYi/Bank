package com.eomcs.pms.listener;

import java.util.Map;
import com.eomcs.context.ApplicationContextListener;

public class AppInitListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String,Object> context) {
    System.out.println("계좌 관리 시스템을 시작합니다!");
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
    System.out.println("계좌 관리 시스템을 종료합니다!");
  }
}
