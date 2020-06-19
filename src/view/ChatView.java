package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ClientNet.SendClass;

public class ChatView extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	public Socket clientSocket;// 접속한 클라이언트.
	public SendClass sc;// 서버에 id와 메세지 소켓을 보내는 역할을 하는 클래스.
	public IdView id;// 
	public Boolean register = false;
	//처음 접속했으면 false id ui에 접속해서 id를 입력, 그 다음에 채팅창에서 메세지를 보내기위해서 true로 설정.
	JLabel label;
	JButton sendBtn;
	JButton exitBtn;
	public JTextField send_txt;
	public JTextArea recv_txt;
	
	JPanel panel1;
	JPanel panel2;
	
	
	public ChatView(Socket clientSocket) {
		
		this.clientSocket = clientSocket;
		// 접속한 클라이언트의 소켓을 저장.
		sc = new SendClass(this);
		// 다른 클라이언트에게 메세지를 보내는 기능을 가진 클래스의 인스턴스 생성.
		new IdView(this,sc);
		// 접속하기 전에 ID를 입력할 ID UI를 불러옴.
		
		// 채팅창 UI
		label = new JLabel("채팅창");
		label.setBackground(new Color(173,255,47));
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		sendBtn = new JButton("보내기");
		sendBtn.addActionListener(this);
		exitBtn = new JButton("나가기");
		exitBtn.addActionListener(this);
		
		send_txt = new JTextField(20);
		recv_txt = new JTextArea(30,30);
		
		JScrollPane sp = new JScrollPane(recv_txt);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(label,BorderLayout.NORTH);
		panel1.add(sp,BorderLayout.CENTER);
		
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.white);
		panel2.add(send_txt);
		panel2.add(sendBtn);
		panel2.add(exitBtn);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(panel1,BorderLayout.CENTER);
		c.add(panel2,BorderLayout.SOUTH);
		
		setBounds(600, 200, 500, 600);
		setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==sendBtn) {
			// 보내기 버튼을 눌렀을 경우.
			if(send_txt.getText().trim().equals("")) return;
			// 공백문자를 보냈는지 확인.
			recv_txt.append("【" + sc.id + "】 " + send_txt.getText()+"\n");
			// 자기자신의 TextArea에 보낸메세지와 함께 id를 붙여서 삽입.
			sc.send_msg();
			// sendClass의 메소드를 통해서 서버에 메세지를 보냄.
			send_txt.setText("");
			// 메세지를 보낸 뒤 입력창을 비움.
		}
		if(obj==exitBtn) {
			System.exit(0);
			// 나가기 버튼을 누르면 종료.
		}		
	}
}
