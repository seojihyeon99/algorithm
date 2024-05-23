import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 동서남북상하
	static int[][] dir = new int[][] {{0,1,0}, {0,-1,0}, {1,0,0}, {-1,0,0}, {0,0,1}, {0,0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken()); // 층 수
			int n = Integer.parseInt(st.nextToken()); // 한 층의 행의 개수
			int m = Integer.parseInt(st.nextToken()); // 한 층의 열의 개수
			
			if(h == 0 && n == 0 && m == 0) break; // 입력의 끝
			
			boolean[][][] visited = new boolean[n][m][h]; // 방문여부
			Queue<int[]> queue = new ArrayDeque<>(); // x좌표, y좌표, z좌표, 시간

			// 3차원 배열 입력받음
			char[][][] map = new char[n][m][h];
			for(int i=0; i<h; i++) {
				for(int r=0; r<n; r++) {
					String s = br.readLine();
					for(int c=0; c<m; c++) {
						map[r][c][i] = s.charAt(c);
						if(map[r][c][i] == 'S') {
							queue.add(new int[] {r, c, i, 0});
							visited[r][c][i] = true;
						}
					}
				}
				br.readLine();
			}
			
			boolean isArrived = false;
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				
				// 도착 지점에 도착했을 경우
				if(map[cur[0]][cur[1]][cur[2]] == 'E') {
					isArrived = true;
					sb.append("Escaped in " + cur[3] + " minute(s).\n");
				}
				
				for(int i=0; i<6; i++) {
					int nx = cur[0] + dir[i][0];
					int ny = cur[1] + dir[i][1];
					int nz = cur[2] + dir[i][2];
					
					// 배열의 인덱스를 벗어날 경우
					if(nx<0 || nx >=n || ny<0 || ny>=m || nz<0 || nz>=h) {
						continue;
					}
					
					// 해당 칸이 금이 아니고, 방문하지 않았을 경우
					if(map[nx][ny][nz] != '#' && !visited[nx][ny][nz]) {
						queue.add(new int[] {nx, ny, nz, cur[3] + 1});
						visited[nx][ny][nz] = true;
					}
				}
			}
			
			if(!isArrived) sb.append("Trapped!\n");
		}
		
		System.out.println(sb);
	}
}