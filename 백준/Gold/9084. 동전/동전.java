import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * sol1) 2차원 배열 이용
 * dp[i][j] : 현재까지 사용한 동전의 수 i개(앞에서부터), 만드는 금액 j원 일때의 경우의 수
 * 점화식 dp[i][j] = dp[i-1][j]     +     dp[i][j-현재동전금액]
 * 				  -> 현재 보는 동전 사용 x		   -> 현재 보는 동전 사용 o(지금까지 (j-현재 동전)을 만든 방법에 "현재 동전" 1개를 추가시킴)
 * 만약 보고 있는 동전이 c원이라면, 0~c-1열까지는 이전까지의 경우의 수를 복사해서 가져옴! => 이번행은 c열부터 시작!!!!
 * 
 * sol2) 1차원 배열 이용
 * 배열 복사 없이, 1차원 배열을 동전수만큼 업데이트하는 식으로 사용 가능!!
 * dp[i] : 만드는 금액 i원 일때의 경우의 수
 * dp[i] = dp[i] + dp[i-coin] (단, dp[i-coin] == dp[0] 이라면 -> 1)
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
				int coin = coins[i]; // 현재 동전의 금액
				// 0원 ~ coin-1원 까지는 현재 동전(coin)으로 만들 수 없으므로, 이전것 복사
				if(coin < amount) {
					for(int j=0; j<coin; j++) {
						dp[i][j] = dp[i-1][j];
					}					
				} else {
					for(int j=0; j<=amount; j++) {
						dp[i][j] = dp[i-1][j];
					}						
				}
				
				// coin원 ~
				for(int j=coin; j<=amount; j++) {
					dp[i][j] = dp[i][j-coin] + dp[i-1][j]; // 현재 i번째 동전 사용 o + 현재 i번째 동전 사용 x
				}
			}
			
			sb.append(dp[n-1][amount] + "\n");
		}
		
		System.out.println(sb);
	}
}
