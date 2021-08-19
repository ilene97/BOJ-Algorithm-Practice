package BOJ3109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 빵집 : https://www.acmicpc.net/problem/3109 **/
public class Main {
	static int R, C;
	static String[][] map;
	static int cnt;
	static int[][] dir = {{-1, 1}, {0, 1}, {1, 1}};
	static boolean flag = true;
	//static int[] pipes;
	
	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		//pipes = new int[C];
		map = new String[R][C];
		for(int i = 0; i < R; i++) {
			temp = br.readLine().split("");
			for(int j = 0; j < C; j++)
				map[i][j] = temp[j];
		}
		br.close();
		
		// solving
		for(int i = 0; i < R; i++) {
			backtrack(i,0);
			flag = true;
		}
		System.out.println(cnt);
	}

	private static void backtrack(int sr, int sc) {		
		//pipes[sc] = sr;
		
		if(sc == C - 1) {
			cnt++;
			flag = false;
			//for(int i = 0; i < C; i++)
			//	map[pipes[i]][i] = "O";
			return;
		}
		
		for(int i = 0; i < dir.length; i++) {
			int nextR = sr + dir[i][0], nextC = sc + dir[i][1];
			if(flag && isAvailable(nextR, nextC)) {
				map[nextR][nextC] = "O";
				backtrack(nextR, nextC);
			}
		}
	}

	private static boolean isAvailable(int r, int c) {
		if(0 <= r && r < R && 0 <= c && c < C && map[r][c].equals("."))
			return true;
		return false;
	}
	

}
