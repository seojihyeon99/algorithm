import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 벽의 가로 길이
		int[] dp = new int[n+1];
		
		if(n>=2) {
			dp[0] = 1;
			dp[2] = 3;
			for(int i=4; i<=n; i+=2) {
				int sum = 0;
				for(int j=0; j<i/2-1; j++) {
					sum += dp[j*2];
				}
				dp[i] = dp[i-2] * dp[2] + 2*sum;
			}
		}
		
		System.out.println(dp[n]);
	}
}
