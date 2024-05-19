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
		int n = Integer.parseInt(st.nextToken()); // 세로
		int m = Integer.parseInt(st.nextToken()); // 가로
		
		// 배열 초기화 및 입력받기
		int[][] map = new int[n][m];
		for(int r=0; r<n; r++) {
			String s = br.readLine();
			for(int c=0; c<m; c++) {
				map[r][c] = s.charAt(c) - '0';
			}
		}
		boolean[][] visited = new boolean[n][m];
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0,0,1});
		visited[0][0] = true;
		
		// 상하좌우
		int[] dx = new int[] {-1,1,0,0};
		int[] dy = new int[] {0,0,-1,1};
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			// 종료 조건 (도착 지점일 경우)
			if(cur[0] == n-1 && cur[1] == m-1) {
				cnt = cur[2];
				break;
			}
			
			// 동서남북 탐색
			for(int i=0; i<4; i++) {
				int curx = cur[0] + dx[i];
				int cury = cur[1] + dy[i];
				
				if(curx>=0 && curx<n && cury>=0 && cury<m && map[curx][cury] == 1 && !visited[curx][cury]) {
					queue.offer(new int[] {curx, cury, cur[2] + 1});
					visited[curx][cury] = true;
					
				}
			}
		}
		
		System.out.println(cnt);
		
		
	}
}