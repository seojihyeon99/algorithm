import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[10];
		for(int r=0; r<10; r++) {
			dp[r] = 1;
		}
		
		for(int c=2; c<=n; c++) {
			for(int r=9; r>=0; r--) {
				int sum = 0;
				for(int i=0; i<=r; i++) {
					sum += dp[i];
				}
				dp[r] = sum % 10007;
			}
		}
		
		int result = 0;
		for(int i=0; i<=9; i++) {
			result += dp[i];
		}
		System.out.println(result % 10007);
	}
}