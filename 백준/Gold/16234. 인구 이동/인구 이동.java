import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n; // 땅의 한변의 크기
	static int left; // 인구차이의 하한
	static int right; // 인구차이의 상한
	static int[][] map;
	// 방향벡터(상,하,좌,우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 땅의 한변의 크기
		left = Integer.parseInt(st.nextToken()); // 하한
		right = Integer.parseInt(st.nextToken()); // 상한

		// 맵 정보 입력받기
		map = new int[n][n];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int day = 0;
		while(true) {
			boolean changed = false;
			boolean[][] visited = new boolean[n][n]; // 해당 나라의 방문 여부 초기화
			Queue<int[]> queue = new ArrayDeque<>(); // BFS를 위해 연합들의 좌표(x, y) 저장
			
			for(int r=0; r<n; r++) {
				for(int c=0; c<n; c++) {
					// 해당 나라를 방문하지 않았으면
					if(!visited[r][c]) {
						ArrayList<int[]> list = new ArrayList<>(); // 연합들의 인구 평균으로 업데이트 위해 연합들의 좌표(x,y) 저장
						int sum = 0; // 연합의 인구수
						int cnt = 0; // 연합을 이루고 있는 칸의 개수

						// BFS 수행
						sum += map[r][c];
						cnt++;
						queue.add(new int[] {r,c});
						list.add(new int[] {r,c});
						visited[r][c] = true;
						while(!queue.isEmpty()) {
							int[] cur = queue.poll(); // 현재 보는 좌표
							
							for(int i=0; i<4; i++) {
								int x = cur[0] + dx[i];
								int y = cur[1] + dy[i];
								// 배열의 인덱스 넘지않고, 아직 해당 나라를 방문하지 않았고
								if(x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]) {
									int diff = map[cur[0]][cur[1]] - map[x][y]; // 두 나라의 인구 차이
									if(Math.abs(diff) >= left && Math.abs(diff) <= right) { // 인구차이가 L이상 R이하이면 => 국경선을 엶
										sum += map[x][y];
										queue.add(new int[] {x,y});
										list.add(new int[] {x,y});
										visited[x][y] = true;
										cnt++;
									}
								}
							}
						}
						
						// 연합을 이루는 각 칸의 인구수는 평균으로 업데이트됨
						for(int i=0; i<list.size(); i++) {
							int[] cur = list.get(i);
							map[cur[0]][cur[1]] = sum/cnt;
						}
						
						if(list.size() > 1) changed = true; // 인구 이동이 발생하지 않음
					}
				}
			}
			
			if(!changed) break; // 인구 이동이 발생하지 않으면 종료
			
			day++; // 날짜 증가
		}
		
		System.out.println(day);
	}
}
