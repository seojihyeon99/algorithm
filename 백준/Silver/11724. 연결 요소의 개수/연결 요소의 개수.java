import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 정점 개수
		int m = Integer.parseInt(st.nextToken()); // 간선 개수
		boolean[] visited = new boolean[n]; // 방문 체크 
		
		List<List<Integer>> graph = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			graph.add(new ArrayList<>()); // 각 노드별 리스트 만들어줌
		}
		
		// 간선 개수만큼 간선 입력받음
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u-1).add(v-1);
			graph.get(v-1).add(u-1);
		}
		
		// 정점 수만큼 탐색 시작
		int count = 0; // 연결 요소의 개수
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i=0; i<n; i++) {
			if(visited[i]) { // 이미 방문했으면 패스~
				continue;
			}
			
			queue.offer(i); 
			count++;
			visited[i] = true;
			
			while(!queue.isEmpty()) {
				int idx = queue.poll();		

				for(int j=0; j<graph.get(idx).size(); j++) {					
					int next = graph.get(idx).get(j);
					if(!visited[next]) {
						queue.offer(next);
						visited[next] = true;
					}
				}
			}
		}
			
		
		System.out.println(count);
		
	}
}
