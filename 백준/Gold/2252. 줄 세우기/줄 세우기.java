/**
 * [아이디어]
 * 위상정렬
 * DAG(Directed Acyclic Graph)
 * 정정 대비 간선수 작으므로 -> 인접리스트 이용
 * 
 * 처음에 boolean[] visited = new boolean[n+1]; 배열 만들어줬는데.. 생각해보니 필요 x
 * 이미 진입차수가 0이어서 방문했으면 -> 다음번에 인접정점이될경우에는 또 -1해서 -1,-2,...되기때문
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 학생수
		int m = Integer.parseInt(st.nextToken()); // 키 비교횟수
		
		// 인접리스트 생성
		List<Integer>[] list = new List[n+1];
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}

		int[] indegree = new int[n+1]; // 진입차수 저장
		
		for(int i=0; i<m; i++) { // 비교횟수만큼 반복
			st = new StringTokenizer(br.readLine());
			int prev = Integer.parseInt(st.nextToken()); // 선행되어야하는 애
			int next = Integer.parseInt(st.nextToken()); // 후에 방문해도되는 애
			indegree[next]++; // 진입차수 1 증가
			list[prev].add(next); // 인접리스트에 넣어줌
		}
				
		// 시작점들을 큐에 넣어줌
		Queue<Integer> queue = new ArrayDeque<>();

		// 진입차수가 0인애들 모두 큐에 넣음
		for(int i=1; i<=n; i++) {
			if(indegree[i] == 0) { // 진입차수가 0이면
				queue.offer(i); // 큐에 넣고
			}
		}
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {			
			int cur = queue.poll(); // 큐에서 하나 꺼내서
			sb.append(cur + " "); // 출력
				
			for(int v : list[cur]) { // 인접한 정점
				indegree[v]--; // 진입차수 1 감소
				if(indegree[v] == 0) { // 인접한 정점의 진입차수가 0이되었다면
					queue.offer(v); // 큐에 넣어줌
				}
			}				
		}
		
		System.out.println(sb.toString());
	
	}
}
