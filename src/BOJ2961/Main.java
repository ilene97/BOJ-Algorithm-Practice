package BOJ2961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] sour = new int[N];
		int[] bitter = new int[N];
		String[] temp;
		for (int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			sour[i] = Integer.parseInt(temp[0]);
			bitter[i] = Integer.parseInt(temp[1]);
		}

		int[] input = new int[N];
		for(int i = 0; i < N; i++)
			input[i] = i;
		
		// PriorityQueue<Integer> answer = new PriorityQueue<>();
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int[] p = new int[N];
			int cnt = 0;
			while (++cnt <= i) p[N - cnt] = 1;

			do {
				int sourValue = 1, bitterValue = 0;
				for (int j = 0; j < N; j++)
					if (p[j] == 1) {
						sourValue *= sour[j];
						bitterValue += bitter[j];
					}
				// answer.offer(Math.abs(bitterValue - sourValue));
				answer = Math.min(answer, Math.abs(bitterValue-sourValue));
			} while (nextPermutation(p));
		}
		
		// System.out.println(answer.poll());
		System.out.println(answer);
	}
	
	private static boolean nextPermutation(int[] inputs) {
		int N = inputs.length;
		int i = N - 1;
		while (i > 0 && inputs[i - 1] >= inputs[i]) i--;
		
		if(i == 0) return false; 
		int j = N - 1; 
		while(inputs[i - 1] >= inputs[j]) j--; 
		swap(inputs, i - 1, j);
		int k = N - 1;
		while(i < k) swap(inputs, i++, k--);
		
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
