import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n; // 행과 열의 크기
	static int[][] arr; // 2차원 배열 맵 저장
	static boolean[][] cloud; // 현재 구름의 위치
	
	// 방향벡터(원위치, 좌, 좌상, 상, 우상, 우, 우하, 하, 좌하)
	static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 행과 열의 크기
		int moveCnt = Integer.parseInt(st.nextToken()); // 이동 횟수
		
		// 2차원 배열 생성 및 입력받기
		arr = new int[n][n];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 현재 구름의 위치 저장 배열 생성 및 초기화
		cloud = new boolean[n][n]; 
		cloud[n-1][0] = true;
		cloud[n-1][1] = true;
		cloud[n-2][0] = true;
		cloud[n-2][1] = true;
		
		// 이동 횟수만큼 반복하며  시뮬레이션 시작!!
		for(int i=0; i<moveCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken()); // 이동 방향(1~8)
			int dist = Integer.parseInt(st.nextToken()); // 이동 거리
			
			simulation(dir, dist); 
			
/*			System.out.println((i+1)+"번째 반복 후 좌표");
			printArr();*/
		}
		
		// 이동 모두 끝난 후 바구니에 들어있는 물의 양의 합 출력
		int sum = 0;
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				sum += arr[r][c];
			}
		}
		System.out.println(sum);
	}
	
	static void simulation(int dir, int dist) { // dir: 이동 방향, dist : 이동 거리
		boolean[][] newCloud = new boolean[n][n]; // 새로운 구름의 위치
		
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				// 1) 모든 구름이 dir 방향으로 dist만큼 이동
				// 해당 위치에 구름이 존재한다면 => dir 방향으로 dist%n 만큼 이동(이동거리가 n만큼의 주기로 반복될것임)
				if(cloud[r][c]) { 
					int x = r + dx[dir]*(dist%n); // 이동한 구름의 x좌표
					x = (x+n)%n; // x 위치 보정(배열 인덱스 넘을 때 이어지게)
					int y = c + dy[dir]*(dist%n); // 이동한 구름의 y좌표
					y = (y+n)%n; // y 위치 보정(배열 인덱스 넘을 때 이어지게)
					
					newCloud[x][y] = true; // 이동한 구름 표시
					arr[x][y]++; // 2) 해당 칸의 물 1 증가시킴
				}
			}
		}
		
		// 4) 물이 증가한 칸에 물복사 버그 마법 시전
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				// 해당 위치에 구름이 존재한다면 => 물복사 버그 마법 시전
				if(newCloud[r][c]) {
					int cnt = 0; // 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 개수
					for(int i=2; i<=8; i+=2) { // 대각선 방향의 방향벡터(2,4,6,8)
						int x = r + dx[i];
						int y = c + dy[i];
						// 배열의 인덱스 넘지 않고, 물이 있으면(>0)
						if(x>=0 && x<n && y>=0 && y<n && arr[x][y]>0) cnt++;
					}
					arr[r][c] += cnt; // 해당 위치의 물의 양 cnt만큼 증가
				}
			}
		}
		
		cloud = new boolean[n][n];
		// 5) 물의 양이 2 이상인 칸에 구름이 생기고, 물의 양이 2 줄어듦 (단, 원래 구름 위치에는 구름 생성 불가)		
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				// 물의 양이 2 이상이고, 해당 위치에 구름이 없다면
				if(arr[r][c] >=2 && !newCloud[r][c]) {
					arr[r][c] -= 2; // 물의 양이 2 줄어듦
					cloud[r][c] = true; // 다음 이동에서 해당 위치에 구름 있게 됨
				}
			}
		}
	}

/*	static void printArr() {
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("====================");
	}*/
}
