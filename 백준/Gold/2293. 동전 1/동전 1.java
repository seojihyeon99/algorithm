import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * dp[n][m] : n개의 동전(1~n까지의 동전까지 고려)을 사용하여 m원을 만드는 경우의 수
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 동전의 가짓 수
		int k = Integer.parseInt(st.nextToken()); // 동전 가치의 합
		
		int[][] dp = new int[n][k+1];
		// 1열 채우기
		for(int r=0; r<n; r++) {
			dp[r][0] = 1;
		}
		// 1행 채우기
		int coin = Integer.parseInt(br.readLine());
		for(int c=0; c<=k; c+=coin) {
			dp[0][c] = 1;
		}
		
		for(int i=1; i<n; i++) {
			coin = Integer.parseInt(br.readLine());
			
			dp[i] = Arrays.copyOf(dp[i-1], k+1);
			for(int j=coin; j<=k; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-coin]; // 현재 동전 고려 x + 현재 동전 고려 o(무조건 1개는 포함)
			}
		}
		
		System.out.println(dp[n-1][k]);
	}
}
