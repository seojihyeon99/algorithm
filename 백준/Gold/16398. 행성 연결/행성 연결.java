import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int node;
	int cost;
	
	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
	
	public Node(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
}
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 행성의 수
		
		int[][] edges = new int[n][n];
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				edges[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[] include = new boolean[n]; // MST에 해당 정점 포함 여부
		int[] dist = new int[n]; // MST와 정점 x 사이의 거리
		Arrays.fill(dist, 100_000_001);
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(0, 0));
		
		long cost = 0; // 관리 비용 합
		int cnt = 0; // MST에 포함된 정점의 수
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			// 이미 MST에 포함되어있다면 pass
			if(include[cur.node]) continue;
			
			// MST에 해당 정점과 간선을 추가
			include[cur.node] = true;
			cost += cur.cost;
			cnt++;
			
			// 추가된 정점과 이웃한 정점들을 보면서
			for(int i=0; i<n; i++) {
				if(cur.node == i) continue;
				
				// 현재 MST와 연결했을 때, 비용이 더 작다면 업데이트 
				if(dist[i] > edges[cur.node][i]) {
					dist[i] = edges[cur.node][i];
					queue.offer(new Node(i, edges[cur.node][i]));
				}
			}
			
			if(cnt == n) break;
		}
		
		System.out.println(cost);
	}
}