/**
 * [아이디어]
 * 위상정렬
 * DAG(Directed Acyclic Graph)
 * 정정 대비 간선수 작으므로 -> 인접리스트 이용
 * 
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
//		List<List<Integer>> list1 = new ArrayList<>();
//		for(int i=0; i<n+1; i++) {
//			list.add(new ArrayList<>());
//		}
		List<Integer>[] list = new List[n+1];
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}

		boolean[] visited = new boolean[n+1]; // 방문여부 저장
		int[] indegree = new int[n+1]; // 진입차수 저장
		
		for(int i=0; i<m; i++) { // 비교횟수만큼 반복
			st = new StringTokenizer(br.readLine());
			int prev = Integer.parseInt(st.nextToken()); // 선행되어야하는애
			int next = Integer.parseInt(st.nextToken()); // 그 다음 방문
			indegree[next]++; // 진입차수 1 증가
//			list.get(prev).add(next);
			list[prev].add(next);
		}
				
		// 시작점들을 큐에 넣어줌
		Queue<Integer> queue = new ArrayDeque<>();

		// 진입차수가 0인애들 모두 큐에 넣음
		for(int i=1; i<=n; i++) {
			if(indegree[i] == 0 && !visited[i]) {
				queue.offer(i);
				visited[i] = true;
			}
		}
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {			
			int size = queue.size();
			for(int i=0; i<size; i++) {
				int cur = queue.poll();
				sb.append(cur + " ");
				
//				for(int v : list.get(cur)) {
				for(int v : list[cur]) {
					indegree[v]--;
					if(indegree[v] == 0 && !visited[v]) {
						queue.offer(v);
						visited[v] = true;
					}
				}				
			}
			
//			// 진입차수가 0인애들 모두 큐에 넣음
//			for(int i=1; i<=n; i++) {
//				if(indegree[i] == 0 && !visited[i]) {
//					queue.offer(i);
//					visited[i] = true;
//				}
//			}
		}
		
		System.out.println(sb.toString());
	
	}
}
