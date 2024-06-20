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

public class Buttons extends JButton{ // 이 클래스 자체로 하나의 버튼으로 기능하면서도 여러 속성과 기능을 담을 수 있도록 JButton을 계승하였습니다.
	
	private int x, y; // 이 버튼의 좌표입니다. 다른 클래스 내에 public static으로 설정된 scanned(스캔 여부)와 mine(지뢰인지 여부) 등을 찾을 때 사용됩니다.
	
	public Buttons(int x, int y) {
		this.x=x;
		this.y=y;
		set_buttons(this.x, this.y);
	}
	
	void set_buttons(int x, int y){ // 버튼에 대한 세부 설정 메서드입니다. 그냥 생성자로 만들면 이벤트리스너 안에 this.x 등으로 입력할 수 없어 분리했습니다.

		Screen onlyScreen = Screen.getInstance(); // 스크린이 될 프레임입니다. 하나의 스크린에 다수의 버튼을 연결할 수 있도록 싱글톤으로 설정했습니다.
		setBounds((x+1)*50, (y+1)*50, 50, 50); // 각 버튼의 좌표 지정
		onlyScreen.contentPane.add(this); // 스크린에 연결

		///////////////////////////////////////////////// 액션 리스너
		addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if(GroupOfButtons.mine[y][x]) { // 누른 버튼이 지뢰일 경우
	            	setText("!!!");
	            	onlyScreen.textbox.setText("Boom!! You lose"); // 다만 이것은 스크린에 출력되지 않고 스크린이 종료되어서
	            	System.out.println("Boom!! You lose"); // 콘솔 출력하였습니다.
	            	try {
	                    Thread.sleep(3000);
	                } catch (InterruptedException X) {
	                }
	            	System.exit(0); // 3초 대기 후 종료합니다.
	            }
	            else {
	            	setText("X"); // 이 버튼은 지뢰가 아니었음을 보여줍니다.
	            	onlyScreen.textbox.setText("It's safe!");
	            }
	            
	            isScanned(x, y); // 해당 버튼은 스캔되었음으로 상태를 변경합니다.
	            scan_around(x, y); // 주변 버튼에 대한 정보를 수집하고 출력합니다.
	            and_again(x, y);
	            
	        }
	    });
		//////////////////////////////////////////////////
	}
	
	void isScanned(int x, int y) { // 해당 버튼은 스캔되었음으로 상태를 변경합니다.
		GroupOfButtons.scanned[y][x] = true;
	}
	
	void scan_around(int x, int y) { // 주변 버튼에 대한 정보를 수집하고 출력합니다.
		
		int mine_count = 0; // 탐색 범위 내 지뢰의 갯수를 세는 변수입니다.
		
		int x_min = Math.max(x-1, 0); // 탐색할 범위를 설정합니다. min, max, 그리고 GroupOfButtons에 저장된 x,y값을 활용함으로써,
		int x_max = Math.min(x+1, GroupOfButtons.saveX); // 배열의 범위를 초과하여 존재하지 않는 배열에 대한 작업을 진행하지 않도록 하였습니다.
		
		int y_min = Math.max(y-1, 0);
		int y_max = Math.min(y+1, GroupOfButtons.saveY);
		
		for(int i=y_min; i<y_max; i++) {
			for(int j=x_min; j<x_max; j++) {
				if(GroupOfButtons.mine[i][j]==true) { // 탐색 범위 내에 지뢰가 발견될 때마다 지뢰의 갯수를 늘립니다.
					mine_count++;
				}
				else {
					String str = (mine_count == 0) ? "X" : String.valueOf(mine_count); // 지뢰의 갯수에 따라 버튼에 표시할 텍스트를 다르게 설정합니다.
					GroupOfButtons.bt[i][j].setText(str); // 지뢰가 주변에 있다면 지뢰의 갯수를 버튼 내 텍스트로 표시합니다. 
					GroupOfButtons.scanned[i][j] = true;
					mine_count = 0;
				}
				
			}
		}
	}
	
	void and_again(int a, int b) {
		
		int a_min = Math.max(a-1, 0); // 탐색할 범위를 설정합니다. min, max, 그리고 GroupOfButtons에 저장된 x,y값을 활용함으로써,
		int a_max = Math.min(a+1, GroupOfButtons.saveX); // 배열의 범위를 초과하여 존재하지 않는 배열에 대한 작업을 진행하지 않도록 하였습니다.
		
		int b_min = Math.max(b-1, 0);
		int b_max = Math.min(b+1, GroupOfButtons.saveY);
		
		for(int c=b_min; c<b_max; c++){
			for(int d=a_min; d<a_max; d++) {
				scan_around(b, c);
			}
		}
	}


}
