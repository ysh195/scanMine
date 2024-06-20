package scan_mine;

public class GroupOfButtons {
	public static boolean[][] scanned, mine;
	public static Buttons bt[][];
	public static int saveX, saveY;
	
	public GroupOfButtons(int x, int y) {
		
		saveX = x;
		saveY = y;
		
		scanned = new boolean[saveY][saveX];
		mine = new boolean[saveY][saveX];
		bt = new Buttons[saveY][saveX];
		
		for(int i=0; i<saveY; i++) {
			for(int j=0; j<saveX; j++) {
				
				bt[i][j] = new Buttons(j,i);
				
				scanned[i][j] = false;
				
				int random_num = (int)(Math.random()*11);
				mine[i][j] = (random_num < 1) ? true : false;
			}
		}
	}

}
