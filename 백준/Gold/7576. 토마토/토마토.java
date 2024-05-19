import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()); // 가로 길이
		int n = Integer.parseInt(st.nextToken()); // 세로 길이
		
		Queue<int[]> queue = new ArrayDeque<>(); // x좌표, y좌표, 날
		int ntomato = 0; // 총 토마토의 개수
		int nbaked = 0; // 익은 토마토의 개수
		
		// 2차원 배열에 입력 받기
		int[][] map = new int[n][m];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				int num = Integer.parseInt(st.nextToken());
				map[r][c] = num;
				
				if(num == 1) {
					queue.offer(new int[] {r,c,0}); // 익은 토마토라면, 큐에 넣음
					nbaked++; // 익은 토마토의 개수 증가
				}
				
				if(num != -1) ntomato++; // 토마토가 있다면, 개수 증가
			}
		}
		
		// 상하좌우
		int[] dx = new int[] {-1,1,0,0};
		int[] dy = new int[] {0,0,-1,1};
		
		int day = 0; // 현재 경과한 날
		while(!queue.isEmpty()) {

			if(ntomato == nbaked) break;
			
			int[] cur = queue.poll();
			if(cur[2] > day) day++; // 하루 증가
			
			for(int i=0; i<4; i++) {
				int nextx = cur[0] + dx[i];
				int nexty = cur[1] + dy[i];
				
				if(nextx>=0 && nextx<n && nexty>=0 && nexty<m && map[nextx][nexty] == 0) {
					map[nextx][nexty] = 1; // 인접한 익지 않은 토마토가 익음
					nbaked++; // 익은 토마토의 수 증가
					queue.offer(new int[] {nextx, nexty, day+1});
					
					if(ntomato == nbaked) { // 해당 토마토를 익혔을때 모든 토마토가 다 익었다면, day 1 증가
						day++;
						break;
					}
				}
			}
			
		}
		
		if(ntomato == nbaked) 
			System.out.println(day);
		else
			System.out.println(-1);
	}
}