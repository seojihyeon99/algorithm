import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int row; // 행크기
	static int col; // 열크기
	static int time; // 시간
	static int[] top; // 위쪽 공기청정기의 좌표
	static int[] bottom; // 아래쪽 공기청정기의 좌표
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken()); // 행크기
		col = Integer.parseInt(st.nextToken()); // 열크기
		time = Integer.parseInt(st.nextToken()); // 시간
		
		// 맵 생성 및 입력받기 + 공기청정기의 좌표 저장
		map = new int[row][col];
		for(int r=0; r<row; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<col; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == -1 && top == null) {
					top = new int[] {r,c}; // 위쪽 공기청정기의 좌표 저장
				}
				else if(map[r][c] == -1 && top != null) {
					bottom = new int[] {r,c}; // 아래쪽 공기청정기의 좌표 저장
				}
			}
		}
		
		for(int i=0; i<time; i++) {
			// 미세먼지 확산
			spread();
//			printMap();
//			System.out.println();
			// 공기청정기 작동
			clean();			
//			printMap();
		}
		
		
		int sum = 0;
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				sum+=map[r][c];
			}
		}
		System.out.println(sum+2);
	}
	
	// 방향벡터(상,하,좌,우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	// 미세먼지 확산
	static void spread() {
		// 동시에 확산이 일어남을 처리하기 위해, 배열을 임시 복사하여 결과 담음 => 후에 다시 원배열에 재복사
		int[][] temp = new int[row][col];
		for(int r=0; r<row; r++) {
			temp[r] = Arrays.copyOf(map[r], col);
		}
		
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(map[r][c]>0) { // 미세먼지가 있다면
					int amount = map[r][c]/5; // 확산될 양
					int ndir = 0; // 확산된 방향의 개수
					// 인접한 4방향으로 확산
					for(int i=0; i<4; i++) {
						int nextx = r + dx[i]; // 확산될 x좌표
						int nexty = c + dy[i]; // 확산될 y좌표
						// 배열 인덱스 넘지 않고, 인접한 방향에 공기청정기가 없어야 확산 일어남
						if(nextx>=0 && nextx<row && nexty>=0 && nexty<col && map[nextx][nexty]!=-1) {
							temp[nextx][nexty] += amount; // 확산됨
							ndir++; // 확산된 방향의 개수 1 증가
						}
					}
					int remain = temp[r][c] - (amount*ndir); // 남은 미세먼지의 양
					temp[r][c] = remain;
				}
			}
		} 
		
		// 다시 재복사
		for(int r=0; r<row; r++) {
			map[r] = Arrays.copyOf(temp[r], col);
		}
	}
	
	// 공기청정기 작동
	static void clean() {
		// 위쪽 공기청정기 작동(반시계)
		int topx = top[0];
		int topy = top[1];
		// 왼쪽
		for(int l=2; l<=topx; l++) {
			map[topx-l+1][0] = map[topx-l][0];
		}
		// 위쪽
		for(int t=1; t<=col-1; t++) {
			map[0][0+t-1] = map[0][0+t];
		}
		// 오른쪽
		for(int r=1; r<=topx; r++) {
			map[0+r-1][col-1] = map[0+r][col-1];
		}
		// 아래쪽
		for(int d=1; d<=col-2; d++) {
			map[topx][col-d] = map[topx][col-1-d];
		}
		map[topx][topy+1] = 0;
		
		// 아래쪽 공기청정기 작동(시계)
		int bottomx = bottom[0];
		int bottomy = bottom[1];
		// 왼쪽
		for(int l=2; l<=row-1-bottomx; l++) {
			map[bottomx+l-1][0] = map[bottomx+l][0];
		}
		// 아래쪽
		for(int d=1; d<=col-1; d++) {
			map[row-1][d-1] = map[row-1][d];
		}
		// 오른쪽
		for(int r=1; r<=row-1-bottomx; r++) {
			map[row-1-r+1][col-1] = map[row-1-r][col-1];
		}
		// 위쪽
		for(int t=1; t<=col-2; t++) {
			map[bottomx][col-1-t+1] = map[bottomx][col-1-t];
		}
		map[bottomx][bottomy+1] = 0;
	}
	
	
	static void printMap() {
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
