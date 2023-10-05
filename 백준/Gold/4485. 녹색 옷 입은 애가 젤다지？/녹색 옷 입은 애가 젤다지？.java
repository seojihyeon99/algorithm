import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n; // 동굴의 크기
	static int[][] map; // 동굴 2차원 배열
	static int[][] sumMap; // 잃는 금액 누적 배열
	static boolean[][] visited; // 해당 좌표 방문했는지
	
	//static int min; // 최소 잃는 금액
	// 방향벡터(상하좌우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0,0});
		sumMap[0][0] = map[0][0];
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for(int i=0; i<4; i++) {
				int x = cur[0] + dx[i]; // 이동할 x좌표
				int y = cur[1] + dy[i]; // 이동할 y좌표
				// 배열의 인덱스 넘지 않고, 방문하지 않았으면
				if(x>=0 && x<n && y>=0 && y<n) {
					int temp = sumMap[cur[0]][cur[1]] + map[x][y];
					if(sumMap[x][y] > temp) {
						sumMap[x][y] = temp;
						queue.offer(new int[] {x,y});
					}
				}
			}
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; ;t++) {
			n = Integer.parseInt(br.readLine()); // 동굴의 크기
			// 0인 입력 받으면 종료
			if(n == 0) break;
			
			// 동굴 2차원 배열 생성 및 입력받기
			map = new int[n][n];
			for(int r=0; r<n; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0; c<n; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			//min = 0; // 최소 잃는 금액 초기화
			sumMap = new int[n][n]; // 누적 잃는 금액 저장
			for(int r=0; r<n; r++) {
				Arrays.fill(sumMap[r], Integer.MAX_VALUE);
			}
			
			visited = new boolean[n][n];  // 해당 좌표 방문했는지 저장
			
			bfs();
			
			// 출력위해 저장
			sb.append("Problem " + t + ": " + sumMap[n-1][n-1] + "\n");
			
			//printState(map);
			//printState(sumMap);
			
		}
		
		System.out.println(sb.toString());
	}
	
}
