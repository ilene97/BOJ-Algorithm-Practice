package BOJ15686;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int N, M;
	static int[] numbers;
	static ArrayList<int[]> chickens, houses; 
	static int minChickenDist = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		numbers = new int[M];
		chickens = new ArrayList<>();
		houses = new ArrayList<>();
		int num;
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				num = Integer.parseInt(temp[j]);
				if(num == 1) houses.add(new int[] {i, j});
				else if (num == 2) chickens.add(new int[] {i, j});
			}
		}
		br.close();
		

		combination(0,0, chickens.size());
		System.out.println(minChickenDist);
		
		// houses.forEach(t -> {System.out.println(Arrays.toString(t));});
	}
	
	private static void combination(int cnt, int start, int N) {
		if (cnt == M) {
			minChickenDist = Math.min(minChickenDist, getCityChickenDist(numbers));
			return;
		}
		for (int i = start; i < N; i++) {
			numbers[cnt] = i;
			combination(cnt + 1, i + 1, N);
		}
	}
	
	private static int getCityChickenDist(int[] numbers) {
		int sum = 0;
		for(int i = 0; i < houses.size(); i++) {
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < M; j++) {
				min = Math.min(min, getDist(houses.get(i), chickens.get(numbers[j])));
			}
			sum += min;
		}
		return sum;
	}

	private static int getDist(int[] house, int[] chicken) {
		return Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
	}
}
