package BOJ1600;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int K, H, W;
	static int[][] board;
	static boolean[][][] visited;
	static int[][] horseDir = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
	static int[][] monkeyDir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		W = Integer.parseInt(temp[0]);
		H = Integer.parseInt(temp[1]);
		board = new int[H][W];
		visited = new boolean[H][W][K+1];
		for(int i = 0; i < H; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < W; j++)
				board[i][j] = Integer.parseInt(temp[j]);
		}
		
		bfs();
				
	}

	public static void bfs() {
		Queue<int[]> que = new LinkedList<>();
		
		que.offer(new int[] {0, 0, 0, 0}); // r, c, m, k

		int current[];
		while (!que.isEmpty()) {
			current = que.poll();
			
			if(current[0] == H-1 && current[1] == W-1) {
				System.out.println(current[2]);
				return;
			}

			int nextR, nextC;
			for(int i = 0; i < monkeyDir.length; i++) {
				nextR = current[0] + monkeyDir[i][0];
				nextC = current[1] + monkeyDir[i][1];
				if((0 <= nextR && nextR < H && 0 <= nextC && nextC < W && board[nextR][nextC] != 1) 
						&& !visited[nextR][nextC][current[3]] ) {
					que.offer(new int[] {nextR, nextC, current[2] + 1, current[3]});
					visited[nextR][nextC][current[3]] = true;
				}
			}
			if(current[3] < K) {
				for(int i = 0; i < horseDir.length; i++) {
					nextR = current[0] + horseDir[i][0];
					nextC = current[1] + horseDir[i][1];
					if((0 <= nextR && nextR < H && 0 <= nextC && nextC < W && board[nextR][nextC] != 1) 
							&& !visited[nextR][nextC][current[3] + 1] ) {
						que.offer(new int[] {nextR, nextC, current[2] + 1, current[3] + 1});
						visited[nextR][nextC][current[3] + 1] = true;
					}
				}
			}
		}
		
		System.out.println(-1);
	}
	
}
