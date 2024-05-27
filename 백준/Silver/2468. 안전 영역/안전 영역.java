import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 방향벡터 (상하좌우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 2차원 배열의 길이
		
		// 2차원 배열 입력 받기
		int[][] map = new int[n][n];
		int minHeight = 101; // 최소 물의 높이
		int maxHeight = 0; // 최대 물의 높이
		
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				minHeight = Math.min(map[r][c], minHeight);
				maxHeight = Math.max(map[r][c], maxHeight);
			}
		}
		
		// 현재 물에 잠기는 높이
		Queue<int[]> queue = new ArrayDeque<>();
		int max = Integer.MIN_VALUE; // 안전 영역의 최대 개수
		for(int h=minHeight-1; h<=maxHeight; h++) {
			boolean[][] visited = new boolean[n][n]; // 해당 좌표 방문 여부
			int size = 0; // 현재 높이에서의 안전 영역의 개수
			for(int r=0; r<n; r++) {
				for(int c=0; c<n; c++) {
					if(map[r][c] <= h) continue; // 물에 잠겼으면 pass
					
					// 해당 좌표 방문하지 않았다면, 큐에 넣음
					if(!visited[r][c]) {
						queue.offer(new int[] {r,c});
						visited[r][c] = true; // 방문 표시
						size++; // 안전 영역의 개수 증가
						
						while(!queue.isEmpty()) {
							int[] cur = queue.poll();
							
							for(int i=0; i<4; i++) {
								int nx = cur[0] + dx[i];
								int ny = cur[1] + dy[i];
								
								// 배열의 인덱스 넘는 경우
								if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
								
								// 아직 방문하지 않았고 물에 잠기지 않았다면, 안전 영역에 추가
								if(!visited[nx][ny] && map[nx][ny] > h) {
									queue.offer(new int[] {nx, ny});
									visited[nx][ny] = true;
								}
							}
						}
					}
					
				}
			}
			max = Math.max(max, size);
		}
		
		System.out.println(max);
		
	}
}