package BOJ14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N, M;
	static int map[][];
	static ArrayList<Integer> zeroLoca = new ArrayList<>();
	static ArrayList<Integer> virusLoca = new ArrayList<>();
	static int[] combNum = new int[3];
	static int minVirusArea = Integer.MAX_VALUE;
	static int[][] dir = {{1, 0},{-1, 0},{0, 1},{0, -1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]); // 세로
		M = Integer.parseInt(temp[1]); // 가로
		map = new int[N][M];
		int num;
		int cntWall = 0;
		for (int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				num = Integer.parseInt(temp[j]);
				if (num == 0)
					zeroLoca.add(i * M + j);
				else if (num == 2)
					virusLoca.add(i * M + j);
				else
					cntWall++;
				map[i][j] = num;
			}
		}
		
		/*
		0  1  2  3 
		4  5  6  7
		8  9  10 11
		row = i/M
		col = i%M
		*/
		
		combination(0, 0);

		System.out.println(N*M - cntWall - 3 - minVirusArea);
	}

	private static void combination(int cnt, int start) {
		if (cnt == 3) {
			buildWall();
			bfs();
			clearMap();
			return;
		}
		for (int i = start; i < zeroLoca.size(); i++) {
			combNum[cnt] = zeroLoca.get(i);
			combination(cnt + 1, i + 1);
		}
	}

	private static void buildWall() {
		for (int i = 0; i < 3; i++) {
			int r = combNum[i] / M;
			int c = combNum[i] % M;
			map[r][c] = 1;
		}
	}

	private static void bfs() {
		int cntVirusArea = 0;
		Queue<int[]> que = new LinkedList<>();
		for(int i = 0; i < virusLoca.size(); i++) {
			int loca = virusLoca.get(i);
			que.offer(new int[] {loca / M, loca % M});
			cntVirusArea++;
		}

		int current[];
		while (!que.isEmpty()) {
			current = que.poll();

			int nextR, nextC;
			for(int i = 0; i < dir.length; i++) {
				nextR = current[0] + dir[i][0];
				nextC = current[1] + dir[i][1];
				
				if((0 <= nextR && nextR < N && 0 <= nextC && nextC < M) && map[nextR][nextC] == 0) {
					map[nextR][nextC] = 2;
					cntVirusArea++;
					que.offer(new int[] {nextR, nextC});
				}
			}
		}
		
		minVirusArea = Math.min(minVirusArea, cntVirusArea);
	}

	private static void clearMap() {
		for (int i = 0; i < 3; i++) {
			int r = combNum[i] / M;
			int c = combNum[i] % M;
			map[r][c] = 0;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 2) map[i][j] = 0;
			}
		}
		
		for(int i = 0; i < virusLoca.size(); i++) {
			int loca = virusLoca.get(i);
			map[loca / M][loca % M] = 2;
		}
	}

}
