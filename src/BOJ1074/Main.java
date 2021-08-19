package BOJ1074;

import java.util.Scanner;

public class Main {
	static int r, c;
	static int cnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		sc.close();

		search(0, 0, N);
		System.out.println(cnt);
	}

	static void search(int startR, int startC, int N) {
		if (startR == r && startC == c)	 	//  1 | 2
			return; 						// -------
											//  3 | 4
		if (startR <= r && r < startR + Math.pow(2, N - 1)) {
			if (startC <= c && c < startC + Math.pow(2, N - 1)) 		// 영역 1
				search(startR, startC, N - 1);
			else { 														// 영역 2
				cnt += (int) Math.pow(2, 2 * N - 2);
				search(startR, startC + (int) Math.pow(2, N - 1), N - 1);
			}
		} 
		else {
			if (startC <= c && c < startC + Math.pow(2, N - 1)) { 		// 영역3
				cnt += (int) Math.pow(2, 2 * N - 2) * 2;
				search(startR + (int) Math.pow(2, N - 1), startC, N - 1);
			} else {													// 영역4
				cnt += (int) Math.pow(2, 2 * N - 2) * 3;
				search(startR + (int) Math.pow(2, N - 1), startC + (int) Math.pow(2, N - 1), N - 1);
			}
		}
		
	}
}
