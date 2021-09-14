package BOJ1149;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] costs = new int[N+1][3];
		String[] temp;
		for(int i = 1; i <= N; i++) {
			temp = br.readLine().split(" ");
			costs[i][0] = Integer.parseInt(temp[0]);
			costs[i][1] = Integer.parseInt(temp[1]);
			costs[i][2] = Integer.parseInt(temp[2]);
		}
		
		int[][] resultCosts = new int[N+1][3];
		resultCosts[1] = costs[1];
		for(int i = 2; i <= N; i++) {
			resultCosts[i][0] = costs[i][0] + Math.min(resultCosts[i-1][1], resultCosts[i-1][2]);
			resultCosts[i][1] = costs[i][1] + Math.min(resultCosts[i-1][0], resultCosts[i-1][2]);
			resultCosts[i][2] = costs[i][2] + Math.min(resultCosts[i-1][0], resultCosts[i-1][1]);
		}
		
		//for(int i = 1; i <= N; i++)
		//System.out.println(Arrays.toString(resultCosts[i]));
		System.out.println(IntStream.of(resultCosts[N]).min().getAsInt());
		
	}

}
