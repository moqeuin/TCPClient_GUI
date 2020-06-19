package ClientNet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import view.ChatView;
import view.IdView;

public class SendClass {
	
	Socket clientSocket;
	ChatView chat;
	public String id;// id View에서 받은 데이터를 저장하고 있다가 chatview와 server에게 보냄.
	
	public SendClass(ChatView chat) {
		this.clientSocket = chat.clientSocket;
		this.chat = chat;
	}
	
	public void send_msg() {
				
		try {
			PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
			String msg;
			if(!chat.register) {
				// 처음 접속을 했다면 false, 자신의 id를 작성하는 작업 절차를 밞음.
				InetAddress iaddr = clientSocket.getLocalAddress();
				String ip = iaddr.getHostAddress();
				// 접속한 클라이언트의 ip.
				id = IdView.Id.getText();
				// id view에서 입력한 텍스트를 저장.
				
				msg = "【" + id +  "】"+"님이 로그인을 하셨습니다.";
				// 서버에게 보낼 메세지.
				chat.setTitle("IP주소 : " + ip +"   " + " ID : " + id);
				// chatview의 상단 윈도우의 제목 텍스트 작성.				
			}		
			else {			
			msg ="【" + id +  "】 " + chat.send_txt.getText();
			// register가 true이면 chatview의 textfield에서 작성한 내용 저장.
			}
			pw.println(msg);
			// 서버에게 메세지 전송.
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}	
}
