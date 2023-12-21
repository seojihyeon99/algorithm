import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * dp[i][j] : i번째 잔까지의 최댓값, j는 마지막으로 연속된 마신 컵의 개수
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 포도잔의 개수
		int[][] dp = new int[n+1][3];
		dp[1][1] = Integer.parseInt(br.readLine());
		
		for(int i=2; i<=n; i++) {
			int num = Integer.parseInt(br.readLine());
			dp[i][0] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
			dp[i][1] = dp[i-1][0] + num;
			dp[i][2] = dp[i-1][1] + num;
		}
		
		System.out.println(Math.max(Math.max(dp[n][0], dp[n][1]), dp[n][2]));
	}
}