package scan_mine;

import java.util.Scanner;

public class GamePlay {

	public static void main(String[] args) throws ClassNotFoundException, NullPointerException {
		
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		System.out.println("원하시는 가로축 갯수를 입력해주세요. (최대 : 17)");		
		int X_size = sc1.nextInt();
		
		System.out.println("원하시는 세로축 갯수를 입력해주세요. (최대 : 10)");
		int Y_size = sc2.nextInt();
		
		int X_Max = 17, Y_Max = 10;
		
		X_size = Math.min(Math.max(X_size, 1), X_Max); // 사용자에게 사이즈를 입력받을 경우를 대비해 어느 정도 한계를 설정
		Y_size = Math.min(Math.max(Y_size, 1), Y_Max);
		
		Screen onlyScreen = Screen.getInstance(); // 다수 생성되는 버튼도 다 Screen과 연결해주려고 안에 스크린 생성자를 가지고 있음
												// 근데 그런 식으로 하면 Screen 생성자가 너무 많으니 Screen은 싱글톤으로 만듦 
		
		GroupOfButtons GOB = new GroupOfButtons(X_size, Y_size);
		
		onlyScreen.setVisible();

	}

}
