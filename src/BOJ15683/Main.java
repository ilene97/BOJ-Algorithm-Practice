package BOJ15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static int N, M;
	static int[][] office;
	static ArrayList<Cctv> cctvs = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;
	static int[][] dir = {{-1, 0},{0, 1},{1, 0},{0, -1}};	// 상 우 하 좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		office = new int[N][M];
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				int type = Integer.parseInt(temp[j]);
				office[i][j] = type;
				if(1 <= type && type <= 5)
					cctvs.add(new Cctv(i, j, type));
			}
		}
		
		func(0, office);
		System.out.println(answer);
		
	}

	public static void func(int cnt, int[][] curOffice) {
		int[][] canSee = new int[N][M];
		if(cnt == cctvs.size()) {
			answer = Math.min(answer, numOfZeros(curOffice));
			//System.out.println(answer);
			//for(int i = 0; i < N; i++) {
			//	System.out.println(Arrays.toString(prev[i]));
			//}System.out.println();
			return;
		}
		
		Cctv cur = cctvs.get(cnt);
		switch(cur.type) {
		case 1:
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < N; j++)
					canSee[j] = Arrays.copyOf(curOffice[j], M);
				setCCTV(cur.x, cur.y, dir[i], canSee);
				func(cnt+1, canSee);
			}
			break;
		case 2:
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < N; j++)
					canSee[j] = Arrays.copyOf(curOffice[j], M);
				setCCTV(cur.x, cur.y, dir[i], canSee);
				setCCTV(cur.x, cur.y, dir[i+2], canSee);
				func(cnt+1, canSee);
			}
			break;
		case 3:
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < N; j++)
					canSee[j] = Arrays.copyOf(curOffice[j], M);
				setCCTV(cur.x, cur.y, dir[i], canSee);
				setCCTV(cur.x, cur.y, dir[(i+1)%4], canSee);
				func(cnt+1, canSee);
			}
			break;
		case 4:
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < N; j++)
					canSee[j] = Arrays.copyOf(curOffice[j], M);
				setCCTV(cur.x, cur.y, dir[i], canSee);
				setCCTV(cur.x, cur.y, dir[(i+1)%4], canSee);
				setCCTV(cur.x, cur.y, dir[(i+2)%4], canSee);
				func(cnt+1, canSee);
			}
			break;
		case 5:
			for(int j = 0; j < N; j++)
				canSee[j] = Arrays.copyOf(curOffice[j], M);
			for(int i = 0; i < 4; i++)
				setCCTV(cur.x, cur.y, dir[i], canSee);
			func(cnt+1, canSee);
			break;
		}
		
	}
	
	public static void setCCTV(int x, int y, int[] dir, int[][] canSee) {
		int nextX = x + dir[0];
		int nextY = y + dir[1];
		while(isAvailable(nextX, nextY)) {
			canSee[nextX][nextY] = -1;
			nextX += dir[0];
			nextY += dir[1];
		}
	}
	
	private static boolean isAvailable(int nextX, int nextY) {
		if(0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
			if(office[nextX][nextY] != 6)
				return true;
		}
		return false;
	}

	public static int numOfZeros(int[][] office) {
		int num = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(office[i][j] == 0) num++;
			}
		}
		return num;
	}
}

class Cctv{
	int x;
	int y;
	int type;
	
	public Cctv(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
