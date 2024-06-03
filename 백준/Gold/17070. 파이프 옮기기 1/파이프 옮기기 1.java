import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n; // 집의 한변의 길이
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine()); // 집의 한변의 길이
		
		// 2차원 배열 입력받기
		int[][] map = new int[n+1][n+1];
		for(int r=1; r<=n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=1; c<=n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 오른쪽 (같을경우 아래쪽) x좌표, y좌표, 파이프의 방향(0:가로, 1:세로, 2:대각선)
		int[][][] dp = new int[n+1][n+1][3];
		dp[1][2][0] = 1; // 처음 (1,1), 가로방향
		
		for(int r=1; r<=n; r++) {
			for(int c=1; c<=n; c++) {
				// 오른쪽 (같을경우 아래쪽) 좌표에 벽이 있다면, 1행 1&2열은 pass
				if(map[r][c] == 1 || (r==1 && c<=2)) continue;
				
				dp[r][c][0] = dp[r][c-1][0] + dp[r][c-1][2]; // 현재 파이프 방향 가로 => 이전에 파이프 가로 또는 대각선
				dp[r][c][1] = dp[r-1][c][1] + dp[r-1][c][2]; // 현재 파이프 방향 세로 => 이전에 파이프 세로 또는 대각선
				
				// 현재 파이프 방향이 대각선일 경우, 위와 왼쪽에 벽이 있다면
				if(map[r][c-1] == 1 || map[r-1][c] == 1) continue;
				dp[r][c][2] = dp[r-1][c-1][0] + dp[r-1][c-1][1] + dp[r-1][c-1][2]; // 현재 파이프 방향 대각선 => 이전에 파이프 가로 또는 세로 또는 대각선
			}
		}
		
		System.out.println(dp[n][n][0] + dp[n][n][1] + dp[n][n][2]);
		
	}
	
	
}