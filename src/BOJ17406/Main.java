package BOJ17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class Main {
	static Queue<int[]> perms;
	static int K;
	static boolean[] isSelected;
	static int[] numbers; // 생성한 순열 저장

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		K = Integer.parseInt(temp[2]);
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(temp[j]);
		}
		int[][] op = new int[K][3];
		for (int i = 0; i < K; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < 3; j++)
				op[i][j] = Integer.parseInt(temp[j]);
		}
		temp = null;
		br.close();

		/** main algorithm **/
		numbers = new int[K];  // 만든 순열 임시 저장 배열
		isSelected = new boolean[K+1];  // 이미 사용중인 수인지 체크하는 리스트
		perms = new LinkedList<>();   // 가능한 순열들을 저장하는 큐
		PriorityQueue<Integer> values = new PriorityQueue<>();  // 순열들로 구한 배열 값들
		
		permutation(0);  // 가능한 순열 모두 구하기 -> perms에 순열들이 저장됨
		while (!perms.isEmpty()) {
			// 순열 하나씩 꺼내기
			int[] thisidx = perms.poll(); 
			
			// 돌린 배열 저장할 임시 배열
			int[][] arrClone = new int[N][M];
			for(int i =0; i < N; i++)
				System.arraycopy(arr[i], 0, arrClone[i], 0, M);
			
			// 배열 회전시키고 배열값 구해서 큐에 넣기
			for(int i = 0; i < K; i++)  // 순열 순서대로 회전 시키기
				operation(arrClone, op[thisidx[i]-1][0], op[thisidx[i]-1][1], op[thisidx[i]-1][2]);
			values.offer(getArrValue(arrClone));
		}
		
		// output
		System.out.println(values.poll());
	}

	private static void permutation(int cnt) {
		if (cnt == K) { // 종료 조건
			perms.offer(numbers.clone());
			return;
		}
		// 가능한 모든 수 시도
		for (int i = 1; i <= K; i++) {
			if (isSelected[i])
				continue; // 이미 사용 중인 수면 다음 수로.
			numbers[cnt] = i;
			isSelected[i] = true;
			// 다음자리 순열 뽑기
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}

	public static Integer getArrValue(int[][] arr) {
		int value = IntStream.of(arr[0]).sum();
		for (int i = 1; i < arr.length; i++) {
			int temp = IntStream.of(arr[i]).sum();
			if (temp < value)
				value = temp;
		}
		return value;
	}

	static void operation(int[][] arr, int r, int c, int s) {
		int idxCnt = s;
		for (int i = 0; i < idxCnt; i++)
			rightRotation(arr, r - s - 1 + i, r + s - 1 - i, c - s - 1 + i, c + s - 1 - i);
	}

	static void rightRotation(int[][] arr, int rs, int re, int cs, int ce) {
		int temp = arr[rs][cs];
		for (int i = rs; i < re; i++)
			arr[i][cs] = arr[i + 1][cs];
		for (int i = cs; i < ce; i++)
			arr[re][i] = arr[re][i + 1];
		for (int i = re; i > rs; i--)
			arr[i][ce] = arr[i - 1][ce];
		for (int i = ce; i > cs; i--)
			arr[rs][i] = arr[rs][i - 1];
		arr[rs][cs + 1] = temp;
	}

}
