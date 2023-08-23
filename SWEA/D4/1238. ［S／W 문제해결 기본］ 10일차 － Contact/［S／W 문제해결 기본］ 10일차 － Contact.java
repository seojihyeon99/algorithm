import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] adjMatrix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 테스트케이스 10번 반복
		for(int t=1; t<=10; t++) {
			adjMatrix = new int[100][100]; // 인접행렬 생성
			
			// 첫째줄 입력받기
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 입력 받는 데이터의 길이
			int start = Integer.parseInt(st.nextToken())-1; // 시작점. 정점 0부터 시작
			
			// 둘째줄 입력받기
			// 인접 행렬 생성 및 입력받기
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n/2; i++) {
				int from = Integer.parseInt(st.nextToken())-1; // 전화 거는 사람. 정점 0부터 시작
				int to = Integer.parseInt(st.nextToken())-1; // 전화 받는 사람. 정점 0부터 시작
				adjMatrix[from][to] = 1; // 해당 방향 간선 존재 표시
			}
			
			sb.append("#" + t + " " + bfs(start) + "\n");
		}
		System.out.println(sb.toString());
		
	}
	
	// bfs 
	static int bfs(int start) { // start : 탐색 시작 정점
		boolean[] visited = new boolean[100]; // 방문여부 저장
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {start, 0}); // 큐에 시작 정점 넣음
		visited[start] = true; // 시작 정점 방문 체크
		
		int maxBreadth = 0; // 현재 최대 너비
		int max = 0; // 마지막으로 연락 받은 사람중 가장 숫자 큰 사람
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			int[] cur = queue.poll(); // 큐에서 하나 꺼내서
			// 인접행렬에서 해당 행의 열의수(100)만큼 반복하며 탐색
			for(int i=0; i<100; i++) {
				if(adjMatrix[cur[0]][i] == 1 && !visited[i]) { // 해당 방향 간선 존재하고, 방문하지 않았으면 
					queue.offer(new int[] {i, cur[1]+1}); // 큐에 넣어줌(이때 breadth 1 증가)
					visited[i] = true; // 방문 체크
				}
			}
			
			if(maxBreadth < cur[1]) { // 현재 최대 너비 보다 크면
				maxBreadth = cur[1]; // 최대 너비를 업데이트
				max = 0; // 현재 최대 너비의 가장 숫자 큰 사람 초기화
			}
			
			if(max < cur[0]) max = cur[0]; // 더 숫자 큰 사람 있으면, 업데이트
		}
		
		return max+1; // 정점 0부터 시작했었으므로 +1
	}
}
