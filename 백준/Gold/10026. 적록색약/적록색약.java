import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	// 방향벡터(상,하,좌,우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static char[][] arr;
	static int n;
	
	static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine()); // 배열의 크기
		
		// 배열 생성 및 입력받기
		arr = new char[n][n];
		for(int r=0; r<n; r++) {
			String s = br.readLine();
			for(int c=0; c<n; c++) {
				arr[r][c] = s.charAt(c);
			}
		}
		
		int total1 = 0;
		visited = new boolean[n][n]; // 방문했는지 여부	
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(!visited[r][c]) {
					dfs(r,c,arr[r][c]);
					total1++;
				}
			}
		}
		
		int total2 = 0;
		visited = new boolean[n][n]; // 방문했는지 여부	
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(!visited[r][c]) {
					dfs2(r,c,arr[r][c]);
					total2++;
				}
			}
		}		
		System.out.println(total1 + " " + total2);

	}
	
	static void dfs(int r, int c, char color) {
		for(int i=0; i<4; i++) {
			int nextx = r + dx[i];
			int nexty = c + dy[i];
			if(nextx >=0 && nextx<n && nexty>=0 && nexty<n && !visited[nextx][nexty]) {
				if(arr[r][c] == arr[nextx][nexty]) { // 색깔 같다면
					visited[nextx][nexty] = true;
					dfs(nextx, nexty, color);
				}
			}
		}
	}
	
	static void dfs2(int r, int c, char color) {
		for(int i=0; i<4; i++) {
			int nextx = r + dx[i];
			int nexty = c + dy[i];
			if(nextx >=0 && nextx<n && nexty>=0 && nexty<n && !visited[nextx][nexty]) {
				if(arr[r][c] == 'B' && arr[nextx][nexty]!='B') { // B인데 색깔 같지않다면
					continue;
				}
				else if((arr[r][c] == 'R' || arr[r][c] == 'G') && arr[nextx][nexty] == 'B') { // R또는 G인데 B라면
					continue;
				}
				visited[nextx][nexty] = true;
				dfs2(nextx, nexty, color);
			}
		}
	}
}
