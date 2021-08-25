package BOJ16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 	백준 16236번 아기상어 : https://www.acmicpc.net/problem/16236 
 * **/

public class Main {
	static int N;					// 공간의 크기
	static int[][] space;		   	// 공간의 상태
	static boolean[][] visited;
	static int babyR, babyC;		// 아기 상어의 위치
	static int mysize = 2, ate = 0;	// 아기 상어의 크기, 먹은 물고기 수 카운트
	static int sec = 0;				// 시간
	static int[][] dir = {{-1, 0},{0, -1},{0, 1},{1, 0}}; // 상 좌 우 하
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		space = new int[N][N];
		visited = new boolean[N][N];
		String[] temp;
		int num;
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				num = Integer.parseInt(temp[j]);
				if(num == 9) {
					babyR = i;
					babyC = j;
					continue;
				}
				if(num != 0)
					space[i][j] = num;
			}
		}
		
		/** main **/
		while(bfs()) {
			for(int i = 0; i < N; i++)
				Arrays.fill(visited[i], false);
		}
		
		// output
		System.out.println(sec);

	}

	private static boolean bfs() {
		Queue<Pair> que = new LinkedList<>();
		que.offer(new Pair(babyR, babyC)); // 루트 노드
		visited[babyR][babyC] = true;
		Pair current = null;
		int level = 0, size = 0;
		while (!que.isEmpty()) {
			size = que.size();
			
			while (size-- > 0) {
				current = que.poll();
				
				if(space[current.x][current.y] != 0 && space[current.x][current.y] < mysize) {
					System.out.println(current.x + " " + current.y);
					babyR = current.x;
					babyC = current.y;
					ate++;
					sec += level;
					space[current.x][current.y] = 0;
					if(ate == mysize) {
						mysize++;
						ate = 0;
					}
					return true;
				}
				
				
				for(int i = 0; i < 4; i++) {
					int nextR = current.x + dir[i][0];
					int nextC = current.y + dir[i][1];
					if(isAvailable(nextR, nextC) && !visited[nextR][nextC] && space[current.x][current.y] <= mysize) {
						que.offer(new Pair(nextR, nextC));
						visited[nextR][nextC]= true;
					}
				}
			}
			level++;
		}
		return false;
	}
	
	private static boolean isAvailable(int r, int c) {
		if(0 <= r && r < N && 0 <= c && c < N ) return true;
		return false;
	}
}

class Pair{
	int x, y;

	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}
