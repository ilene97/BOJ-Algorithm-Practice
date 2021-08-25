package BOJ1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
	static Map<Integer, ArrayList<Edge>> adjacent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		int V = Integer.parseInt(temp[0]); 
		int E = Integer.parseInt(temp[1]);
		int start = Integer.parseInt(in.readLine());
		final int INFINITY = Integer.MAX_VALUE;
		
		adjacent = new HashMap<>();
		int[] distance = new int[V + 1];
		boolean[] visited = new boolean[V + 1];
		
		int from, to, weight;
		for(int i=0; i<E; ++i){
			temp = in.readLine().split(" ");
			from = Integer.parseInt(temp[0]);
			to = Integer.parseInt(temp[1]);
			weight = Integer.parseInt(temp[2]);
			if(!adjacent.containsKey(from))
				adjacent.put(from, new ArrayList<>());
			adjacent.get(from).add(new Edge(to, weight));
		}
		
		Arrays.fill(distance, INFINITY);
		distance[start] = 0;
		
		/** Dijkstra using PQ **/
		/*
		PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            int current = pq.poll().to;

            if (visited[current]) continue;
            visited[current] = true;

            ArrayList<Edge> currentNode = adjacent.get(current);
            if(currentNode != null) {
	            int size = currentNode.size();
	            for (int i = 0; i < size; i++) {
	                int t = currentNode.get(i).to;
	                int w = currentNode.get(i).w;
	
	                if (distance[t] > distance[current] + w) {
	                	distance[t] = distance[current] + w;
	                    pq.offer(new Edge(t, distance[t]));
	                }
	
	            }
            }

        }
        */
		
		/** Dijkstra using Array **/
		int min = 0, current = 0;
		for(int i = 1; i <= V; ++i){
			//a단계 : 방문하지 않은 정점들 중 최소가중치의 정점 선택
			min = INFINITY;
			for(int j = 1; j <= V; j++){
				if(!visited[j] && distance[j] < min){
					min = distance[j];
					current = j;
				}
			}
			visited[current] = true; // 선택 정점 방문 처리
			
			if(adjacent.get(current) != null) {
				for(Edge edge : adjacent.get(current)) {
					if(!visited[edge.to] && distance[edge.to] > min + edge.w)
						distance[edge.to] = min + edge.w;
				}
			}
			
		}
		
		
		// output
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			if(distance[i] == INFINITY)
				sb.append("INF\n");
			else
				sb.append(distance[i]).append("\n");
		}
		System.out.println(sb);
	}

}

class Edge implements Comparable<Edge> {
	int to;
	int w;
	
	public Edge(int to, int w) {
		super();
		this.to = to;
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		return this.w = o.w;
	}
	
	
}
