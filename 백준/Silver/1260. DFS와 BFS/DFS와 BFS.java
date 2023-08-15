import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); //그래프 정보
	public static boolean[] dvisited; // dfs에서 방문했는지 여부 저장
	public static boolean[] bvisited; // bfs에서 방문했는지 여부 저장
	
	public static void dfs(int x) {
		dvisited[x] = true; // 현재 노드 방문처리
		System.out.print(x + " "); // 현재 방문한 노드 출력

		Collections.sort(graph.get(x)); // 정점 번호가 작은것 먼저 방문하므로, 오름차순 정렬
		// 현재 노드의 인접 노드를 하나씩 꺼냄 => y
		for (int i = 0; i < graph.get(x).size(); i++) {
			int y = graph.get(x).get(i);
			// 방문하지 않았다면 해당 노드 방문
			if (!dvisited[y]) {
				dfs(y);
			}
		}
	}
	
	public static void bfs(int x) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(x); //큐에 시작 노드 넣음
		bvisited[x] = true; //시작 노드 방문 처리
		
		Collections.sort(graph.get(x)); // 정점 번호가 작은것 먼저 방문하므로, 오름차순 정렬
		//큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			int v = queue.poll(); //큐에서 노드 하나 꺼냄
			System.out.print(v + " ");
			// 현재 노드의 인접 노드를 하나씩 꺼냄 => y
			for(int y : graph.get(v)) {
				// 방문하지 않았다면 해당 노드 인큐
				if(!bvisited[y]) {
					queue.offer(y);
					bvisited[y] = true; //해당 노드 방문 처리
				}
			}
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);

		int n = Integer.parseInt(st.nextToken()); // 정점의 개수
		int m = Integer.parseInt(st.nextToken()); // 간선의 개수
		int v = Integer.parseInt(st.nextToken()); // 시작 노드

		dvisited = new boolean[n + 1]; // dfs에서 방문여부 체크. 인덱스 0은 사용하지 x
		bvisited = new boolean[n + 1]; // bfs에서 방문여부 체크. 인덱스 0은 사용하지 x

		// 그래프 초기화
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<Integer>());
		}

		// 간선의 개수 m만큼 돌면서, 각 정점에 인접한 노드 정보 저장
		for (int i = 0; i < m; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());

			graph.get(n1).add(n2);
			graph.get(n2).add(n1);
		}
		
		dfs(v); // 시작 노드를 전달하며 dfs 호출
		System.out.println();
		bfs(v); // 시작 노드를 전달하며 bfs 호출
	}

}
