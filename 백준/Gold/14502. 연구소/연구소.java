import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n; // 세로 크기
	static int m; // 가로 크기
	static int[][] map;
	static int[] selected = new int[3]; // 세운 벽의 인덱스 저장
	static int max = Integer.MIN_VALUE;
	
	// 상하좌우
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void comb(int start, int count) {
		// 기저조건 : 3개의 벽을 다 세웠을 때
		if(count== 3) {
			int[][] temp = new int[n][m];
			for(int i=0; i<n; i++) {
				temp[i] = Arrays.copyOf(map[i], m);
			}
			
			for(int i=0; i<3; i++) {
				int num = selected[i];
				temp[num/m][num%m] = 1;
			}
			
			for(int r=0; r<n; r++) {
				for(int c=0; c<m; c++) {
					if(temp[r][c] == 2) {
						//bfs 시작
						Queue<int[]> queue = new ArrayDeque<>();
						queue.offer(new int[] {r,c});
						
						while(!queue.isEmpty()) {
							int[] cur = queue.poll();
							for(int i=0; i<4; i++) {
								int x = cur[0] + dx[i];
								int y = cur[1] + dy[i];
								
								// 배열의 인덱스 넘지 않고, 해당 좌표 0이라면
								if(x>=0 && x<n && y>=0 && y<m && temp[x][y] == 0) {
									temp[x][y] = 2;
									queue.offer(new int[] {x,y}); // 해당 좌표 큐에 넣음
								}
							}
						}
					}
				}
			}
			
			int sum = 0;
			for(int r=0; r<n; r++) {
				for(int c=0; c<m; c++) {
					if(temp[r][c] == 0) sum++;
				}
			}
			
			if(max < sum) max = sum;
			return;
		}
		 
		
		for(int i=start; i<n*m; i++) { // 인덱스 0은 쓰지 x
			if(map[i/m][i%m] == 0) {
				selected[count] = i;
				comb(i+1, count+1);				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 세로 크기
		m = Integer.parseInt(st.nextToken()); // 가로 크기
		
		// 맵 생성 및 입력 받기
		map = new int[n][m];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0, 0);
		
		System.out.println(max);
		
	}
	
}
