import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp[r][c] : (r,c)정점까지의 최소 비용
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int idx = 1;
		while(true) {
			int n = Integer.parseInt(br.readLine()); // 그래프 행의 개수 
			if(n == 0) break;
			
			int[][] dp = new int[n][3];
			// 1번째 행 입력받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			Integer.parseInt(st.nextToken()); 
			dp[0][0] = Integer.MAX_VALUE;
			dp[0][1] = Integer.parseInt(st.nextToken()); 
			dp[0][2] = Integer.parseInt(st.nextToken()); 
			if(dp[0][2] < 0) dp[0][2] = dp[0][2] + dp[0][1];
			else dp[0][2] = dp[0][1];
			
			for(int r=1; r<n; r++) {
				st = new StringTokenizer(br.readLine());
				dp[r][0] = Integer.parseInt(st.nextToken()) + Math.min(dp[r-1][0], dp[r-1][1]);
				dp[r][1] = Integer.parseInt(st.nextToken()) + Math.min(Math.min(Math.min(dp[r-1][0], dp[r-1][1]), dp[r-1][2]), dp[r][0]);
				dp[r][2] = Integer.parseInt(st.nextToken()) + Math.min(Math.min(dp[r-1][1], dp[r-1][2]), dp[r][1]);
			}
			
			sb.append((idx++) + ". " + dp[n-1][1] + "\n");
		}
		
		System.out.println(sb);
	}
}
