package main;

import java.net.Socket;
import ClientNet.ClientThread;
import view.ChatView;

public class mainClass {

	public static void main(String[] args) {
		
		try {
			Socket clientSocket = new Socket("127.0.0.1",9000);
			// 소켓을 생성하고 보낼 서버의 ip(지금은 자기자신)와 포트번호를 설정하고 connect를 시도.
			
			/*
			아래의 절차를 위에 코드로 줄임.
			InetSocketAddress sockAddr = new InetSocketAddress("192.168.7.66", 9000);
			Socket socket = new Socket();
			
				socket.connect(sockAddr, 10000);
				
				InetAddress inerAddr;	// 접속 확인용
				if((inerAddr = socket.getInetAddress()) != null) {
					System.out.println("Server connect success : " + inerAddr);
				}else {
					System.out.println("server connect fail.);
				}
			*/
			
			System.out.println("채팅 서버에 접속을 했습니다.");
			
			ChatView cv = new ChatView(clientSocket);
			// 채팅창UI에게 클라이언트소켓정보를 매개변수로 보내고 UI 실행.
			new ClientThread(clientSocket,cv).start();
			// 접속한 클라이언트가 다른 클라이언트의 메세지를 받기위한 스레드를 실행.
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
