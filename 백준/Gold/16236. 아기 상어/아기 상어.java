import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n; // 배열의 크기
	static int[][] arr; // 2차원 배열
	static int sharksize = 2; // 아기상어 크기(처음에 2)
	static int xpos; // 상어의 x좌표
	static int ypos; // 상어의 y좌표
	static int time; // 현재까지 걸린 시간
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine()); // 배열의 크기
		
		// 2차원 배열 생성 및 입력받기
		arr = new int[n][n];
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				if(arr[r][c] == 9) { // 상어 만났다면 -> 위치 업데이트
					xpos = r;
					ypos = c;
				}
			}
		}
		
		while(true) {
			boolean caneat = moveShark(xpos, ypos);
			// 더 이상 먹을 수 없는 물고기 없다면 -> 엄마상어에게 도움 요청
			if(!caneat) break;
			
			// 그중 가장 맨 위에 행, 맨 왼쪽 열 물고기 찾음
			int idx = -1;
			int row = Integer.MAX_VALUE;
			int col = Integer.MAX_VALUE;
			for(int j=0; j<eatfish.size(); j++) {
				if(eatfish.get(j)[0] < row) {
					row = eatfish.get(j)[0];
					col = eatfish.get(j)[1];
					idx = j;
				}
				else if(eatfish.get(j)[0] == row && eatfish.get(j)[1] < col) {
					row = eatfish.get(j)[0];
					col = eatfish.get(j)[1];									
					idx = j;
				}
			}		
//			System.out.println("현재 먹은 좌표 : (" +row + ", " + col + ")");
			// 상어의 위치 업데이트
			arr[xpos][ypos] = 0;
			arr[row][col] = 9;
			xpos = row;
			ypos = col;
			time += (eatfish.get(idx)[2]); // 현재까지 먹은 시간 증가시킴
			
			eatcnt++; // 먹은 개수 1증가
			if(eatcnt == sharksize) { // 아기상어가 자신의 크기와 같은 수의 물고기를 먹으면 -> 아기상어 크기 1씩 증가함
				eatcnt = 0; // 현재 먹은 개수를 다시 0으로
				sharksize++; // 상어 크기 1 증가
			}
			
//			print();
		}
		
		System.out.println(time);
		
		// 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동(1초 소요)		
		// 먹을 수 있는 물고기 1마리라면 그 물고기 먹으러감
		// 먹을 수 있는 물고기 1마리보다 많다면, 가장 거리 가까운(여러개라면 가장 위 -> 가장 왼쪽) 물고기 먹으로 감
	}
	
	// 방향벡터(상좌우하)
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	
	static int eatcnt = 0;
	static List<int[]> eatfish; // 해당 초에 먹을 수 있는 물고기
	static boolean moveShark(int x, int y) {
		boolean iseat = false; // 물고기를 먹었는지 여부
		boolean[][] visited = new boolean[n][n];
		Queue<int[]> queue = new ArrayDeque<>();
		visited[x][y] = true; // 시작지점 방문체크
		queue.offer(new int[] {x,y,0}); // 해당 시작지점, 현재 시간, 현재 상어와의 거리 큐에 넣음
		
		eatfish = new ArrayList<>(); // 해당 초에 먹을 수 있는 물고기
		int firsteattime = 0; // 처음 물고기 먹었을때 시간
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for(int i=0; i<4; i++) {
				int nextx = cur[0] + dx[i];
				int nexty = cur[1] + dy[i];
				// 배열의 인덱스 넘지않고, 방문하지 않았고, 빈칸이면
				if(nextx>=0 && nextx<n && nexty>=0 && nexty<n && !visited[nextx][nexty] && arr[nextx][nexty] == 0) {
					visited[nextx][nexty] = true; // 현재 지점 방문체크
					queue.offer(new int[] {nextx, nexty, cur[2]+1}); // 이동한 지점, (현재 시간 + 1) 큐에 넣음
				}
				// 배열의 인덱스 넘지않고, 방문하지 않았고, 상어가 있는 칸이면
				else if(nextx>=0 && nextx<n && nexty>=0 && nexty<n && !visited[nextx][nexty] && arr[nextx][nexty] != 0) {
					// 현재 상어보다 더 큰 물고기 만남 => 해당 칸 지날 수 없고, 먹을 수 없음
					if(arr[nextx][nexty] > sharksize) {
						continue;
					}
					// 현재 상어와 같은 크기 물고기 만남 => 해당 칸 지날 수는 있지만, 먹을 수 없음
					else if(arr[nextx][nexty] == sharksize) {
						visited[nextx][nexty] = true; // 현재 지점 방문체크
						queue.offer(new int[] {nextx, nexty, cur[2]+1}); // 이동한 지점, (현재 시간 + 1) 큐에 넣음						
					}
					// 현재 상어보다 작은 크기 물고기 만남 => 먹음
					else if(arr[nextx][nexty] < sharksize) {
						visited[nextx][nexty] = true; // 현재 지점 방문체크
						
						// 처음 먹기 시작한 시간이 0이라면 -> 처음 먹기 시작한 시간 업데이트
						if(firsteattime == 0) {
							firsteattime = cur[2]+1;
						}
						
						if(firsteattime == cur[2]+1) {
//							System.out.println("물고기 넣음");
							iseat = true;
							eatfish.add(new int[] {nextx, nexty, cur[2]+1}); // 해당 초에 먹을수 있는 물고기에 넣음							
						}
						else {
							return iseat;							
						}
					}
				}
			}
		}
		
		return iseat;	
	}
	
	// 현재 맵 출력
	static void print() {
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("현재 상어 크기 : "+ sharksize + ", 현재 먹은 상어 수 : " + eatcnt + ", 현재 시간 : " + time);
		System.out.println("===========================");
	}
}
