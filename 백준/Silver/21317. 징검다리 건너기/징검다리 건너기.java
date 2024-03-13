import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] energy = new int[n][2];
		for(int i=1; i<=n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				energy[i][j] = Integer.parseInt(st.nextToken());
			}			
		}
		int k = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n+1];
		
		if(n >= 2) dp[2] = energy[1][0];
		if(n >= 3) dp[3] = Math.min(dp[2] + energy[2][0], dp[1] + energy[1][1]);
		
		int min = Integer.MAX_VALUE;
		for(int idx=4; idx<=n; idx++) { // 매우 큰 점프를 해서 도착한 인덱스를 1개 정함			
			dp = new int[n+1];
			dp[2] = energy[1][0];
			dp[3] = Math.min(dp[2] + energy[2][0], dp[1] + energy[1][1]);
			
			for(int i=4; i<=n; i++) {
				// 작은 점프 or 큰 점프 중 작은 것
				dp[i] = Math.min(dp[i-1] + energy[i-1][0], dp[i-2] + energy[i-2][1]);
				
				if(i == idx) dp[i] = Math.min(dp[i], dp[i-3] + k);
			}
			
			min = Math.min(min, dp[n]);
		}
		
		if(n <= 3) min = dp[n];
		System.out.println(min);
	}
}