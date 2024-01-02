import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * dp[i][j] : 암호길이가 i일때까지의 경우의 수. j는 그 전 마지막 단어 누적여부(1: 누적, 0: 누적 x) 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine(); // 암호코드
		int n = str.length(); // 암호코드의 길이
		int[][] dp = new int[n+1][2];
		
		// 초기 길이가 1일 경우 숫자가 0이 아니면 => 1가지
		if(str.charAt(0)-'0' != 0)
			dp[1][0] = 1;
		
		for(int i=2; i<=n; i++) {
			// 현재까지 누적시킨 단어가 없는 경우 (그 전까지 단어를 누적했거나(1)/그 전까지 단어를 누적하지 않았거나(0)) => 이번 숫자가 0이 아니어야!!
			if(str.charAt(i-1)-'0' != 0) {
				dp[i][0] = (dp[i][0] + dp[i-1][1]) % 1000000;
				dp[i][0] = (dp[i][0] + dp[i-1][0]) % 1000000;
			}
			
			// 현재까지 누적시킨 단어가 있는 경우 (그 전까지 단어를 누적하지 않았어야(0)) => 누적한 단어의 숫자가 1~26 사이여야!!
			String newChar = String.valueOf(str.charAt(i-2)) + String.valueOf(str.charAt(i-1));
			if(Integer.valueOf(newChar) <= 26) {
				dp[i][1] = (dp[i][1] + dp[i-1][0]) % 1000000;				
			}
		}
		
		System.out.println((dp[n][0] + dp[n][1]) % 1000000);
	}
}
