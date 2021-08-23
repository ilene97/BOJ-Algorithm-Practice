package BOJ1260;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	static int N, M, V;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
	static Queue<Integer> que = new LinkedList<Integer>();
	static Stack<Integer> stack = new Stack<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		V = Integer.parseInt(temp[2]);
		visited = new boolean[N + 1];
		for(int i = 0; i <= N; i++) {
			ArrayList<Integer> list = new ArrayList<>();
			nodes.add(list);
		}
		int from, to;
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			from = Integer.parseInt(temp[0]);
			to = Integer.parseInt(temp[1]);
			nodes.get(from).add(to);
			nodes.get(to).add(from);
		}
		
		//for(int i = 0; i < nodes.size(); i++)
		//	System.out.println(Arrays.toString(nodes.get(i).toArray()));
		
		for(int i = 0; i <= N; i++)
			Collections.sort(nodes.get(i));
		
		sb.append(V).append(" ");
		dfs(V);
		sb.setLength(sb.length()-1);
		sb.append("\n");
		Arrays.fill(visited, false);
		bfs();
		System.out.println(sb);
		
	}
	
	private static void bfs() {
		que.offer(V);
		visited[V] = true;
		sb.append(V).append(" ");
		
		while(!que.isEmpty()) {
			int t = que.poll();
			ArrayList<Integer> list = nodes.get(t);
			for(int i = 0; i < list.size(); i++) {
				int u = list.get(i);
				if(!visited[u]) {
					que.offer(u);
					visited[u] = true;
					sb.append(u).append(" ");
				}
			}
		}
		sb.setLength(sb.length()-1);
		sb.append("\n");
	}

	private static void dfs(int v) {
		visited[v] = true;
		
		ArrayList<Integer> list = nodes.get(v);
		for(int i = 0; i < list.size(); i++) {
			int u = list.get(i);
			if(!visited[u]) {
				visited[u] = true;
				sb.append(u).append(" ");
				dfs(u);
			}
		}
	}
}