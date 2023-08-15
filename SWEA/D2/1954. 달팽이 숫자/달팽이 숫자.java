import java.util.Scanner;

public class Solution {
	// 방향벡터 (우, 하, 좌, 상)
	static int[] dx  = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int ncase = sc.nextInt(); // 테스트 케이스 수
		
		// 테스트 케이스 수만큼 반복
		for(int t=1; t<=ncase; t++) {
			int n = sc.nextInt(); // 달팽이 크기 N
			
			int[][] arr = new int[n][n]; // 달팽이 숫자 저장하는 배열
			boolean[][] visited = new boolean[n][n]; // 방문 체크 저장하는 배열
			
			// 시작 좌표를 (0,0)으로
			int curx = 0;
			int cury = 0;
			
			int direction = 0; //시작 방향을 0으로
			
			for(int count=1; count<=n*n; count++) {
				// 현재 좌표 방문 처리
				visited[curx][cury] = true;

				// 달팽이 숫자 1씩 증가시키면서 대입
				arr[curx][cury] = count;
				
				// 현재 방향으로 1칸 이동했을때의 좌표
				int nextx = curx + dx[direction];
				int nexty = cury + dy[direction];
				
				// 현재 방향으로 1칸 이동했을 때, 배열의 인덱스 벗어나면
				if(nextx<0 || nextx>=n || nexty<0|| nexty>=n || visited[nextx][nexty]) {
					direction = (direction + 1) % 4; // 방향을 바꿈
					nextx = curx+dx[direction];
					nexty = cury+dy[direction];			
				}
				
				// 다음 반복의 좌표 업데이트
				curx = nextx;
				cury = nexty;
			}
			
			sb.append("#"+t+"\n");
			
			//테스트 케이스 출력 저장
			for(int r=0; r<n; r++) {
				for(int c=0; c<n; c++) {
					sb.append(arr[r][c] + " ");
				}
				sb.append("\n");
			}
			
		}
		
		System.out.println(sb.toString());
	}

}
