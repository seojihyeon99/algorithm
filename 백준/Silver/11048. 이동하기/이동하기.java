import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 행크기
		int m = Integer.parseInt(st.nextToken()); // 열크기
		
		// 미로 생성 및 입력받기
		int[][] maze = new int[n+1][m+1]; // 0번 인덱스들 사용 x
		for(int r=1; r<=n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=m; c++) {
				maze[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[n+1][m+1]; // 메모
		dp[1][1] = maze[1][1];
		
		for(int r=1; r<=n; r++) {
			for(int c=1; c<=m; c++) {
				dp[r][c] = dp[r-1][c] + maze[r][c];
				if(dp[r][c-1]+maze[r][c] > dp[r][c]) {
					dp[r][c] = dp[r][c-1]+maze[r][c];
				}
				if(dp[r-1][c-1]+maze[r][c] > dp[r][c]) {
					dp[r][c] = dp[r-1][c-1]+maze[r][c];
				}				
			}
		}
		
		System.out.println(dp[n][m]);
	}
}
