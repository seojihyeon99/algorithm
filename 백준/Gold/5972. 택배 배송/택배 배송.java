import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 처음에 pq안쓰고 매 반복마다 배열의 모든 값 탐색했음.. => 시간초과(50000*50000)
 * PriorityQueue로 구현한 Dijkstra 알고리즘 이용
 */

public class Main {
	static class Node {
		int to;
		int weight;
		
		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
	
	static class Vertex implements Comparable<Vertex>{
		int no; // 해당 정점 번호
		int weight; // 시작점과 연결했을 때 간선 비용
		
		public Vertex(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight-o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 정점의 수
		int m = Integer.parseInt(st.nextToken()); // 간선의 수
		
		boolean[] visited = new boolean[n+1]; // 해당 정점 방문 여부
		int[] dist = new int[n+1]; // 시작점 ~ 해당 정점까지의 최소 거리
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0; // 정점 1이 시작점
		
		List<Node>[] adjList = new List[n+1];
		for(int i=1; i<=n; i++) {
			adjList[i] = new ArrayList<>();
		}
		// 간선 정보 입력받음
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			// 양방향 그래프이므로, 둘 다 넣어줌
			adjList[from].add(new Node(to, weight));
			adjList[to].add(new Node(from, weight));
		}
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(1, 0)); // 시작 정점 pq에 넣음
		while(!pq.isEmpty()) {
			// 현재 방문하지 않은 정점들 중, 시작점부터 가장 거리가 최소인 정점 찾음
			Vertex cur = pq.poll();
			if(visited[cur.no]) continue; // 이미 방문했다면 -> 다시 pq에서 꺼냄
			
			visited[cur.no] = true; // 해당 정점 방문 처리
			
			// 해당 정점과 이웃한 정점들 탐색
			for(Node node : adjList[cur.no]) {
				if(!visited[node.to] && dist[node.to] > dist[cur.no] + node.weight) {
					dist[node.to] = dist[cur.no] + node.weight; // 거리 더 짧다면 업데이트
					pq.offer(new Vertex(node.to, dist[node.to])); // pq에 업데이트된 정점과 거리 넣음
				}
			}
			
			if(cur.no == n) break; // 도착지라면 break
		}
		
		System.out.println(dist[n]);
	}
	
	static int findMin(int[] dist, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int vertex = -1;
		for(int i=1; i<dist.length; i++) {
			if(!visited[i] && min > dist[i]) {
				min = dist[i];
				vertex = i;
			}
		}
		return vertex;
	}
}
