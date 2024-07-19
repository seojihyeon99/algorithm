import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 남은 날짜
		int[][] schedule = new int[2][n+1]; // 상담 일정표 (0행: 시간, 1행: 비용)
		
		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			schedule[0][i] = Integer.parseInt(st.nextToken()); // 상담시간
			schedule[1][i] = Integer.parseInt(st.nextToken()); // 상담비용
		}
		
		int[] dp = new int[n+2]; // dp[i]: i일부터 마지막날(n)까지의 최대이익
		for(int i=n; i>=1; i--) {
			// 해당 날짜에 상담 진행 o
			int take = 0;
			if(i + schedule[0][i] -1 <= n) {
				take = dp[i + schedule[0][i]] + schedule[1][i];
			}
			
			// 해당 날짜에 상담 진행 x
			int notTake = dp[i+1];
			
			dp[i] = Math.max(take, notTake);
		}
		
		System.out.println(dp[1]);
	}
}