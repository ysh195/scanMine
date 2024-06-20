package scan_mine;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Screen {
	
	JFrame frame = new JFrame();
	Container contentPane = frame.getContentPane(); // 컨텐트팬과 연결
	JLabel textbox = new JLabel("");
	
	private static Screen screen = new Screen(); // 싱글톤
	public static Screen getInstance() {return screen;} // 싱글톤
	
	private Screen() {
	
	frame.setTitle("지뢰 찾기 만들기"); // 제목
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.setLocationRelativeTo(null);	
	frame.setSize(1000,600); // 화면 사이즈
	
	Font font = new Font("Serif",Font.BOLD,20);

	contentPane.setLayout(null); // 레이아웃 설정을 사용자에게 맡기는 기능 추가
	
	textbox.setFont(font);
	textbox.setBounds(400, 450, 100, 100);
	contentPane.add(textbox);
	
	frame.setVisible(false);
	}
	
	void setVisible() {
		frame.setVisible(true);
	}
	
}

