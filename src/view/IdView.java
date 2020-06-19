package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ClientNet.SendClass;

public class IdView extends JFrame implements ActionListener{	
	private static final long serialVersionUID = 6411930086291829122L;
	
	ChatView chat; // 입력이 끝나면 채팅창으로 전환해주기위한 변수.
	SendClass sc;
	
	public static JTextField Id;
	JLabel label;
	JButton insertBtn;
	
	public IdView(ChatView chat,SendClass sc) {
		
		this.chat = chat;
		this.sc = sc;
				
		label = new JLabel("아이디 입력 : ");
		Id = new JTextField(10);
		insertBtn = new JButton("로그인");
		insertBtn.addActionListener(this);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(label);
		c.add(Id);
		c.add(insertBtn);
		
		setBounds(800,300,290,100);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == insertBtn) {
			// 로그인 버튼을 눌렀을 경우.
			sc.send_msg();
			// send클래스를 통해서 id를 저장하고 서버에게 id정보를 보냄.
			chat.register = true;
			// send클래스에서 메세지를 보내기위해서 true 변경.
			chat.setVisible(true);
			this.dispose();
			// id UI의 창을 종료.
		}		
	}
}
