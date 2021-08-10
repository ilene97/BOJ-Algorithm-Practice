package BOJ1158;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		
		// 1 ~ N까지 큐에 저장
		Queue<Integer> yose = new LinkedList<>();
		for (int i = 1; i <= N; i++)
			yose.offer(i);

		// 큐가 빌 때까지 반복
		sb.append("<");
		while (!yose.isEmpty()) {
			for (int cnt = 1; cnt < K; cnt++)
				yose.offer(yose.poll());			// 1 ~ (K-1)번째까지 큐에서 빼서 큐 뒤에 다시 넣음
			sb.append(yose.poll()).append(", ");	// K번째 숫자 큐에서 뺌
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");

		// output
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
