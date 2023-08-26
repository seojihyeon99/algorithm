import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int[][] map; // 맵정보(해당 칸에 상어가 몇마리 있는지)
	static int n; // 행크기
	static int m; // 열크기
	static int nshark; // 상어의 수
	static int result; // 잡은 상어 크기의 합
	// 방향벡터(제자리,상,하,우,좌)
	static int[] dx = {0,-1,1,0,0};
	static int[] dy = {0,0,0,1,-1};
	
	static Map<Integer,int[]> sharkInfo = new HashMap<>(); // 상어 정보 : x, y, speed, dir, size
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 행크기
		m = Integer.parseInt(st.nextToken()); // 열크기
		nshark = Integer.parseInt(st.nextToken()); // 상어의 수
		
		// 맵 생성하기
		map = new int[n][m];
		
		// 상어 정보 입력받기
		for(int i=1; i<=nshark; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
					
			sharkInfo.put(i, new int[] {x, y, speed, dir, size});
			map[x][y] = i; // 해당 칸에 해당 번호에 해당하는 상어가 있다고 표시
		}
		
//		System.out.println("***초기값***");
//		printState();
		// step 1 : 낚시왕 오른쪽 1칸씩 이동하면서
		if(nshark == 0) {
			System.out.println(result);
			return;
		}
		
		for(int c=0; c<m; c++) {
//			System.out.println("***현재 낚시왕 이동전 상어 ***" + (c+1) +"초");
//			printState();
			// step 2 : 낚시왕의 열에 있는 가장 가까운 상어 잡음 
			int row = findShark(c);
			if(row != -1) {
				int key = map[row][c];
				result += sharkInfo.get(key)[4];
				
				// 해당 위치 상어 잡음
				sharkInfo.remove(key);
				map[row][c] = 0; 
			}		
//			System.out.println("***낚시왕 이동후***");
//			printState();
			
			moveShark();
			
		}

		System.out.println(result);
	}
	
	static void moveShark() {
		int[][] resultmap = new int[n][m];
		// 2차원 배열을 순회하며
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				if(map[r][c] != 0) {
//					System.out.println("현재  몇번상어? : " + map[r][c]);
					int key = map[r][c];
					int dir = sharkInfo.get(map[r][c])[3]; // 현재 상어의 방향
					int speed = 0;
					// 상하 방향이라면
					if(dir == 1 || dir == 2) {
						speed = sharkInfo.get(map[r][c])[2]%(2*n-2); // 현재 상어의 속력
					}
					// 우좌 방향이라면
					else if(dir == 3 || dir == 4) {
						speed = sharkInfo.get(map[r][c])[2]%(2*m-2); // 현재 상어의 속력
					}					
					int nextx = r;
					int nexty = c;
					
					// 1개의 상어가 이동
					for(int i=0; i<speed; i++) {
						int tempx = nextx + dx[dir];
						int tempy = nexty + dy[dir];
						
						// 배열 인덱스 넘어간다면 -> 방향전환
						if(tempx<0 || tempx>=n || tempy<0 || tempy>=m) { 
							// 방향 전환
							if(dir%2 == 1) dir+=1;
							else if(dir%2 == 0) dir-=1;
							// 다시 바뀐 방향으로 설정
							tempx = nextx + dx[dir];
							tempy = nexty + dy[dir];
						}
						nextx = tempx;
						nexty = tempy;
					}
					
					// 이미 해당칸에 다른 상어 존재하는 경우
					if(resultmap[nextx][nexty] != 0) {
						int cur = sharkInfo.get(key)[4];
						int next = sharkInfo.get(resultmap[nextx][nexty])[4];
						if(cur > next) {
							sharkInfo.remove(resultmap[nextx][nexty]);
							resultmap[nextx][nexty] = 0;
						}
						else {
							sharkInfo.remove(key);
//							resultmap[r][c] = 0;		
							continue;
						}
					}
					
					// 상어 이동 및 정보 업데이트
					resultmap[nextx][nexty] = key;
//					resultmap[r][c] = 0;
					sharkInfo.get(key)[0] = nextx;
					sharkInfo.get(key)[1] = nexty;
					sharkInfo.get(key)[3] = dir;
					
//					System.out.println("해당 좌표 상어 이동후");
//					System.out.println();
//					printState2(resultmap);
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			map[i] = Arrays.copyOf(resultmap[i], m);
		}
	}		

	
	// 해당 열에서 가장 가까운 상어의 행위치 찾음
	static int findShark(int col) {
		for(int r=0; r<n; r++) {
			if(map[r][col] != 0) return r;
		}
		return -1;
	}
	
	static void printState() {
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				System.out.print(map[r][c] +" ");
			}
			System.out.println();
		}
		System.out.println("========================");
	}
	
//	static void printState2(int[][] resultmap) {
//		for(int r=0; r<n; r++) {
//			for(int c=0; c<m; c++) {
//				System.out.print(resultmap[r][c] +" ");
//			}
//			System.out.println();
//		}
//		System.out.println("========================");
//	}
}
