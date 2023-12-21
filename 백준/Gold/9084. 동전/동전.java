import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * dp[i][j] : 현재까지 사용한 동전의 수 i개(앞에서부터), 만드는 금액 j 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for(int t=0; t<tc; t++) {
			int n = Integer.parseInt(br.readLine()); // 동전의 수
			int[] coins = new int[n]; // 동전 별 금액
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			
			int amount = Integer.parseInt(br.readLine()); // 만들어야 할 금액
			
			int[][] dp = new int[n][amount+1];
			// 첫번째 동전에 대한 dp 테이블 작성
			for(int i=coins[0]; i<=amount; i+=coins[0]) {
				dp[0][i] = 1;
			}
			for(int i=0; i<n; i++) {
				dp[i][0] = 1;
			}
			
			// 나머지번째 동전에 대한 dp 테이블 작성
			for(int i=1; i<n; i++) {
				dp[i] = Arrays.copyOf(dp[i-1], amount+1);
				int coin = coins[i]; // 현재 동전의 금액
				for(int j=coin; j<=amount; j++) {
					dp[i][j] = dp[i][j-coin] + dp[i-1][j];
				}
			}
			
			sb.append(dp[n-1][amount] + "\n");
		}
		
		System.out.println(sb);
	}
}
