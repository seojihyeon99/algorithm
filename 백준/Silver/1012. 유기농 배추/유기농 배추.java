import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int m; // 배추밭 가로 길이
	static int n; // 배추밭 세로 길이
	static int k; // 배추 개수
	static boolean[][] map; // 배추밭
	static boolean[][] visited; // 방문체크
	// 방향벡터(상,하,좌,우)
	static int[] dx = new int[]{-1,1,0,0};
	static int[] dy = new int[]{0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		// 테스트 케이스 수만큼 반복
		for(int i=0; i<t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			map = new boolean[n][m];
			visited = new boolean[n][m];
			
			// 배추 개수만큼 반복하며 배추밭에 저장
			for(int j=0; j<k; j++) {
				st = new StringTokenizer(br.readLine());
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			}
			
			int count = 0;
			Queue<int[]> queue = new ArrayDeque<>();
			// 배추밭에 지렁이를 뿌리러 출동!!!
			for(int r=0; r<n; r++) {
				for(int c=0; c<m; c++) {
					// 방문하지 않았다면
					if(!visited[r][c] && map[r][c]==true) {
						queue.offer(new int[]{r,c});
						visited[r][c] = true;
						while(!queue.isEmpty()) {
							int[] cur = queue.poll();
							for(int k=0; k<4; k++) {
								int x = cur[0] + dx[k];
								int y = cur[1] + dy[k];
								if(x>=0 && x<n && y>=0 && y<m && !visited[x][y] && map[x][y]==true) {
									queue.offer(new int[] {x,y});
									visited[x][y] = true;
								}
							}
						}
						count++;
					}
				}
			}
			
			System.out.println(count);
		}
	}
}
