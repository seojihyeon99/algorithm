import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] arr;
	static int n; // 배열 한변 길이
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		// 테스트 케이스 수만큼 반복
		for(int t=1; t<=tc; t++) {
			int maxGroup = 0;
			
			n = Integer.parseInt(br.readLine()); // 배열 한변 길이
			
			// 배열 생성 및 입력받기
			arr = new int[n][n];
			for(int r=0; r<n; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0; c<n; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 100일동안 반복
			int totalzero = 0;
			for(int day=0; day<=100; day++) {
				for(int r=0; r<n; r++) {
					for(int c=0; c<n; c++) {
						if(arr[r][c] == day) {
							arr[r][c] = 0;
							totalzero++;
						}
					}
				}
				boolean[][] visited = new boolean[n][n];
				
				int groupcnt = bfs(visited);
				
				if(maxGroup < groupcnt) {
					maxGroup = groupcnt;
				}
				
				if(totalzero == n*n) break;
			}
			
			sb.append("#" + t + " " + maxGroup + "\n");
		}	
		System.out.println(sb);
	}
	
	// 방향벡터(상,하,좌,우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int bfs(boolean[][] visited) {
		int groupcnt = 0;
		
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(arr[r][c] != 0 && !visited[r][c]) {
					Queue<int[]> queue = new ArrayDeque<>();
					queue.offer(new int[] {r,c});
					visited[r][c] = true;
					
					while(!queue.isEmpty()) {
						int[] cur = queue.poll();
						// 인접한 상하좌우에 대해서 반복
						for(int i=0; i<4; i++) {
							int nextx = cur[0] + dx[i];
							int nexty = cur[1] + dy[i];
							// 배열의 인덱스를 넘지않고, 방문하지 않았고, 먹은게 아니라면
							if(nextx>=0 && nextx<n && nexty>=0 && nexty<n && !visited[nextx][nexty] && arr[nextx][nexty] != 0) {
								queue.offer(new int[] {nextx,nexty});
								visited[nextx][nexty] = true;
							}
						}
					}
					
					groupcnt++;
				}
			}
		}
		
		return groupcnt;
	}
}
