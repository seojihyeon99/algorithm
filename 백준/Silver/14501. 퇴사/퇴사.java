import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 남은 날짜
		int[][] arr = new int[2][n+1]; // 상담 일정표
		
		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[0][i] = Integer.parseInt(st.nextToken()); // 상담시간
			arr[1][i] = Integer.parseInt(st.nextToken()); // 상담비용
		}
		
		int[] dp = new int[n+1]; // dp[i] : i번째날에 얻을 수 있는 최대 수익
		for(int i=1; i<=n; i++) {
			dp[i] = dp[i-1];
			
			// 상담 기간의 최소 1일 ~ 최대 5일 => 그 전 5개를 탐색
			for(int j=i; j>=i-5; j--) {
				if(j < 1) break;
				
				// 현재 날짜에 끝나는 상담이 있다면, 최대 수익 업데이트 될 가능성 있음
				if(j + arr[0][j] - 1 == i) {
					dp[i] = Math.max(dp[i], dp[j-1] + arr[1][j]);
				}
			}
		}
		
		System.out.println(dp[n]);
		
	}
}