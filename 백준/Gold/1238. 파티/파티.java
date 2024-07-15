import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int v; // 정점번호
	int t; // 소요시간
	
	public Node(int v, int t) {
		this.v = v;
		this.t = t;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.t - o.t;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 학생(마을)의 수
		int m = Integer.parseInt(st.nextToken()); // 단방향 도로의 수
		int x = Integer.parseInt(st.nextToken()); // 파티를 열 예정인 마을
		
		// 그래프 초기화 및 입력받기
		List<int[]>[] list = new List[n+1]; // int[] : 정점, 시간(가중치)
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<int[]>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); // 시작점
			int e = Integer.parseInt(st.nextToken()); // 끝점
			int t = Integer.parseInt(st.nextToken()); // 소요시간(가중치)
			
			list[s].add(new int[] {e,t});
		}
		
		
		int[] time = new int[n+1]; // '정점->x' + 'x->정점'의 소요시간
		Arrays.fill(time, Integer.MAX_VALUE);
		time[x] = 0;
		
		// 'x->정점'의 소요시간
		boolean[] visited = new boolean[n+1]; // 해당 정점 방문여부
		PriorityQueue<Node> q = new PriorityQueue<>();
		for(int i=1; i<=n; i++) {
			q.add(new Node(i, Integer.MAX_VALUE));
		}
		q.add(new Node(x, 0)); // 시작 노드 (x)
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(visited[cur.v]) continue;
			
			visited[cur.v] = true; // 현재 노드 방문함
			
			// 인접 노드에 대해 최단거리 갱신
			for(int j=0; j<list[cur.v].size(); j++) {
				int[] adj = list[cur.v].get(j);
				int tmp = cur.t + adj[1];
				
				if(tmp < time[adj[0]]) {
					time[adj[0]] = tmp;
					q.add(new Node(adj[0], tmp));
				}
			}		
		}
		
		// '정점->x'의 소요시간. 시작지점 s를 1번 ~ n번까지 바꿔가며 다익스트라를 통해 최단거리 구함
		for(int s=1; s<=n; s++) {
			int[] dist = new int[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[s] = 0;
			
			visited = new boolean[n+1]; // 해당 정점 방문여부
			q = new PriorityQueue<>();
			
			for(int i=1; i<=n; i++) {
				q.add(new Node(i, Integer.MAX_VALUE));
			}
			
			q.add(new Node(s, 0)); // 시작 노드
			while(!q.isEmpty()) {
				Node cur = q.poll();
				
				if(cur.v == x) break; // 파티 장소 x에 도착했다면
				
				if(visited[cur.v]) continue;
				
				visited[cur.v] = true; // 현재 노드 방문함
				
				// 인접 노드에 대해 최단거리 갱신
				for(int j=0; j<list[cur.v].size(); j++) {
					int[] adj = list[cur.v].get(j);
					int tmp = cur.t + adj[1];
					
					if(tmp < dist[adj[0]]) {
						dist[adj[0]] = tmp;
						q.add(new Node(adj[0], tmp));
					}
				}		
			}
			
			time[s] += dist[x];
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=1; i<=n; i++) {
			max = Math.max(max, time[i]);
		}
		
		System.out.println(max);
	}
}