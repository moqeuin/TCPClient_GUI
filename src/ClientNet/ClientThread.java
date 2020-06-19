package ClientNet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import view.ChatView;

public class ClientThread extends Thread{
	
	Socket clientSocket;
	ChatView chat;
	
	public ClientThread(Socket clientSocket, ChatView chat) {
		this.clientSocket = clientSocket;
		this.chat = chat;	
	}
	@Override
	public void run() {
		try {
			while(true) {
				BufferedReader br = new BufferedReader
								   (new InputStreamReader(clientSocket.getInputStream()));
				String msg = br.readLine();
				// 서버에서 소켓을 전송을 한다면 스레드는 실행하고 있다가 소켓의 메세지를 msg에 저장. 
				chat.recv_txt.append(msg+"\n");
				// 그리고 chatview의 textarea에 문자열을 저장.
				Thread.sleep(300);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
