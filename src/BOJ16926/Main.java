package BOJ16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		int R = Integer.parseInt(temp[2]);
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(temp[j]);
		}

		/** main **/
		// N/2와 M/2 중 작은 값을 x라고 하면 시작인덱스 : arr[0][0]~arr[x-1][x-1]
		int idxCnt = (N / 2 <= M / 2) ? N / 2 : M / 2;
		for (int r = 0; r < R; r++) { // R번 회전
			for (int i = 0; i < idxCnt; i++) // 배열에서 idxCnt 깊이 만큼 내부로 들어가서 돌림
				leftRotation(arr, 0 + i, N - 1 - i, 0 + i, M - 1 - i);
		}

		// output
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				sb.append(arr[i][j]).append(" ");
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static void leftRotation(int[][] arr, int rs, int re, int cs, int ce) {
		int temp = arr[rs][cs];
		for (int i = cs; i < ce; i++)
			arr[rs][i] = arr[rs][i + 1];
		for (int i = rs; i < re; i++)
			arr[i][ce] = arr[i + 1][ce];
		for (int i = ce; i > cs; i--)
			arr[re][i] = arr[re][i - 1];
		for (int i = re; i > rs; i--)
			arr[i][cs] = arr[i - 1][cs];
		arr[rs + 1][cs] = temp;
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
