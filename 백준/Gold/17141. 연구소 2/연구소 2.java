import java.io.*;
import java.util.*;

class Main {
	static int n; // 연구소의 크기
	static int m; // 바이러스의 개수
	static int[][] map; // 연구소의 상태
	static List<int[]> virus = new ArrayList<>(); // 바이러스 놓을 수 있는 지점
	static boolean[] choose = new boolean[10]; // 해당 바이러스 지점 선택 여부
	static int result = Integer.MAX_VALUE;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	// 연구소 모든 지점 방문 여부
	static boolean isAllVisited(boolean[][] visited) {
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				// 방문하지 않았고, 벽이 아니라면
				if(!visited[r][c] && map[r][c] != 1) return false;
			}
		}
		return true;
	}
	
	// 바이러스 퍼지는 시뮬레이션
	static void simulation() {
		boolean[][] visited = new boolean[n][n];
		Queue<int[]> queue = new ArrayDeque<int[]>();

		// 초기 바이러스의 위치
		int time = 0; // 모두 퍼지는데 걸린 시간
		for(int i=0; i<virus.size(); i++) {
			if(choose[i]) {
				int[] cur = virus.get(i);
				queue.offer(new int[] {cur[0], cur[1], 0}); // x좌표, y좌표, 걸린시간
				visited[cur[0]][cur[1]] = true;
			}
		}
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			if(time != cur[2]) {
				time = cur[2];
			}
			
			for(int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
				
				// 방문한 적 없고 벽이 아니라면
				if(!visited[nx][ny] && map[nx][ny] != 1) {
					queue.offer(new int[] {nx, ny, cur[2] + 1});
					visited[nx][ny] = true;
				}
			}
		}
		
		// 모두 방문했는지 체크
		if(isAllVisited(visited)) {
			result = Math.min(time, result);
		}
	}
	
	// 바이러스 놓을 수 있는 지점 택
	static void comb(int idx, int cnt) { // idx : 현재 보기 시작하는 인덱스, cnt : 현재 골라진 바이러스 지점 개수
		if(cnt == m) {
			// 바이러스 퍼지는 시뮬레이션 시작
			simulation();
			
			return;
		}
		
		for(int i=idx; i<virus.size(); i++) {
			choose[i] = true;
			comb(i+1, cnt+1);
			choose[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 연구소의 크기
		m = Integer.parseInt(st.nextToken()); // 바이러스의 개수
		
		// 연구소의 상태 입력
		map = new int[n][n];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				// 바이러스를 놓을 수 있는 지점이라면
				if(map[r][c] == 2) {
					virus.add(new int[] {r,c});
				}
			}
		}
		
		comb(0,0);
		
		// 바이러스 전체 퍼질수 없는 경우 -1 출력
		if(result == Integer.MAX_VALUE) result = -1;
		
		System.out.println(result);
	}
}