import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 청소: -1, 청소x: 0, 벽:1
 */
public class Main {
	
	// 방향벡터 (북:0, 동:1, 남:2, 서:3)
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 세로 길이
		int m = Integer.parseInt(st.nextToken()); // 가로 길이
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()); // 처음 로봇청소기의 x좌표
		int y = Integer.parseInt(st.nextToken()); // 처음 로봇청소기의 y좌표
		int dir = Integer.parseInt(st.nextToken()); // 처음 로봇청소기의 방향
		
		int[][] map = new int[n][m];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0; // 현재까지 청소한 칸의 수
		while(true) {
			// 현재 칸 청소되어 있지 않으면 => 청소
			if(map[x][y] == 0) {
				map[x][y] = -1;
				cnt++; // 청소한 칸의 수 증가
			}
			
			boolean isExist = false; // 인접한 칸 중에서 청소되지 않은 칸 존재여부
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// 배열의 인덱스 넘지 않고, 청소되지 않은 칸 있으면
				if(nx>=0 && nx<n && ny>=0 && ny<m && map[nx][ny] == 0) {
					isExist = true;
					break;
				}
			}
			
			// 인접한 칸 중, 청소되지 않은 칸이 존재하지 않는 경우
			if(!isExist) {
				// 바라보는 방향 유지한 채, 한 칸 후진할 수 있다면 후진
				// 0->2, 2->0, 1->3, 3->1 방향으로 => (dir+2)%4
				int ndir = (dir+2)%4; // 후진 방향
				int nx = x + dx[ndir];
				int ny = y + dy[ndir];
				
				// 후진할 수 있다면 (배열의 인덱스 넘지 않고, 벽이 아니라면) => 후진
				if(nx>=0 && nx<n && ny>=0 && ny<m && map[nx][ny] != 1) {
					x = nx;
					y = ny;
					continue; // 다시 1번으로
				} 
				// 후진할 수 없다면 => 작동 중지
				else {
					break;
				}
				
			}
			// 인접한 칸 중, 청소되지 않은 칸 존재하는 경우
			else {
				// 반시계 방향으로 90도 회전
				// 3->2, 2->1, 1->0, 0->3 => (4 + (dir-1)) % 4
				dir = (4 + (dir-1)) % 4;
				
				// 앞쪽 칸이 청소되지 않은 빈칸이면 => 1칸 전진
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(nx>=0 && nx<n && ny>=0 && ny<m && map[nx][ny] == 0) {
					x = nx;
					y = ny;
				}
			}
		}
		
		System.out.println(cnt);
	}
}