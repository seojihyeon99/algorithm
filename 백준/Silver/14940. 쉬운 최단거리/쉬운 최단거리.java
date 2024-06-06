import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	// 방향벡터 (상하좌우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 세로 길이
		int m = Integer.parseInt(st.nextToken()); // 가로 길이
		
		int[][] origin = new int[n][m];
		int[][] dist = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		
		Queue<int[]> queue = new ArrayDeque<>(); // x좌표, y좌표, 거리
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				origin[r][c] = Integer.parseInt(st.nextToken());
				
				if(origin[r][c] == 2) {
					queue.offer(new int[] {r, c, 0});
					visited[r][c] = true;
				} else if(origin[r][c] == 1) {
					dist[r][c] = -1;
				}
			}
		}
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				int nd = cur[2] + 1;
				
				// 배열의 인덱스 넘는 경우 pass
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				
				if(!visited[nx][ny] && origin[nx][ny] == 1) {
					queue.offer(new int[] {nx, ny, nd});
					visited[nx][ny] = true;
					dist[nx][ny] = nd;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				sb.append(dist[r][c] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	
}