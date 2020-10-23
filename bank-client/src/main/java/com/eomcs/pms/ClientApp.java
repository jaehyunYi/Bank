package com.eomcs.pms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.eomcs.util.Prompt;

public class ClientApp {

  static String host;
  static int port;

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("프로그램 사용법:");
      System.out.println("  java -cp ... ClientApp 서버주소 포트번호");
      System.exit(0);
    }


    System.out.println("  _    ______  _____  _____   ______   ___   _   _  _   __    _  ");
    try { Thread.sleep(400); } catch (InterruptedException e) {}
    System.out.println(" | |   | ___ \\|_   _||_   _|  | ___ \\ / _ \\ | \\ | || | / /   | | ");
    try { Thread.sleep(400); } catch (InterruptedException e) {}
    System.out.println("/ __)  | |_/ /  | |    | |    | |_/ // /_\\ \\|  \\| || |/ /   / __)");
    try { Thread.sleep(400); } catch (InterruptedException e) {}
    System.out.println("\\__ \\  | ___ \\  | |    | |    | ___ \\|  _  || . ` ||    \\   \\__ \\");
    try { Thread.sleep(400); } catch (InterruptedException e) {}
    System.out.println("(   /  | |_/ / _| |_   | |    | |_/ /| | | || |\\  || |\\  \\  (   /");
    try { Thread.sleep(400); } catch (InterruptedException e) {}
    System.out.println(" |_|   \\____/  \\___/   \\_/    \\____/ \\_| |_/\\_| \\_/\\_| \\_/   |_| ");
    port = Integer.parseInt(args[1]);

    while (true) {
    	try { Thread.sleep(200); } catch (InterruptedException e) {}
    	System.out.println();
    	System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
    	System.out.println("▒▒                                                                  ▒▒");
    	System.out.println("▒▒   [1] 계좌 개설          [2] 계좌 목록           [3] 계좌 삭제   ▒▒");
    	System.out.println("▒▒                                                                  ▒▒");
    	System.out.println("▒▒   [4] 입금               [5] 출금                [exit] 종료     ▒▒");
    	System.out.println("▒▒                                                                  ▒▒");
    	System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
    	System.out.println();

      String input = Prompt.inputString("명령 입력> ");
    	host = args[0];
      if (input.equalsIgnoreCase("quit"))
        break;

      request(input);

      if (input.equalsIgnoreCase("exit"))
        break;
     System.out.println();
    }
    System.out.println("안녕!");
    System.out.println();

  }

  private static void request(String message) {
    // 클라이언트가 서버에 stop 명령을 보내면 다음 변수를 true로 변경한다.
    boolean exit = false;

    try (Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

      out.println(message);
      out.flush();

      receiveResponse(out, in);

      if (message.equalsIgnoreCase("exit")) {
        exit = true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (exit) {
      // 서버를 멈추기 위해 그냥 접속했다가 끊는다.
      try (Socket socket = new Socket(host, port)) {
        // 아무것도 안한다.
        // 서버가 stop 할 기회를 주기 위함이다.
      } catch (Exception e) {
        // 아무것도 안한다.
      }
    }
  }

  private static void receiveResponse(PrintWriter out, BufferedReader in) throws Exception {
    while (true) {
      String response = in.readLine();
      if (response.length() == 0) {
        break;
      } else if (response.equals("!{}!")) {
        // 사용자로부터 값을 입력을 받아서 서버에 보낸다.
        out.println(Prompt.inputString(""));
        out.flush(); // 주의! 출력하면 버퍼에 쌓인다. 서버로 보내고 싶다면 flush()를 호출하라!
      } else {
        System.out.println(response);
      }
    }
  }
}