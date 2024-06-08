import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	// 방향벡터 (하우)
	static int[] dx = {1,0};
	static int[] dy = {0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 게임 판의 크기
		// 2차원 배열 입력받기
		int[][] map = new int[n][n];
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		long[][] dp = new long[n][n];
		dp[0][0] = 1; // 시작점
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				int num = map[r][c];

				// 현재 주어진 숫자가 0이면
				if(num == 0) continue;
				
				// 아래/오른쪽 이동
				for(int i=0; i<2; i++) {
					int nx = r + num*dx[i]; // 다음 x좌표
					int ny = c + num*dy[i]; // 다음 y좌표
					
					// 배열의 인덱스 넘는 경우 pass
					if(nx>=n || ny>=n) continue;
					
					dp[nx][ny] += dp[r][c]; // 경로의 개수 증가
				}
				
			}
		}
		
		System.out.println(dp[n-1][n-1]);
	}
}