import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n; // 지도의 크기
	static int[][] map; // 2차원 지도
	static int[][] group; // 그룹번호
	
	// 방향벡터(상하좌우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		// 2차원 지도 생성 및 입력받기
		map = new int[n][n]; // x좌표, y좌표, 그룹번호
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 현재 그룹 번호 지정하기
		group = new int[n][n];
		// 초기 섬들 좌표 입력 받음
		Queue<int[]> queue = new ArrayDeque<>(); // 처음 그룹 번호 지정을 위한 큐
		Queue<int[]> start = new ArrayDeque<>(); // 현재 주목하는 섬들 좌표 저장
		int idx = 1; // 현재 그룹 번호
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(map[r][c] == 1 && group[r][c] == 0) {
					queue.offer(new int[] {r,c,1}); // r:x좌표, c:y좌표, 1:이동거리
					start.offer(new int[] {r,c,1}); // r:x좌표, c:y좌표, 1:이동거리
					group[r][c] = idx;
					// 여기서부터 bfs 시작
					while(!queue.isEmpty()) {
						int[] cur = queue.poll();
						for(int i=0; i<4; i++) {
							int x = cur[0] + dx[i];
							int y = cur[1] + dy[i];
							// 배열의 인덱스 넘지 않고
							if(x>=0 && x<n && y>=0 && y<n) {
								// 현재 방문한 적이 없다면
								if(map[x][y] == 1 && group[x][y] == 0) {
									queue.offer(new int[] {x,y,1});
									start.offer(new int[] {x,y,1});
									group[x][y] = idx;
								}
							}
						}
					}
					idx++;
				}
			}
		}
		
		// 여기서 부터 한개씩 늘려가는 bfs 시작
		int result = Integer.MAX_VALUE;
		while(!start.isEmpty()) {
			int[] cur = start.poll();
			for(int i=0; i<4; i++) {
				int x = cur[0] + dx[i];
				int y = cur[1] + dy[i];
				// 배열의 인덱스 넘지 않고
				if(x>=0 && x<n && y>=0 && y<n) {
					// 현재 방문한 적이 없다면
					if(map[x][y] == 0) {
						map[x][y] = cur[2]+1;
						start.offer(new int[] {x,y,cur[2]+1});
						group[x][y] = group[cur[0]][cur[1]];
					}
					// 다른 섬에 방문했다면(짝수개의 다리 사용)
					else if(map[x][y] == cur[2] && group[x][y] != group[cur[0]][cur[1]]){
						int min = 2*((cur[2]+1)-2);
						if(result > min) result = min;
						break;
					}
					// 다른 섬에 방문했다면(홀수개의 다리 사용)
					else if(map[x][y] == cur[2]+1 && group[x][y] != group[cur[0]][cur[1]]) {
						int min = 2*((cur[2]+1)-2)+1;							
						if(result > min) result = min;
						break;
					}
				}
			}
		}

		System.out.println(result);
	}

}
