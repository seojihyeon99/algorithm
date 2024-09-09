import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Direction {
	int[] d1;
	int[] d2;
	
	public Direction(int[] d1, int[]d2) {
		this.d1 = d1;
		this.d2 = d2;
	}
}

class Main {
	static Direction[] dir = new Direction[4];
	static int n; // 세로 크기
	static int m; // 가로 크기
	static int[][] map; // 나무 재료의 강도 정보
	static boolean[][] visited; // 해당 나무 재료 사용 여부
	static int max = 0;
	
	static void solve(int idx, int sum) { // idx : 현재 보는 좌표 지점, sum : 강도의 합
		if(idx == n*m) {
			max = Math.max(max, sum);
			return;
		}
		
		int r = idx/m;
		int c = idx%m;
		
		// 해당 지점의 나무재료 사용 o
		for(int i=0; i<4; i++) {
			if(!visited[r][c]) {
				int nx1 = r + dir[i].d1[0];
				int ny1 = c + dir[i].d1[1];
				if(nx1 < 0 || nx1 >= n || ny1 < 0 || ny1 >= m || visited[nx1][ny1]) continue;
				
				int nx2 = r + dir[i].d2[0];
				int ny2 = c + dir[i].d2[1];
				if(nx2 < 0 || nx2 >= n || ny2 < 0 || ny2 >= m || visited[nx2][ny2]) continue;
				
				// 부메랑 만들 수 있다면
				visited[nx1][ny1] = true;
				visited[nx2][ny2] = true;
				visited[r][c] = true;
				solve(idx+1, sum + (2*map[r][c] + map[nx1][ny1] + map[nx2][ny2]));
				visited[nx1][ny1] = false;
				visited[nx2][ny2] = false;
				visited[r][c] = false;
			}
		}
		
		// 해당 지점의 나무재료 사용 x
		solve(idx+1, sum);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 세로 크기
		m = Integer.parseInt(st.nextToken()); // 가로 크기
		
		visited = new boolean[n][m];
		// 나무 재료의 강도 정보 입력
		map = new int[n][m];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dir[0] = new Direction(new int[] {-1,0}, new int[]{0,-1}); 
		dir[1] = new Direction(new int[]{-1,0}, new int[]{0,1}); 
		dir[2] = new Direction(new int[]{1,0}, new int[]{0,-1}); 
		dir[3] = new Direction(new int[]{1,0}, new int[]{0,1});
		
		solve(0, 0);
		
		System.out.println(max);
	}
}