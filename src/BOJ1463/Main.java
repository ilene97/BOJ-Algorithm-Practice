package BOJ1463;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// dp[N] = min(dp[N-1], dp[N/3], dp[N/2]) + 1
		
		int[] dp = new int[N+1];
		int min;
		for(int i = 2; i <= N; i++) {
			min = Integer.MAX_VALUE;
			
			if(i % 3 == 0) min = Math.min(dp[i/3], min);
			if(i % 2 == 0) min = Math.min(dp[i/2], min);
			min = Math.min(dp[i-1], min);
			
			dp[i] = min + 1;
		}
		System.out.println(dp[N]);
	}
}
