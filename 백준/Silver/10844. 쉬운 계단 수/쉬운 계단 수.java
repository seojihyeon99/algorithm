import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * dp[i][j] : 현재 자릿수가 j일때, 맨 마지막 자리 숫자 i
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 수의 길이
		long[][] dp = new long[10][n+1]; // 열이 -1~10을 의미하는 배열
		// 처음 n=1일때의 dp 채움 (계단수는 0으로 시작 불가능)
		for(int i=1; i<=9; i++) {
			dp[i][1] = 1;
		}
		
		// 나머지 dp 채움
		for(int j=2; j<=n; j++) {
			for(int i=0; i<=9; i++) {
				if(i==0) dp[i][j] = dp[i+1][j-1];
				else if(i==9) dp[i][j] = dp[i-1][j-1];
				else {
					dp[i][j] = (dp[i-1][j-1] + dp[i+1][j-1])%1000000000;
				}
			}
		}
		
		// 결과
		long sum = 0;
		for(int i=0; i<=9; i++) {
			sum += dp[i][n]%1000000000;
		}
		sum %= 1000000000;
		
		System.out.println(sum);
	}
}
