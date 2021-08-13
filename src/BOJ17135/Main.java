package BOJ17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int N, M, D;
	static ArrayList<int[]> enemies = new ArrayList<>();
	static int[] archers = new int[3];;
	static int maxKills = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		D = Integer.parseInt(temp[2]);
		for (int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(temp[j]);
				if (num == 1)
					enemies.add(new int[] { i, j });
			}
		}

		combination(0, 0);
		System.out.println(maxKills);
	}

	private static void combination(int cnt, int start) {
		if (cnt == 3) {
			maxKills = Math.max(maxKills, startWar());
			return;
		}
		for (int i = start; i < M; i++) {
			archers[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	private static int startWar() {
		ArrayList<Enemy> tempEnemies = new ArrayList<Enemy>();	// 임시로 적 좌표 저장
		for (int i = 0; i < enemies.size(); i++) {
			int[] temp = enemies.get(i);
			tempEnemies.add(new Enemy(temp[0], temp[1]));
		}
		int kills = 0;
		int cnt = Integer.MAX_VALUE;   // 몇번 내려와야 하는지 계산 (궁수와 가장 멀리 있는 아이의 x좌표)
		for (int i = 0; i < enemies.size(); i++)
			if (enemies.get(i)[0] < cnt)
				cnt = enemies.get(i)[0];
		
		for (; cnt < N; cnt++) {
			Enemy[] killIdx = new Enemy[3];  // 세 궁수가 잡을 적의 좌표 저장
			for(int i = 0; i < 3; i++) {
				for (int j = 0; j < tempEnemies.size(); j++) { // 현재 궁수와 적들 사이의 거리 구하기
					Enemy enemy = tempEnemies.get(j);
					enemy.dist = getDist(enemy, archers[i]);
				}
				Collections.sort(tempEnemies);  // 거리, col 순서로 정렬
				for(int j = 0; j < tempEnemies.size(); j++) {
					Enemy temp = tempEnemies.get(j);
					if(temp.x < N && temp.dist <= D) {  // 처음 만나는 판 위에 있고, 거리 D 이하인 적이 이 궁수가 잡을 적. 
						killIdx[i] = new Enemy(temp.x, temp.y);
						break;
					}
				}
			}
			
			for(int i = 0; i < 3; i++) {   
				if(killIdx[i] != null) {
					for(int j = 0; j < tempEnemies.size(); j++) {
						Enemy temp = tempEnemies.get(j);
						if(temp.x == killIdx[i].x && temp.y == killIdx[i].y) {   
							temp.x = N;		// 각 궁수가 잡을 적의 x좌표에 N 넣어서 잡았다고 표시.
							kills++;
						}
					}
				}
			}
			
			for(int i = 0; i < tempEnemies.size(); i++)  // 적들 한칸씩 전진 (x 좌표 증가)
				tempEnemies.get(i).x++;
		}	
		return kills;
	}

	public static int getDist(Enemy enemy, int colOfOrcher) {
		return Math.abs(N - enemy.x) + Math.abs(colOfOrcher - enemy.y);
	}
}

class Enemy implements Comparable<Enemy>{
	int x;
	int y;
	int dist = 0;

	Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Enemy o) {
		if(this.dist - o.dist == 0)
			return this.y - o.y;
		return this.dist - o.dist;
	}
}
