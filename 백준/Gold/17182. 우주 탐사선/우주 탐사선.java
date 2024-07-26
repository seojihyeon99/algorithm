import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {	
	static int min = Integer.MAX_VALUE; // 행성 탐사 최소시간
	static boolean[] visited; // 해당 행성의 방문여부
	static int[] result; // 방문하는 행성의 순서
	static int n; // 행성의 개수
	static int[][] graph; // 각 정점사이의 최단거리 (플로이드워셜 결과)
	
	static void perm(int idx, int sum) {
		// 백트랙킹
		if(min < sum) return;
		
		// 종료조건 (모든 행성 순서 결정)
		if(idx == n) {
			min = Math.min(min, sum);
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			result[idx] = i;
			perm(idx+1, sum + graph[result[idx-1]][i]);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 행성의 개수
		int k = Integer.parseInt(st.nextToken()); // 발사되는 행성 (출발점)
		
		graph = new int[n][n];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 플로이드워셜 : 모든쌍의 최단거리 구함. 시간복잡도(N^3)
		for(int t=0; t<=n-1; t++) { // 중간 경유지 (i,j)보다 (i,t) + (t,j)가 더 빠르면 업데이트
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(graph[i][j] > graph[i][t] + graph[t][j]) {
						graph[i][j] = graph[i][t] + graph[t][j];
					}
				}
			}
		}
		
		visited = new boolean[n];
		result = new int[n];
		
		// 출발점
		result[0] = k;
		visited[k] = true;
		
		perm(1, 0); // 현재 결정된 자릿수
		
		System.out.println(min);
	}
}