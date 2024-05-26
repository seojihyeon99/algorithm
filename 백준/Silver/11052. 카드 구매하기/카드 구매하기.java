import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 구매하려는 카드 개수
		
		int[] dp = new int[n+1]; // n개의 카드를 구매하기 위한 최대 금액
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			dp[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=2; i<=n; i++) {
			int max = dp[i];
			for(int j=i-1; j>=i/2; j--) {
				max = Math.max(max, dp[j] + dp[i-j]);
			}
			dp[i] = max;
		}
		
		System.out.println(dp[n]);
	}
}