import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 상하좌우
	static int[] dx = new int[]{-1,1,0,0};
	static int[] dy = new int[]{0,0,-1,1};
	
	static int row;
	static int col;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken()); // 행의 개수
		col = Integer.parseInt(st.nextToken()); // 열의 개수
		
		Queue<int[]> jihoon = new ArrayDeque<>(); // 지훈의 x좌표, y좌표, 시간
		Queue<int[]> fire = new ArrayDeque<>(); // 불의 x좌표, y좌표, 시간
		
		// 맵 입력 및 초기화
		char[][] map = new char[row][col];
		for(int r=0; r<row; r++) {
			String s = br.readLine();
			for(int c=0; c<col; c++) {
				map[r][c] = s.charAt(c);
				
				if(map[r][c] == 'J') jihoon.add(new int[] {r,c,0});
				else if(map[r][c] == 'F') fire.add(new int[] {r,c,0});
			}
		}
		
		boolean isEscaped = false; // 탈출여부
		int time = 0; // 현재 시간
		while(!jihoon.isEmpty()) {
			time++; // 1분 증가
			
			// 지훈 이동
			if(moveJihoon(map, jihoon)) {
				isEscaped = true;
				break;
			}
			
			// 불 이동
			moveFire(map, fire);
		}
		
		if(isEscaped) System.out.println(time);
		else System.out.println("IMPOSSIBLE");
		
	}
	
	static void moveFire(char[][]map, Queue<int[]> fire) {
		int len = fire.size();
		
		for(int i=0; i<len; i++) {
			int[] cur = fire.poll();
			
			// 인접한 칸으로 이동
			for(int j=0; j<4; j++) {
				int nextx = cur[0] + dx[j];
				int nexty = cur[1] + dy[j];
				
				// 경계를 넘지 않고, 벽이 아니라면
				if(nextx>=0 && nextx<row  && nexty>=0 && nexty<col && map[nextx][nexty] != '#' && map[nextx][nexty] != 'F') {
					map[nextx][nexty] = 'F';
					fire.add(new int[] {nextx, nexty, cur[2]+1}); // 큐에 불 위치 넣음
				}
				
			}
		}
	}
	
	static boolean moveJihoon(char[][] map, Queue<int[]> jihoon) {
		int len = jihoon.size();
		
		for(int i=0; i<len; i++) {
			int[] cur = jihoon.poll();
			
			// 해당 좌표가 불탔으면, 패스
			if(map[cur[0]][cur[1]] == 'F') {
				continue;
			}
			
			// 탈출 가능 여부 확인
			if(cur[0] == 0 || cur[0] == row-1 || cur[1] == 0 || cur[1] == col-1) {
				return true;
			}

			// 인접한 칸으로 이동
			for(int j=0; j<4; j++) {
				int nextx = cur[0] + dx[j];
				int nexty = cur[1] + dy[j];
				
				// 경계를 넘지 않고, 지나갈 수 있는 공간이면
				if(nextx>=0 && nextx<row && nexty>=0 && nexty<col && map[nextx][nexty] == '.') {
					map[nextx][nexty] = 'J';
					jihoon.add(new int[] {nextx, nexty, cur[2]+1}); // 큐에 지훈 위치 넣음
				}
			}
		}
		
		return false;
	}
}