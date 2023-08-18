import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n; // 정점의 개수
	static int m; // 간선의 개수
	static int v; // 탐색 시작할 정점의 번호
	
	static boolean[] visited; // 정점의 방문 체크. 0번째 정점은 사용 x -> n+1 크기로 만듦
	
	static StringBuilder sb = new StringBuilder(); // 한번에 출력하기 위한 StringBuilder
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 정점의 개수
		m = Integer.parseInt(st.nextToken()); // 간선의 개수
		v = Integer.parseInt(st.nextToken()); // 탐색 시작할 정점의 번호
		
		// 인접 리스트 생성(0번째 정점은 사용 안할것이므로, n+1개만큼 생성)
		List<List<Integer>> graph = new ArrayList<>();
		for(int i=0; i<n+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 인접 리스트에 간선 정보 저장
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int e1 = Integer.parseInt(st.nextToken()); 
			int e2 = Integer.parseInt(st.nextToken());
			graph.get(e1).add(e2);
			graph.get(e2).add(e1); // 반드시 대칭?방향도 추가해줘야!!
		}
		
		// 인접 리스트 정렬 -> 정점 번호가 작은것부터 방문 위함
		for(int i=0; i<n+1; i++) {
			Collections.sort(graph.get(i));
		}
		
		visited = new boolean[n+1]; // 방문 체크를 위한 visited 배열 초기화
		visited[v] = true; // 시작정점 방문 체크
		sb.append(v + " "); // 시작정점 출력
		dfs(graph, v); // dfs 수행
		sb.append("\n");
		
		visited = new boolean[n+1]; // 방문 체크를 위한 visited 배열 초기화
		bfs(graph); // bfs 수행
		sb.append("\n");
		
		System.out.println(sb.toString());
	}
	
	
	static void dfs(List<List<Integer>> graph, int vertex) {
		for(int i=0; i<graph.get(vertex).size(); i++) {
			int adj = graph.get(vertex).get(i); // 인접한 정점의 번호
			if(!visited[adj]) { // 인접 정점을 방문하지 않았다면
				visited[adj] = true; // 해당 인접 정점을 방문 처리
				sb.append(adj + " "); // 해당 인접 정점을 출력
				dfs(graph, adj); // 다시 해당 정점을 기준으로 재귀호출
			}
		}
	}
	
	static void bfs(List<List<Integer>> graph) { 
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(v); // 시작 정점을 큐에 넣어줌
		visited[v] = true; // 시작 정점을 방문 체크
		
		while(!queue.isEmpty()) { // 큐가 빌때까지 반복
			int cur = queue.poll(); // 큐에서 하나 꺼냄
			sb.append(cur + " ");
			for(int i=0; i<graph.get(cur).size(); i++) { // 해당 인접리스트의 사이즈만큼 반복
				int adj = graph.get(cur).get(i); // 인접한 정점의 번호
				if(!visited[adj]) { // 인접 정점을 방문하지 않았다면
					queue.offer(adj); // 해당 인접 정점을 큐에 넣어줌
					visited[adj] = true; // 해당 인접 정점을 방문 처리 
				}
			}
		}
	}
	
}
