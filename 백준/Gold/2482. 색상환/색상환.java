import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[n+1][k+1];
		
		for(int r=1; r<=n; r++) {
			dp[r][0] = 1;
			dp[r][1] = r;
		}
		
		if(k>=2) dp[3][2] = 1;
		
		for(int r=3; r<n; r++) {
			for(int c=2; c<=k; c++) {
				dp[r][c] = (dp[r-1][c] + dp[r-2][c-1])%1000000003;
			}
		}
		
		System.out.println((dp[n-3][k-1] + dp[n-1][k])%1000000003);
		
	}
}