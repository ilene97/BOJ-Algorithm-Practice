package BOJ17143;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Main {
	// 낚시왕
	static int R, C, M;
	static int[][][] map;	// 상어 넘버 저장
	static Map<Integer, Shark> sharks = new HashMap<>();	// 상어 넘버 : 1~M
	static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 위 아래 왼 오
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		M = Integer.parseInt(temp[2]);
		map = new int[R][C][M];
		for(int i = 1; i <= M; i++) {
			temp = br.readLine().split(" ");
			int r = Integer.parseInt(temp[0]) - 1;
			int c = Integer.parseInt(temp[1]) - 1;
			sharks.put(i, new Shark(r, c, Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4])));
			map[r][c][0] = i;
		}
		
		int answer = 0;
		for(int c = 0; c < C; c++) {
			// c열의 상어 중에서 땅과 가장 가까운 상어 잡기
			for(int r = 0; r < R; r++) {
				if(map[r][c][0] == 0) continue;
				else {
					int sharkNum = map[r][c][0];
					answer += sharks.get(sharkNum).z;
					sharks.remove(sharkNum);
					map[r][c][0] = 0;
					break;
				}
			}
			
			// 상어 이동
			for(int i = 1; i <= M; i++) {
				if(sharks.containsKey(i)) {
					Shark shark = sharks.get(i);
					int nextR = shark.r;
					int nextC = shark.c;
					int rowOffset = dir[shark.d - 1][0];
					int colOffset = dir[shark.d - 1][1];
					int cnt = 0;
					
					if(shark.d == 1 || shark.d == 2) {	// 위아래
						while(cnt++ < shark.s) {
							if(nextR == 0 || nextR == R-1) 
								rowOffset *= -1;
							nextR += rowOffset;
							nextC += colOffset;
						}
					}
					else {	// 양옆
						while(cnt++ < shark.s) {
							if(nextC == 0 || nextC == C-1) 
								colOffset *= -1;
							nextR += rowOffset;
							nextC += colOffset;
						}
					}
					
					for(int j = 0; j < M; j++) {
						if(map[nextR][nextC][j] == 0)
							map[nextR][nextC][j] = i;
					}
					shark.r = nextR;
					shark.c = nextC;
				}
				else continue;
			}
			
			for(int i = 1; i <= M; i++) {
				if(sharks.containsKey(i)) {
					Shark shark = sharks.get(i);
					if(map[shark.r][shark.c][1] != 0) {
						for(int j = 1; j < M; j++) {
							if(map[shark.r][shark.c][j] == 0) break;
							if(sharks.get(map[shark.r][shark.c][0]).z < sharks.get(map[shark.r][shark.c][j]).z) {
								sharks.remove(map[shark.r][shark.c][0]);
								map[shark.r][shark.c][0] = map[shark.r][shark.c][1];
								map[shark.r][shark.c][1] = 0;
							}
						}
					}
				}
				else continue;
			}
		}
		
		System.out.println(answer);

	}
	
	public static class Shark{
		int r, c, s, d, z;	// 행, 열, 속력, 방향, 크기
		
		Shark(int r, int c, int s, int d, int z){
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

}
