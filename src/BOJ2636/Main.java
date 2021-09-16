package BOJ2636;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 치즈
public class Main {
	static int H, W;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		H = Integer.parseInt(temp[0]);
		W = Integer.parseInt(temp[1]);
		board = new int[H][W];
		for(int i = 0; i < H; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < W; j++)
				board[i][j] = Integer.parseInt(temp[j]);
		}
		
		
		/** main algo **/
		int hourAgoCheese = 0;
		int hour = 0;
		
		while(true) {			
			findBorder_bfs();
			
			/*
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++)
					System.out.printf("%3d", board[i][j]);
				System.out.print("\n");
			}
			System.out.println();
			 */
			
			// 치즈 개수 세기 && 가장자리 치즈 녹이기
			int curCheese = 0;
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(board[i][j] == 1 || board[i][j] == -1) curCheese++;
					if(board[i][j] == -1) board[i][j] = 0;
				}
			}
			
			if(curCheese == 0) break;
			
			hour++;
			hourAgoCheese = curCheese;
		}
		
		// output
		System.out.println(hour);
		System.out.println(hourAgoCheese);

	}

	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void findBorder_bfs() {
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		
		que.offer(new int[] {0, 0}); 
		visited[0][0] = true;

		int current[];
		while (!que.isEmpty()) {
			current = que.poll();
			
			for(int i = 0; i < dir.length; i++) {
				int r = current[0] + dir[i][0];
				int c = current[1] + dir[i][1];
				
				if(0 <= r && r < H && 0 <= c && c < W && !visited[r][c]) {
					if(board[r][c] == 0) {
						que.offer(new int[] {r, c});
						visited[r][c] = true;
					}
					else if(board[r][c] == 1) {
						board[r][c] = -1;
					}
				}
			}
		}
}

}
