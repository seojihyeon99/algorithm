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
			adjMatrix = new int[100][100];
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 입력 받는 데이터의 길이
			int start = Integer.parseInt(st.nextToken())-1; // 시작점
			
			// 인접 행렬 생성 및 입력받기
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n/2; i++) {
				int from = Integer.parseInt(st.nextToken())-1; // 전화 거는 사람
				int to = Integer.parseInt(st.nextToken())-1; // 전화 받는 사람
				adjMatrix[from][to] = 1;
			}
			
			sb.append("#" + t + " " + bfs(start) + "\n");
		}
		System.out.println(sb.toString());
		
	}
	
	static int bfs(int start) {
		boolean[] visited = new boolean[100];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {start, 0});
		visited[start] = true;
		
		int maxBreadth = 0;
		int max = 0;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int i=0; i<100; i++) {
				if(adjMatrix[cur[0]][i] == 1 && !visited[i]) {
					queue.offer(new int[] {i, cur[1]+1});
					visited[i] = true;
				}
			}
			
			if(maxBreadth < cur[1]) {
				maxBreadth = cur[1];
				max = 0;
			}
			if(max < cur[0]) max = cur[0];
		}
		
		return max+1;
	}
}
