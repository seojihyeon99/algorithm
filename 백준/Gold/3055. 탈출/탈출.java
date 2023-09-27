import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n; // 세로 길이
	static int m; // 가로 길이
	static char[][] map; // 지도 배열
	
	// 방향벡터(상하좌우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int time = 0;
	static boolean isfinished = false;

	public static void simulation(Queue<int[]> queue, Queue<int[]> water, int sr, int sc) {		
		while(true) {
			// 1) 물(*)이 상하좌우로 퍼짐
			while(!water.isEmpty()) {
				if(water.peek()[2] != time) {
					break;					
				}
				int[] cur = water.poll();
				for(int i=0; i<4; i++) {
					int nr = cur[0] + dx[i];
					int nc = cur[1] + dy[i];
					// 인덱스를 넘지 않고, 비버의 소굴이 아니고, 돌이 아니라면
					if(nr >=0 && nr<n && nc>=0 && nc<m && map[nr][nc] != '*' && map[nr][nc] != 'D' && map[nr][nc] != 'X') {
						map[nr][nc] = '*';
						water.offer(new int[] {nr,nc,cur[2]+1});
					}
				}				
			}
			
			boolean changed = false; // 고슴도치 이동 가능 여부
			// 2) 고슴도치 상하좌우로 이동(BFS로 이동횟수 그 이전 값에서 1 증가 시킨 값 적어줌)
			while(!queue.isEmpty()) {
				if(queue.peek()[2] != time) {
					break;					
				}
				int[] cur = queue.poll();
				for(int i=0; i<4; i++) {
					int nr = cur[0] + dx[i];
					int nc = cur[1] + dy[i];
					// 인덱스를 넘지않고, 물이 아니고, 돌이 아니라면
					if(nr >=0 && nr<n && nc>=0 && nc<m && map[nr][nc] != 'S' && map[nr][nc] != '*' && map[nr][nc] != 'X') {
						if(map[nr][nc] == 'D') { // 도착지 만났으면
//							printState();
							time++;
							isfinished = true;
							return;
						}
						map[nr][nc] = 'S';
						queue.add(new int[] {nr,nc,cur[2]+1});
						changed = true;
					}
				}
			}
			
			if(!changed) { // 고슴도치가 이동하지 못했다면 -> KAKTUS 출력 위해 종료
				return;
			}
			
//			printState();
			
			time++ ; // 1분씩 증가함 	
		}
	}
	public static void printState() {
		System.out.println("현재 시간 : " + (time+1));
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
		System.out.println("========================\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 세로 길이 
		m = Integer.parseInt(st.nextToken()); // 가로 길이
		
		int startr = -1;
		int startc = -1;
		// 지도 배열 입력받기
		map = new char[n][m];
		for(int r=0; r<n; r++) {
			String s = br.readLine();
			for(int c=0; c<m; c++) {
				map[r][c] = s.charAt(c);
				if(map[r][c] == 'S') {
					startr = r;
					startc = c;
				}
			}
		}

		Queue<int[]> queue = new ArrayDeque<>(); // 고슴도치의 마지막 이동 위치를 담는 큐
		Queue<int[]> water = new ArrayDeque<>(); // 물의 마지막 이동 위치를 담는 큐
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				if(map[r][c] == '*') {
					water.offer(new int[] {r,c,0}); // x좌표, y좌표, 현재 시간
				}
				else if(map[r][c] == 'S') {
					queue.offer(new int[] {r,c,0});
				}
			}
		}
		
		/*		
		// 1) 물(*)이 상하좌우로 퍼짐(딱 1번)
		while(!water.isEmpty()) {
			if(water.peek()[2] != time) {
				break;					
			}
			int[] cur = water.poll();
			for(int i=0; i<4; i++) {
				int nr = cur[0] + dx[i];
				int nc = cur[1] + dy[i];
				// 인덱스를 넘지 않고, 비버의 소굴이 아니고, 돌이 아니라면
				if(nr >=0 && nr<n && nc>=0 && nc<m && map[nr][nc] != 'D' && map[nr][nc] != 'X') {
					map[nr][nc] = '*';
					water.offer(new int[] {nr,nc,cur[2]+1});
				}
			}				
		}		
		*/
		
		simulation(queue, water, startr, startc);
		if(isfinished) {
			System.out.println(time);
		}
		else {
			System.out.println("KAKTUS");
		}
	}
}
