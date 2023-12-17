import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 최대 간선수(50000*49999/2)대비 문제에서 주어진 간선수(50000)가 훨씬 적으므로 => 인접리스트 사용!
 */
class Node{
	int node;
	int edge;
	
	public Node(int node, int edge) {
		super();
		this.node = node;
		this.edge = edge;
	}
	
	public int getNode() {
		return node;
	}
	
	public int getEdge() {
		return edge;
	}
}

public class Main {
	
	static boolean[] visited; // 해당 헛간 방문 여부
	static int[] dist; // 해당 헛간까지의 최단 거리
	static int cnt; // 방문한 헛간의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] c = br.readLine().split(" ");
		int n = Integer.parseInt(c[0]); // 헛간의 개수
		int m = Integer.parseInt(c[1]); // 길의 개수
		
		visited = new boolean[n+1];
		dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE); // 정수 최댓값으로 초기화
		
		List<Node>[] list = new List[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		// 현재 길의 정보 입력받기		
		for(int i=0; i<m; i++) {
			c = br.readLine().split(" ");
			int v1 = Integer.parseInt(c[0]); // 시작 정점
			int v2 = Integer.parseInt(c[1]); // 끝 정점
			int edge = Integer.parseInt(c[2]); // 간선 가중치
			
			// 양방향이므로 두 정점리스트에 모두 추가해줌
			list[v1].add(new Node(v2, edge));
			list[v2].add(new Node(v1, edge));
		}
		
		// 여기서 부터 시뮬레이션 시작!!
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(1); // 시작 정점(1)을 큐에 넣음
		dist[1] = 0;
		while(!queue.isEmpty()) {
			int cur = queue.poll(); // 현재 탐색중인 정점
			
			for(int i=0; i<list[cur].size(); i++) { // 이웃하는 정점들 탐색
				int adj = list[cur].get(i).getNode();
				if(dist[adj] > dist[cur] + list[cur].get(i).getEdge()) { // 간선 거리 더 짧다면 업데이트
					dist[adj] = dist[cur] + list[cur].get(i).getEdge();	
					queue.offer(adj); // 큐에 다시 추가
				}
			}
			
		}
		
		System.out.println(dist[n]);
	}
}
