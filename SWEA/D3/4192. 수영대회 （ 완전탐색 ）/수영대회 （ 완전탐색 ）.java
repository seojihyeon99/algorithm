import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] arr; // 수영장 맵
	static boolean[][] visited; // 방문여부 체크
	static int n; // 수영장의 크기
	static int startx, starty, finishx, finishy; // 시작 및 도착 좌표
	static boolean isfinished;
	
	// 방향벡터 (상, 하, 좌, 우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	// 나는야 수영시작~~
	static void swimStart(int row, int col, int sec) {
		Queue<int[]> queue = new LinkedList<>();	
		queue.offer(new int[]{row,col,sec});
		visited[row][col] = true;
		
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int curx = temp[0];
			int cury = temp[1];
			int cursec = temp[2];
			
			if(curx==finishx && cury==finishy) {
				isfinished = true;
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nextx = curx + dx[i];
				int nexty = cury + dy[i];
				
				if(nextx>=0 && nextx<n && nexty>=0 && nexty<n) {
					if(arr[nextx][nexty] == 0 && !visited[nextx][nexty]) {
						arr[nextx][nexty] = cursec+1;
						visited[nextx][nexty] = true;
						queue.add(new int[]{nextx, nexty, cursec+1});
					}
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int tcase = Integer.parseInt(br.readLine());
		
		
		// 테스트 케이스 수만큼 반복
		for(int t=1; t<=tcase; t++) {
			isfinished = false;

			n = Integer.parseInt(br.readLine());
			
			// 수영장 맵 생성 및 초기화
			arr = new int[n][n];
			for(int r=0; r<n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int  c=0; c<n; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new boolean[n][n];

			// 시작 좌표 입력받기
			st = new StringTokenizer(br.readLine());
			startx = Integer.parseInt(st.nextToken());
			starty = Integer.parseInt(st.nextToken());
			
			// 종료 좌표 입력받기
			st = new StringTokenizer(br.readLine());
			finishx = Integer.parseInt(st.nextToken());
			finishy = Integer.parseInt(st.nextToken());
			
			swimStart(startx, starty, 0); // 좌표(startx,starty)에서 0초에 시작
			
			// 도착할 수 없다면 -1 출력
			if(isfinished == false) {
				sb.append("#" + t + " " + -1 + "\n");
			}
			// 도착할 수 있다면 초 출력
			else {
				sb.append("#" + t + " " + arr[finishx][finishy] + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
