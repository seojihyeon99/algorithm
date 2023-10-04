import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine()); // 전체 테스트 케이스 수
		// 테스트 케이스 수만큼 반복
		for(int t=1; t<=tc; t++) {
			int n = Integer.parseInt(br.readLine()); // 학생들의 수
			int m = Integer.parseInt(br.readLine()); // 키를 비교한 횟수
			
			int[][] adjMatrix = new int[n][n]; // 그래프를 저장하는 인접행렬
			for(int i=0; i<m; i++) {
				StringTokenizer st =new StringTokenizer(br.readLine());
				int left = Integer.parseInt(st.nextToken())-1; // outVertex
				int right = Integer.parseInt(st.nextToken())-1; // inVertex
				
				adjMatrix[left][right] = 1;
			}
			
			int result = 0; // 키가 몇 번째인지 정확히 알 수 있는 학생들 수
			// 모든 정점에 대해 반복
			for(int i=0; i<n; i++) {
				boolean[] isvisited = new boolean[n]; // 정점들 방문 여부 저장
				
				// outEdge들 구함(자기보다 키가 큰 애들)
				Queue<Integer> taller = new ArrayDeque<>(); // 방문한 정점들 저장
				taller.offer(i);
				isvisited[i] = true; // 해당 정점 방문 표시
				while(!taller.isEmpty()) {
					int cur = taller.poll();
					for(int j=0; j<n; j++) {
						// 인접해 있고, 방문하지 않았으면
						if(adjMatrix[cur][j] == 1 && !isvisited[j]) {
							taller.offer(j);
							isvisited[j] = true;
						}
					}
				}
				
				// inEdge들 구함(자기보다 키가 작은 애들)
				Queue<Integer> shorter = new ArrayDeque<>(); // 방문한 정점들 저장
				shorter.offer(i);
				isvisited[i] = true; // 해당 정점 방문 표시
				while(!shorter.isEmpty()) {
					int cur = shorter.poll();
					for(int j=0; j<n; j++) {
						// 인접해 있고, 방문하지 않았으면
						if(adjMatrix[j][cur] == 1 && !isvisited[j]) {
							shorter.offer(j);
							isvisited[j] = true;
						}
					}
				}
				
				int sum = 0; // 자기와 비교 가능한 애들(작은애들 + 큰애들)의 총합
				for(int j=0; j<n; j++) {
					if(isvisited[j]) sum++;
				}
				
				if(sum == n) result++;
			}
			
			System.out.println("#" + t + " " + result);
		}
	}
	
}